package com.matthieu.epi;

import java.util.Arrays;
import java.util.LinkedList;

public class LargestAreaUnderSkyline implements Solution {
    private static class Border {
        int x;
        int y;
        public Border(int x, int y) {
            this.x=x;
            this.y=y;
        }
    }
    public static int largestRectangle(int [] skyline) {
        LinkedList<Border> border= new LinkedList<Border>();

        int [] from = new int[skyline.length];
        border.offer(new Border(-1, 0));
        for (int i=0; i<skyline.length; i++) {
            if (border.peekFirst().y > skyline[i])
                border.peekFirst().y=skyline[i];
            else if (skyline[i] > border.peekFirst().y)
                border.offerFirst(new Border(i, skyline[i]));

            from[i] = border.peekFirst().x;
        }

        border.clear();
        int [] to = new int[skyline.length];
        border.offer(new Border(skyline.length+1, 0));
        for (int i=skyline.length-1; i>=0; i--) {
            if (border.peekFirst().y > skyline[i])
                border.peekFirst().y=skyline[i];
            else if (skyline[i] > border.peekFirst().y)
                border.offerFirst(new Border(i, skyline[i]));

            to[i] = border.peekFirst().x;
        }

        int max=0;
        for (int i=0; i<skyline.length; i++) {
            if (skyline[i] > 0) {
                int area = (to[i] - from[i]+1) * skyline[i];
                if (area > max) {
                    max = area;
                }
            }
        }
        return max;
    }

    @Override
    public void solveProblem() {
        int []skyline = new int[] {0, 29, 29, 11, 11, 11, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 16, 16, 16, 16, 16, 16, 16, 16, 7, 0, 0, 0, 0};
        System.out.println("Skyline is "+ Arrays.toString(skyline));
        System.out.println("Largest area is "+largestRectangle(skyline));
    }
}
