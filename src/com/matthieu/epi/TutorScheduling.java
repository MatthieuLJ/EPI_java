package com.matthieu.epi;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class TutorScheduling implements Solution {
    private static class Request {
        long time;
        public Request(long start_time) {
            time = start_time;
        }
        @Override
        public String toString() {
            return "Request at "+time;
        }
    }

    private static class Tutor {
        long start_time;
        List<Request> handle=new ArrayList<Request>();
        @Override
        public String toString() {
            return "Tutor starting at "+start_time+" handling ["+handle.toString()+"]";
        }
    }

    private static boolean tutorCanHandleRequest(Tutor tutor, Request req) {
        if (tutor.start_time+90 < req.time)
            return false;
        for (Request r : tutor.handle) {
            if (r.time+30>req.time)
                return false;
        }
        return true;
    }

    public static List<Tutor> scheduleRequests(List<Request> requests) {
        PriorityQueue<Request> requestQ = new PriorityQueue<Request>(requests.size(), new Comparator<Request>() {
            @Override
            public int compare(Request o1, Request o2) {
                return Long.compare(o1.time, o2.time);
            }
        });
        requestQ.addAll(requests);
        List<Tutor> tutors = new ArrayList<Tutor>();

        handling_requests:
        while (requestQ.size() > 0) {
            Request request = requestQ.poll();
            for (Tutor t:tutors) {
                if (tutorCanHandleRequest(t, request)) {
                    t.handle.add(request);
                    continue handling_requests;
                }
            }
            Tutor new_tutor = new Tutor();
            new_tutor.start_time = request.time;
            new_tutor.handle.add(request);
            tutors.add(new_tutor);
        }
        return tutors;
    }

    @Override
    public void solveProblem() {
        List<Request> reqs = new ArrayList<Request>();
        reqs.add(new Request(0));
        reqs.add(new Request(120));
        reqs.add(new Request(140));
        reqs.add(new Request(150));
        reqs.add(new Request(160));
        reqs.add(new Request(200));
        reqs.add(new Request(400));

        System.out.println("To handle requests "+reqs.toString());
        List<Tutor> tuts = scheduleRequests(reqs);
        System.out.println("Need "+tuts.size()+" tutors: "+tuts.toString());
    }
}
