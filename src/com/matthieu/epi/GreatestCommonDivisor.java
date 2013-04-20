package com.matthieu.epi;

public class GreatestCommonDivisor implements Solution {
    public static int gcdNoModulo(int a, int b) {
        int tmp;
        if (a==b)
            return a;
        if (a<b) {
            tmp=a;
            a=b;
            b=tmp;
        }
        if (b==0)
            return a;
        while (a-b>=0) {
            a=a-b;
        }
        return gcdNoModulo(b,a);
    }
    @Override
    public void solveProblem() {
        System.out.println("GCD of 136 and 248 is "+gcdNoModulo(136,248));
    }
}
