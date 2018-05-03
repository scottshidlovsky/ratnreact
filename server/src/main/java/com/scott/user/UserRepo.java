package com.scott.user;

import com.google.inject.Inject;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import ratpack.exec.Blocking;
import ratpack.exec.Promise;

import javax.sql.DataSource;

public class UserRepo {

    DataSource dataSource;

    @Inject()
    UserRepo(DataSource dataSource) {
        System.out.println("data source" + dataSource);
        this.dataSource = dataSource;
    }

    public Promise<User> retrieveUser(User user) {
        DSLContext dsl = DSL.using(this.dataSource, SQLDialect.H2);
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
}
