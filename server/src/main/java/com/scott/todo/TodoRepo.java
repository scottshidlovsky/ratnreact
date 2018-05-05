package com.scott.todo;

import com.google.inject.Inject;
import com.scott.user.User;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import ratpack.exec.Blocking;
import ratpack.exec.Promise;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

public class TodoRepo {
    DataSource dataSource;

    @Inject()
    TodoRepo(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // jooq making normalizing data a piece of cake.. :)
    public Promise<Map<Long, Todo>> retrieveTodos(String username) {
        DSLContext dsl = DSL.using(this.dataSource, SQLDialect.H2);
        return Blocking.get(() -> {
            return dsl.select()
                    .from(jooq.tables.Todo.TODO)
                    .join(jooq.tables.User.USER)
                        .on(jooq.tables.Todo.TODO.USER_ID.eq(jooq.tables.User.USER.USER_ID))
                    .where(jooq.tables.User.USER.USERNAME.eq(username))
                    .orderBy(jooq.tables.Todo.TODO.ORDER)
                    .fetchMap(jooq.tables.Todo.TODO.TODO_ID, Todo.class);

        });
    }
}
