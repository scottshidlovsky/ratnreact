package com.scott.authentication;

import com.google.inject.Inject;
import com.scott.StatusCodes;
import com.scott.user.User;
import com.scott.user.UserRepo;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.jackson.Jackson;

import java.util.Collections;
import java.util.Map;

import static ratpack.jackson.Jackson.json;


public class LoginHandler implements Handler {

    UserRepo userRepo;
    TokenService tokenService;

    @Inject
    LoginHandler(UserRepo userRepo, TokenService tokenService) {
        this.userRepo = userRepo;
        this.tokenService = tokenService;
    }

    private void handlePost(Context ctx) {
        System.out.println(ctx.getRequest());
        ctx.parse(Jackson.fromJson(User.class)).flatMap(user -> {
            return this.userRepo.retrieveUserByUsername(user.getUsername());
        }).then((User user) -> {
            if (user == null) {
                ctx.getResponse().status(StatusCodes.NOT_FOUND);
                ctx.getResponse().send();
            } else {
                // TODO(scottshidlovsky) - check if token is null
                // TODO(scottshidlovsky) - Looks like best practice is to put this in a http, secure cookie and add csrf protection
                //                         But since this is a play app....
                Map<String, Object> ret = Collections.singletonMap("token", this.tokenService.generateToken(user));
                ctx.render(json(ret));
            }
        });
    }

    @Override
    public void handle(Context ctx) throws Exception {
        ctx.byMethod(m -> {
            m.post(this::handlePost);
        });
    }
}
