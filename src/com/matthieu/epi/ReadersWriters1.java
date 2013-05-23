package com.matthieu.epi;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ReadersWriters1 implements Solution {
    private static final Object writerLock = new Object();
    private static int numReaders;
    private static CountDownLatch countDown;

    private static class Reader implements Runnable {
        static int count=0;
        int id = count++;

        @Override
        public void run() {
            try {
                int num_reads = (int) (Math.random()*10)+1;
                while (num_reads>0) {
                    num_reads--;

                    Thread.sleep((long) (Math.random()*50));

                    synchronized(writerLock) {
                        numReaders++;
                        System.out.println("Reader "+id+" reading");
                    }


                    Thread.sleep((long) (Math.random()*50));

                    synchronized (writerLock) {
                        numReaders--;
                        writerLock.notifyAll();
                        System.out.println("Reader "+id+" done");
                    }
                }
                countDown.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class Writer implements Runnable {
        static int count=0;
        int id = count++;

        @Override
        public void run() {
            int num_writes = (int) (Math.random()*10)+1;
            try {
                while(num_writes>0) {
                    num_writes--;
                    Thread.sleep((long) (Math.random()*50));

                    synchronized (writerLock) {
                        while(numReaders >0) {
                            writerLock.wait();
                        }
                        System.out.println("Writer "+id+" writing");
                        Thread.sleep((long) (Math.random()*50));
                        System.out.println("Writer "+id+" done");
                    }
                }
                countDown.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void solveProblem() {
        Executor exec = Executors.newCachedThreadPool();
        int reads=5;
        int writes=2;
        countDown = new CountDownLatch(reads+writes);
        for (int i=0; i<reads; i++)
            exec.execute(new Reader());
        for (int i=0; i<writes; i++)
            exec.execute(new Writer());
        try {
            countDown.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
