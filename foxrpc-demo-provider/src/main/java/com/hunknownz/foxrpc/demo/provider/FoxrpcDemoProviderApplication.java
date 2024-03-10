package com.hunknownz.foxrpc.demo.provider;

import com.hunknownz.foxrpc.core.annotation.FoxProvider;
import com.hunknownz.foxrpc.core.api.RpcRequest;
import com.hunknownz.foxrpc.core.api.RpcResponse;
import com.hunknownz.foxrpc.core.provider.ProviderBootstrap;
import com.hunknownz.foxrpc.core.provider.ProviderConfig;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.Provider;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
@Import({ProviderConfig.class})
public class FoxrpcDemoProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoxrpcDemoProviderApplication.class, args);
    }

    // Use http & JSON

    @Autowired
    ProviderBootstrap providerBootstrap;

    @RequestMapping("/")
    public RpcResponse invoke(@RequestBody RpcRequest request) {

        return providerBootstrap.invoke(request);
    }

    @Bean
    ApplicationRunner providerRun() {
        return x -> {
            RpcRequest request = new RpcRequest();
            request.setService("com.hunknownz.foxrpc.demo.api.UserService");
            request.setMethod("findById");
            request.setArgs(new Object[]{100});

            RpcResponse rpcResponse = invoke(request);
            System.out.println("return : "+rpcResponse.getData());

        };
    }
}
