package com.scott.user;

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

    private Promise<User> retrieveUser(DataSource dataSource, User user) {
        DSLContext dsl = DSL.using(dataSource, SQLDialect.H2);
        return Blocking.get(() -> {
            Record record = dsl.select()
                    .from(jooq.tables.User.USER)
                    .where(jooq.tables.User.USER.USERNAME.eq(user.getUsername()))
                    .fetchOne();
            if (record != null) {
                return record.into(User.class);
            } else {
                return null;
            }
        });
    }

    private void handlePost(Context ctx) {
        ctx.parse(Jackson.fromJson(User.class)).map(user -> {
            DataSource dataSource = ctx.get(DataSource.class);
            return retrieveUser(dataSource, user);
        }).then(user -> {
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
            m.post(h -> {
                handlePost(h);
            });
        });
    }
}
