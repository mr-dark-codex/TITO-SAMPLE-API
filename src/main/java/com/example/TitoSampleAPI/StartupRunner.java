package com.example.TitoSampleAPI;

import java.net.InetAddress;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner implements ApplicationRunner {

    @Value("${spring.security.user.name}")
    private String username;

    @Value("${spring.security.user.password}")
    private String password;

    @Value("${server.port}")
    private String serverPort;

    // @Value("${server.address}")
    // private String serverAddress;

    @Value("${server.address:}")
    private String configuredAddress;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        String actualAddress = configuredAddress.isEmpty() ? InetAddress.getLocalHost().getHostAddress()
                : configuredAddress;
        System.out.println(
                "\n\n\n ================================ TitoSampleAPI Started Successfully ========================================");
        System.out.println("Server is ready to handle requests");
        System.out.println("Authorization Username : " + username);
        System.out.println("Authorization password : " + password);
        System.out.println("Server is Running on IP : " + actualAddress + " and PORT : " + serverPort);
        System.out.println(
                "\n =============================================================================================================");
    }
}
