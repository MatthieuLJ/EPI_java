package com.matthieu.epi;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReadersWriters implements Solution {
    private final static Lock readerLock = new ReentrantLock();
    private final static Lock writerLock = new ReentrantLock();
    private final static Condition noReader = readerLock.newCondition();
    private static CountDownLatch countDown;
    private static volatile int readerCount=0;

    public static class Reader implements Runnable {
        private static int count=1;
        private int id = count++;

        @Override
        public void run() {
            int readCount = (int) (Math.random()*20);
            while (readCount > 0) {
                readCount--;
                writerLock.lock();
                try {
                    readerLock.lock();
                    try {
                        readerCount++;
                    } finally {
                        readerLock.unlock();
                    }
                } finally {
                    writerLock.unlock();
                }
                System.out.println("Reader "+id+" reading ("+readerCount+" readers)");
                try {
                    Thread.sleep((long) (Math.random()*500));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Reader "+id+" done");
                readerLock.lock();
                try {
                    readerCount--;
                    noReader.signalAll();
                } finally {
                    readerLock.unlock();
                }
                try {
                    Thread.sleep((long) (Math.random()*500));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            countDown.countDown();
        }
    }

    public static class Writer implements Runnable {
        private static int count=1;
        private int id = count++;

        @Override
        public void run() {
            int writeCount = (int) (Math.random()*20);
            while (writeCount>0) {
                writeCount--;
                writerLock.lock();
                try {
                    readerLock.lock();
                    try {
                        while (readerCount>0) {
                            noReader.await();
                        }
                        System.out.println("Writer "+id+" writing");
                        try {
                            Thread.sleep((long) (Math.random()*500));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Writer "+id+" done");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        readerLock.unlock();
                    }
                } finally {
                    writerLock.unlock();
                }
                try {
                    Thread.sleep((long) (Math.random()*500));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            countDown.countDown();
        }
    }

    @Override
    public void solveProblem() {
        Executor exec = Executors.newCachedThreadPool();
        int numReaders = 10;
        int numWriters = 4;
        countDown = new CountDownLatch(numReaders+numWriters);
        for (int i=0; i<numReaders; i++) {
            exec.execute(new Reader());
        }
        for (int i=0; i<numWriters; i++) {
            exec.execute(new Writer());
        }
        try {
            countDown.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
