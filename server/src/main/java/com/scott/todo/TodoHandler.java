package com.scott.todo;

import com.google.inject.Inject;
import com.scott.user.User;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.jackson.Jackson;

import java.util.List;
import java.util.Map;

public class TodoHandler implements Handler {

    private final TodoRepo todoRepo;

    @Inject
    TodoHandler(TodoRepo todoRepo) {
        this.todoRepo = todoRepo;
    }

    private void handleGet(Context ctx) {


        this.todoRepo.retrieveTodos(ctx.get(User.class).getUsername()).then((Map<Long, Todo> todos) -> {
            ctx.render(Jackson.json(todos));
        });
    }

    @Override
    public void handle(Context ctx) throws Exception {

        ctx.byMethod(c -> {

            c.get(this::handleGet);
        });
    }
}
