package com.scott.todo;

import com.google.inject.Inject;
import com.scott.user.User;
import ratpack.func.Action;
import ratpack.handling.Chain;
import ratpack.handling.Context;
import ratpack.jackson.Jackson;

import java.util.Map;

public class TodoChain implements Action<Chain> {
    private final TodoRepo todoRepo;

    @Inject
    TodoChain(TodoRepo todoRepo) {
        this.todoRepo = todoRepo;
    }

    private void handleGet(Context ctx) {
        ctx.render(ctx.getPathTokens().get("todo"));
//        this.todoRepo.retrieveTodos(ctx.get(User.class).getUsername()).then((Map<Long, Todo> todos) -> {
//            ctx.render(Jackson.json(todos));
//        });
    }

    @Override
    public void execute(Chain chain) throws Exception {
        chain.path(":todo", this::handleGet);
        chain.all(TodoHandler.class);
    }
}
