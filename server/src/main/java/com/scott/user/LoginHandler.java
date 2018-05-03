package com.scott.user;

import com.google.inject.Inject;
import com.scott.StatusCodes;
import jooq.tables.Todo;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import ratpack.exec.Blocking;
import ratpack.exec.Promise;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.http.Status;
import ratpack.jackson.Jackson;

import javax.sql.DataSource;
import java.util.List;

import static ratpack.jackson.Jackson.fromJson;
import static ratpack.jackson.Jackson.json;


public class LoginHandler implements Handler {

    UserRepo userRepo;

    @Inject
    LoginHandler(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    private void handlePost(Context ctx) {
        ctx.parse(Jackson.fromJson(User.class)).flatMap(user -> {
            return this.userRepo.retrieveUser(user);
        }).then((User user) -> {
            if (user == null) {
                ctx.getResponse().status(StatusCodes.NOT_FOUND);
                ctx.getResponse().send();
            } else {
                ctx.render(json(user));
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
