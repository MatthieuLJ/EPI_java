package com.matthieu.epi;

public class DivisionApproximation implements Solution {
    public float approximateDivision(float x, float y, float epsilon) {
        float min=0;

        float max=x;
        while (max*y < x) {
             // in case y<1
            min=max;
            max*=10;
        }

        float mid =0;
        while (Math.abs(mid*y-x) > epsilon) {
            mid= (float) (min+(max-min)*0.5);
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
