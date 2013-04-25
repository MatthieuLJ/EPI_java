package com.matthieu.epi;

public class DivisionApproximation implements Solution {
    public float approximateDivision(float x, float y, float epsilon) {
        float min=0;
        float max=x;
        while (max*y < x) max*=10; // in case y<1

        float mid =0;
        while (Math.abs(mid*y-x) > epsilon) {
            mid= min+(max-min)/2;
            if (mid*y>x) {
                max=mid;
            } else {
                min=mid;
            }
        }
        return mid;
    }
    @Override
    public void solveProblem() {
        System.out.println("5/7 ~ "+approximateDivision(5,7,0.001f));
    }
}
