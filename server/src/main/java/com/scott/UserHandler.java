package com.scott;

import ratpack.func.Action;
import ratpack.handling.Chain;
import ratpack.handling.Context;
import ratpack.handling.Handler;

public class UserHandler implements Handler {
//    @Override
//    public void execute(Chain chain) throws Exception {
//        chain.path("user", ctx -> {
//            ctx.byMethod(m -> {
//                m.get(h -> {
//                    h.getResponse().status(200).send("GET USER");
//                }).post(h -> {
//                    h.getResponse().status(200).send("PUT USER");
//                });
//            });
//        });
//    }

    private void handleGet(Context handler) {
        handler.getResponse().status(401).send("getted");

    }

    private void handlePost(Context handler) {
        handler.getResponse().status(200).send("posted");
    }

    @Override
    public void handle(Context ctx) throws Exception {
        ctx.byMethod(m -> {
            m.get(h -> {
                handleGet(h);
            }).post(h -> {
                handlePost(h);
            });
        });
    }
}
