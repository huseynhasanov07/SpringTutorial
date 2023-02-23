package az.atlacademy.demo.configuration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.MappingJsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.databind.jsontype.impl.StdSubtypeResolver;
import com.fasterxml.jackson.databind.ser.BeanSerializerFactory;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import com.fasterxml.jackson.databind.util.StdDateFormat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.ClientHttpRequestInitializer;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriTemplateHandler;

@ContextConfiguration(classes = {ClientConfiguration.class})
@ExtendWith(SpringExtension.class)
class ClientConfigurationTest {
    @Autowired
    private ClientConfiguration clientConfiguration;

    /**
     * Method under test: {@link ClientConfiguration#getRestTemplate()}
     */
    @Test
    void testGetRestTemplate() {
        RestTemplate actualRestTemplate = (new ClientConfiguration()).getRestTemplate();
        List<ClientHttpRequestInitializer> clientHttpRequestInitializers = actualRestTemplate
                .getClientHttpRequestInitializers();
        assertTrue(clientHttpRequestInitializers.isEmpty());
        UriTemplateHandler uriTemplateHandler = actualRestTemplate.getUriTemplateHandler();
        assertTrue(uriTemplateHandler instanceof DefaultUriBuilderFactory);
        assertTrue(actualRestTemplate.getRequestFactory() instanceof HttpComponentsClientHttpRequestFactory);
        assertTrue(actualRestTemplate.getErrorHandler() instanceof DefaultResponseErrorHandler);
        List<HttpMessageConverter<?>> messageConverters = actualRestTemplate.getMessageConverters();
        assertEquals(7, messageConverters.size());
        assertEquals(clientHttpRequestInitializers, actualRestTemplate.getInterceptors());
        assertEquals(DefaultUriBuilderFactory.EncodingMode.URI_COMPONENT,
                ((DefaultUriBuilderFactory) uriTemplateHandler).getEncodingMode());
        assertTrue(((DefaultUriBuilderFactory) uriTemplateHandler).getDefaultUriVariables().isEmpty());
        HttpMessageConverter<?> getResult = messageConverters.get(6);
        assertEquals(2, getResult.getSupportedMediaTypes().size());
        assertEquals(2, messageConverters.get(0).getSupportedMediaTypes().size());
        assertFalse(((Jaxb2RootElementHttpMessageConverter) messageConverters.get(5)).isSupportDtd());
        assertFalse(((Jaxb2RootElementHttpMessageConverter) messageConverters.get(5)).isProcessExternalEntities());
        ObjectMapper objectMapper = ((MappingJackson2HttpMessageConverter) getResult).getObjectMapper();
        assertTrue(objectMapper.getPolymorphicTypeValidator() instanceof LaissezFaireSubTypeValidator);
        assertSame(objectMapper.getJsonFactory(), objectMapper.getFactory());
        assertTrue(objectMapper.getDeserializationContext() instanceof DefaultDeserializationContext.Impl);
        assertTrue(objectMapper.getDateFormat() instanceof StdDateFormat);
        assertTrue(objectMapper.getSerializerProviderInstance() instanceof DefaultSerializerProvider.Impl);
        assertTrue(objectMapper.getSubtypeResolver() instanceof StdSubtypeResolver);
        assertTrue(objectMapper.getVisibilityChecker() instanceof VisibilityChecker.Std);
        assertTrue(objectMapper.getSerializerProvider() instanceof DefaultSerializerProvider.Impl);
        assertNull(objectMapper.getPropertyNamingStrategy());
        assertTrue(objectMapper.getSerializerFactory() instanceof BeanSerializerFactory);
    }
}

