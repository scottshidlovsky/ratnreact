package com.scott;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.scott.authentication.AuthenticationHandler;
import com.scott.authentication.LoginHandler;
import com.scott.authentication.TokenService;
import com.scott.todo.TodoChain;
import com.scott.todo.TodoHandler;
import com.scott.todo.TodoRepo;
import com.scott.user.UserRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MainModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(UserRepo.class);
        bind(LoginHandler.class);
        bind(TokenService.class);
        bind(AuthenticationHandler.class);
        bind(TodoRepo.class);
        bind(TodoHandler.class);
        bind(TodoChain.class);

    }

    @Provides
    @Singleton
    ObjectMapper provideObjectMapper() {
        return new ObjectMapper().registerModule((new Jdk8Module()));
    }

    @Provides
    @Singleton
    PasswordEncoder providePasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
