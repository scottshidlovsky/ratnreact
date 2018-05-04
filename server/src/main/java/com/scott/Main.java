package com.scott;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.scott.user.AuthenticationHandler;
import com.scott.user.LoginHandler;
import com.scott.user.SecurityModule;
import com.scott.user.User;
import jooq.tables.Todo;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import ratpack.exec.Blocking;
import ratpack.exec.Promise;
import ratpack.guice.Guice;
import ratpack.hikari.HikariModule;
import ratpack.jackson.Jackson;
import ratpack.server.RatpackServer;

import javax.sql.DataSource;
import java.util.List;


public class Main {
//     `id` bigint auto_increment primary key,
//  `title` varchar(256),
//  `completed` bool default false,
//            `order` int default null

    public static class Person {
        private final String name;
        public Person(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }
    }

    public static class Order {
        public int id;
        public String title;
        public boolean completed;
        public int order;
        public Order(int id, String title, boolean completed, int order) {
            this.id = id;
            this.title = title;
            this.completed = completed;
            this.order = order;
        }
        public Order() {

        }
    }


    public static void main(String... args) throws Exception {
//        RxRatpack.initialize();

        RatpackServer.start(server -> server
                .registry(Guice.registry(b -> {
                    b.module(HikariModule.class, config -> {
                        config.setDataSourceClassName("org.h2.jdbcx.JdbcDataSource");
                        config.addDataSourceProperty("URL", "jdbc:h2:mem:tood;INIT=RUNSCRIPT FROM 'classpath:/init.sql'");
                    }).module(SecurityModule.class);
                }))
                .registryOf(r -> r
                        .add(new ObjectMapper().registerModule(new Jdk8Module()))
                )
                .handlers(chain -> chain
                        .all(ctx -> {
                            System.out.println("logging request: " + ctx.getRequest().getPath());
                            ctx.next();
                        })

                        // Open endpoints
                        .path("login", LoginHandler.class)


                        // Authenticated endpoints
                        .all(AuthenticationHandler.class)
                        .get("todos", ctx -> {
                            ctx.getResponse().send("HEY YOU ARE LOGGED IN AS: " + ctx.get(User.class).getUsername());
                        })
                )
        );
    }
}