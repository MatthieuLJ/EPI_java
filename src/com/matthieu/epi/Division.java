package com.matthieu.epi;

public class Division implements Solution {
    public static int divide(int a, int b) {
        int res=0;
        while (a>b) {
            res++;
            a=a-b;
        }
        return res;
    }
    @Override
    public void solveProblem() {
        System.out.println("5/2="+divide(5,2));
        System.out.println("73/7="+divide(73,7));
    }
}
