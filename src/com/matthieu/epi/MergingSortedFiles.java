package com.matthieu.epi;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class MergingSortedFiles implements Solution {
    public static class FileDataGenerator implements Iterable<FileData> {
        long last_date = 0;
        int counter = 10;
        int file;

        public FileDataGenerator(int file) {
           this.file = file;
        }

        private class FileDataIterator implements Iterator<FileData> {
            @Override
            public boolean hasNext() {
                return (counter > 0);
            }

            @Override
            public FileData next() {
                if (counter -- <= 0)
                    return null;
                last_date += Math.random()*20;
                FileData res = new FileData();
                res.time = last_date;
                res.data = Long.toString(res.time) + " : " + Integer.toString(file);
                return res;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        }

        @Override
        public Iterator<FileData> iterator() {
            return new FileDataIterator();
        }
    }

    private static class FileData implements Comparable<FileData> {
        long time;
        String data;

        public int compareTo(FileData d) {
            return Long.valueOf(this.time).compareTo(d.time);
        }
        public String toString() {
            return data;
        }
    }

    private static class StoreData<T extends Comparable<T>> implements Comparable<StoreData<T>> {
        T data;
        int file;
        public int compareTo(StoreData<T> other) {
            return this.data.compareTo(other.data);
        }

    }

    public static <T extends Comparable<T>> List<T> mergeSortedArrays(Iterable<T> inputs[]) {
        PriorityQueue<StoreData<T>> heap = new PriorityQueue<StoreData<T>>();
        List<T> res = new ArrayList<T>();
        @SuppressWarnings("unchecked") Iterator<T> iterator[] = (Iterator<T>[]) Array.newInstance(Iterator.class, inputs.length);

        for (int i=0; i<inputs.length; i++) {
            iterator[i] = inputs[i].iterator();
            T data = iterator[i].next();
            StoreData<T> to_store = new StoreData<T>();
            to_store.data = data;
            to_store.file = i;
            heap.add(to_store);
        }
        while (heap.size() > 0) {
            StoreData<T> next_in_line = heap.poll();
            res.add(next_in_line.data);

            if (iterator[next_in_line.file].hasNext()) {
                T data = iterator[next_in_line.file].next();
                StoreData<T> to_store = new StoreData<T>();
                to_store.data = data;
                to_store.file = next_in_line.file;
                heap.add(to_store);
            }
        }
        return res;
    }

    @Override
    public void solveProblem() {
        FileDataGenerator generator[] = new FileDataGenerator[20];
        for (int i=0; i<generator.length; i++) {
            generator[i] = new FileDataGenerator(i);
        }
        List<FileData> res = mergeSortedArrays(generator);
        System.out.println("Sorted data from different generators into "+res.toString());

    }
}
