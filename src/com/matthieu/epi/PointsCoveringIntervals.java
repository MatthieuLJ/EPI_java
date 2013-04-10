package com.matthieu.epi;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class PointsCoveringIntervals implements Solution {
    public static class Interval {
        int start;
        int end;
        public Interval(int s, int e) {
            start=s;
            end=e;
        }
        public String toString() {
            return "["+start+"-"+end+"]";
        }
    }

    public static class IntervalStartComparator implements Comparator<Interval> {
        @Override
        public int compare(Interval interval, Interval interval1) {
            return Integer.compare(interval.start, interval1.start);
        }

        @Override
        public boolean equals(Object o) {
            return false;
        }
    }

    public static class IntervalEndComparator implements Comparator<Interval> {
        @Override
        public int compare(Interval interval, Interval interval1) {
            return Integer.compare(interval.end, interval1.end);
        }

        @Override
        public boolean equals(Object o) {
            return false;
        }
    }

    public static List<Integer> findCoveringPoints(List<Interval> intervals) {
        PriorityQueue<Interval> startQ = new PriorityQueue<Interval>(intervals.size(), new IntervalStartComparator());
        PriorityQueue<Interval> endQ = new PriorityQueue<Interval>(intervals.size(), new IntervalEndComparator());
        for (Interval i : intervals) {
            startQ.add(i);
            endQ.add(i);
        }
        List<Integer> res = new ArrayList<Integer>();
        while (endQ.size() > 0) {
            Interval i = endQ.poll();
            res.add(i.end);
            while ((startQ.size() > 0) && (startQ.peek().start <= i.end))
                endQ.remove(startQ.poll());
        }

        return res;
    }


    @Override
    public void solveProblem() {
        List<Interval> intervals = new ArrayList<Interval>();
        int num=20;
        for (int i=0; i<num; i++) {
            int start = (int)(Math.random() * 30);
            int length = (int)(Math.random() * 8);
            intervals.add(new Interval(start, start+length));
        }
        System.out.println("For intervals "+intervals+"\nGot points "+findCoveringPoints(intervals));
    }
}
