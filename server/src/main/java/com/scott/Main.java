package com.scott;

import com.scott.authentication.AuthenticationHandler;
import com.scott.authentication.LoginHandler;
import com.scott.todo.TodoChain;
import com.scott.todo.TodoHandler;
import com.scott.user.User;
import ratpack.guice.Guice;
import ratpack.hikari.HikariModule;
import ratpack.server.RatpackServer;


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
                    }).module(MainModule.class);
                }))
                .handlers(chain -> chain

                        .all(ctx -> {
                            System.out.println("logging request: " + ctx.getRequest().getPath());
                            ctx.next();
                        })

                        // Open endpoints
                        .path("api/login", LoginHandler.class)


                        // Authenticated endpoints
                        .all(AuthenticationHandler.class)
                        .prefix("api/todos", TodoChain.class)
//                        .path("todos", TodoHandler.class)
                )
        );
    }
}