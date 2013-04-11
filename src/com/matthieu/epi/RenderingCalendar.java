package com.matthieu.epi;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class RenderingCalendar implements Solution {
    private static class EventStartEnd implements Comparable<EventStartEnd> {
        boolean is_start;
        int time;
        public EventStartEnd(boolean is_start, int time) {
            this.time = time;
            this.is_start = is_start;
        }

        @Override
        public int compareTo(EventStartEnd o) {
            if (time == o.time) {
                if (is_start) return 1;
                else return -1;
            }
            return Integer.compare(time, o.time);
        }
    }

    public static int findMaximumConcurrentEvent(List<Interval> events) {
        PriorityQueue<EventStartEnd> Q = new PriorityQueue<EventStartEnd>();
        for (Interval e : events) {
            Q.add(new EventStartEnd(true, e.start));
            Q.add(new EventStartEnd(false, e.end));
        }
        int current_number=0, max=0;
        while (Q.size() > 0) {
            EventStartEnd limit = Q.poll();
            current_number += (limit.is_start?1:-1);
            if (current_number > max)
                max = current_number;
        }
        return max;
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
        System.out.println("For intervals "+intervals+"\nMaximum simultaneous is "+findMaximumConcurrentEvent(intervals));
        intervals.clear();

        intervals.add(new Interval(0,3));
        intervals.add(new Interval(1,2));
        intervals.add(new Interval(2,3));
        System.out.println("For intervals "+intervals+"\nMaximum simultaneous is "+findMaximumConcurrentEvent(intervals));
    }
}
