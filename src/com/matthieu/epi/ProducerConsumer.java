package com.matthieu.epi;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumer implements Solution {
    private static final Lock queueLock = new ReentrantLock();
    private static final Condition queueNotFull = queueLock.newCondition();
    private static final Condition queueNotEmpty = queueLock.newCondition();

    static final List<String> array = new LinkedList<String>();
    static final int MAX_SIZE=2;
    static final int NUM_OPERATIONS=20;
    static CountDownLatch threadsDying = new CountDownLatch(2);

    private static class Producer implements Runnable {
        @Override
        public void run() {
            int count = NUM_OPERATIONS;
            while(count > 0) {
                count--;
                queueLock.lock();
                try {
                    if (array.size() >= MAX_SIZE) {
                        System.out.println("Array full");
                        try {
                            queueNotFull.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("Producer adding");
                    array.add("blablabla");
                    queueNotEmpty.signal();
                } finally {
                    queueLock.unlock();
                }
                try {
                    Thread.sleep((int) (Math.random()*500));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            threadsDying.countDown();
        }
    }

    private static class Consumer implements Runnable {
        @Override
        public void run() {
            int count = NUM_OPERATIONS;
            while (count > 0) {
                count--;
                queueLock.lock();
                try {
                    if (array.size() == 0) {
                        System.out.println("Array empty");
                        try {
                            queueNotEmpty.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("Consumer consuming");
                    array.remove(0);
                    queueNotFull.signal();
                } finally {
                    queueLock.unlock();
                }
                try {
                    Thread.sleep((int) (Math.random()*500));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            threadsDying.countDown();
        }
    }

    @Override
    public void solveProblem() {
        Executor exec = Executors.newCachedThreadPool();
        exec.execute(new Consumer());
        exec.execute(new Producer());

        try {
            threadsDying.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
