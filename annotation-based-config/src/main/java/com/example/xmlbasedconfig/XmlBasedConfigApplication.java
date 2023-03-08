package com.example.xmlbasedconfig;


import com.example.xmlbasedconfig.controller.UserController;
import com.example.xmlbasedconfig.repository.UserRepository;
import com.example.xmlbasedconfig.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlBasedConfigApplication {

    public static void main(String[] args) {
        ApplicationContext apc = new ClassPathXmlApplicationContext("config.xml");
        UserService service = apc.getBean(UserService.class);
        service.createUser();
        UserRepository repository = apc.getBean(UserRepository.class);
        repository.findUser();
        UserController controller = apc.getBean(UserController.class);
        controller.deleteUser();
    }

}
