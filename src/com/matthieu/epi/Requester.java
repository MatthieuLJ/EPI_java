package com.matthieu.epi;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Requester implements Solution {
    static Executor exec = Executors.newCachedThreadPool();
    static CountDownLatch cdl = new CountDownLatch(1);

    public static void Dispatch(final Requester r) {
        Runnable run = new Runnable() {
            @Override
            public void run() {
                r.responseHandler.processResponse(r.executer.execute(r.request));
            }
        };
        exec.execute(run);
    }

    public abstract class RequesterResponse {
        abstract void processResponse(String response);
    }
    public abstract class RequesterExecute {
        abstract String execute(String input);
    }

    String request;
    RequesterResponse responseHandler;
    RequesterExecute executer;

    @Override
    public void solveProblem() {
        this.request = "my request is kind of long";
        this.responseHandler = new RequesterResponse() {
            @Override
            void processResponse(String response) {
                System.out.println("Response was "+response);
                cdl.countDown();
            }
        };
        this.executer = new RequesterExecute() {
            @Override
            String execute(String input) {
                StringBuilder res = new StringBuilder();
                for (String part : input.split(" ")) {
                    res.append(new StringBuilder(part).reverse());
                    res.append(" ");
                }
                return res.toString();
            }
        };
        Dispatch(this);
        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
