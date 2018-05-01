package com.scott.user;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.scott.user.LoginHandler;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SecurityModule extends AbstractModule {
    @Override
    protected void configure() {

    }

    @Provides
    PasswordEncoder providePasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Provides
    LoginHandler loginHandler() {
        return new LoginHandler();
    }
}
