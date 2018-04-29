package com.scott;

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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import ratpack.service.Service;
import ratpack.service.StartEvent;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Optional;


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
        RatpackServer.start(server -> server
                .registry(Guice.registry(b -> {
                    b.module(HikariModule.class, config -> {
                        config.setDataSourceClassName("org.h2.jdbcx.JdbcDataSource");
                        config.addDataSourceProperty("URL", "jdbc:h2:mem:tood;INIT=RUNSCRIPT FROM 'classpath:/init.sql'");
                    });
                }))
//                .registryOf(r -> r
//                        .add(new ObjectMapper().registerModule(new Jdk8Module()))
//                )
                .handlers(chain -> chain
                        .all(ctx -> {
                            System.out.println("logging requrest" + ctx.getRequest().getPath());

                            ctx.next();
                        })
                        .get(ctx -> ctx.render("Hello World!"))
//                        .get(":name", ctx -> {
//                            Optional<Person> personOptional = Optional.of(new Person("John"));
//                            ctx.render(Jackson.json(personOptional));
//                        })
                        .path("user", ctx -> {
                            DataSource dataSource = ctx.get(DataSource.class);
                            DSLContext dsl = DSL.using(dataSource, SQLDialect.H2);
//                            ctx.getRequest().
                            System.out.println(ctx.getRequest().getHeaders().getNames());
                            Promise<List<Order>> promise = Blocking.get(() -> dsl.select().from(Todo.TODO).fetch().into(Order.class));
                            promise.then(order -> {
                                ctx.getResponse().status(201);
                                ctx.render(Jackson.json(order));

                            });
                        })
                )
        );
    }
}