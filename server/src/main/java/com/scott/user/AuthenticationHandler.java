package com.scott.user;

import com.google.inject.Inject;
import com.scott.StatusCodes;
import ratpack.handling.Context;
import ratpack.handling.Handler;

public class AuthenticationHandler implements Handler {

    private final UserRepo userRepo;
    TokenService tokenService;

    @Inject
    AuthenticationHandler(TokenService tokenService, UserRepo userRepo) {
        this.tokenService = tokenService;
        this.userRepo = userRepo;
    }

    @Override
    public void handle(Context ctx) throws Exception {
        String token = ctx.getRequest().getHeaders().get("token");
        if (token == null) {
            ctx.getResponse().status(StatusCodes.NOT_AUTHORIZED);
            ctx.getResponse().send();
        } else {
            String username = this.tokenService.getUsernameFromToken(token);
            if (username == null) {
                ctx.getResponse().status(StatusCodes.NOT_AUTHORIZED);
                ctx.getResponse().send();
            } else {
                this.userRepo.retrieveUserByUsername(username).then(user -> {
                    // Probably shouldn't add entire user with password incase logs pick it up or something
                    ctx.getRequest().add(User.class, user);
                    ctx.next();
                });
            }
        }
    }
}
