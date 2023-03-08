package com.example.javabased;


import com.example.javabased.config.UserConfiguration;
import com.example.javabased.controller.UserController;
import com.example.javabased.repository.UserRepository;
import com.example.javabased.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JavaBasedApplication {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(UserConfiguration.class);
        UserController controller = applicationContext.getBean(UserController.class);
        UserService service = applicationContext.getBean(UserService.class);
        UserRepository repository = applicationContext.getBean(UserRepository.class);
        controller.createUser();
        service.deleteUser();
        repository.findUser();


    }

}
