package com.matthieu.epi;

import java.util.PriorityQueue;

public class MergingSortedFiles implements Solution {
    public static class FileDataGenerator {
        long last_date = 0;
        int counter = 10;
        int file;

        public FileDataGenerator(int file) {
           this.file = file;
        }

        public FileData getNext() {
            if (counter -- <= 0)
                return null;
            last_date += Math.random()*20;
            FileData res = new FileData();
            res.time = last_date;
            res.data = Long.toString(res.time) + " : " + Integer.toString(file);
            return res;
        }
    }

    private static class FileData implements Comparable<FileData> {
        long time;
        String data;

        public int compareTo(FileData d) {
            return Long.compare(this.time, d.time);
        }
    }

    private static class StoreData implements Comparable<StoreData> {
        FileData data;
        int file;
        public int compareTo(StoreData other) {
            return this.data.compareTo(other.data);
        }

    }

    @Override
    public void solveProblem() {
        FileDataGenerator generator[] = new FileDataGenerator[20];
        for (int i=0; i<generator.length; i++) {
            generator[i] = new FileDataGenerator(i);
        }
        PriorityQueue<StoreData> heap = new PriorityQueue<StoreData>();
        for (int i=0; i<generator.length; i++) {
            FileData data = generator[i].getNext();
            StoreData to_store = new StoreData();
            to_store.data = data;
            to_store.file = i;
            heap.add(to_store);
        }
        while (heap.size() > 0) {
            StoreData next_in_line = heap.poll();
            System.out.println(next_in_line.data.data);

            FileData data = generator[next_in_line.file].getNext();
            if (data != null) {
                StoreData to_store = new StoreData();
                to_store.data = data;
                to_store.file = next_in_line.file;
                heap.add(to_store);
            }
        }

    }
}
