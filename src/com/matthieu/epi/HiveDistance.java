package com.matthieu.epi;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HiveDistance implements Solution {
    private static class coordinates{
        long x;
        long y;
        public String toString() {
            return "("+x+","+y+")";
        }
    }

    private long getRingId(long cell) {
        long res = 0;
        while (1+3*res*(res+1) < cell) res++;
        return res;
    }

    private coordinates getCoordinates(long cell, long ring) {
        coordinates res= new coordinates();
        if (ring <= 0) {
            switch ((int)cell) {
                case 1: res.x=0; res.y=0; break;
                case 2: res.x=1; res.y=0; break;
                case 3: res.x=0; res.y=-1; break;
                case 4: res.x=-1; res.y=-1; break;
                case 5: res.x=-1; res.y=0; break;
                case 6: res.x=0; res.y=1; break;
                case 7: res.x=1; res.y=1; break;
            }
            return res;
        }

        long corner1 = 2+(3*ring+1)*(ring-1);
        if (cell <= corner1) {
            res.x=ring;
            res.y=corner1-cell;
            return res;
        }
        long corner2 = 3+(3*ring+2)*(ring-1);
        if (cell <= corner2) {
            res.x=corner2-cell;
            res.y=(corner2-cell)-ring;
            return res;
        }
        long corner3 = 4+(3*ring+3)*(ring-1);
        if (cell <= corner3) {
            res.x=(corner3-cell)-ring;
            res.y = -ring;
            return res;
        }
        long corner4 = 5+(3*ring+4)*(ring-1);
        if (cell <= corner4) {
            res.x=-ring;
            res.y=cell-corner4;
            return res;
        }
        long corner5 = 6+(3*ring+5)*(ring-1);
        if (cell <= corner5) {
            res.x=cell-corner5;
            res.y=ring+(cell-corner5);
            return res;
        }
        long corner6 = 7+(3*ring+6)*(ring-1);
        if (cell <= corner6) {
            res.x=ring-(corner6-cell);
            res.y=ring;
            return res;
        }
        res.x=ring;
        res.y=ring-(cell-corner6);
        return res;
    }

    private long hexDistance(coordinates a, coordinates b) {
        // from a go to b

        if (((a.x >= b.x) && (a.y<=b.y)) ||
                ((a.x <= b.x) && (a.y>=b.y))) // basically going up and down only
            return (Math.abs(a.x-b.x) + Math.abs(a.y-b.y));
        else {
            long diagonal=Math.min(Math.abs(a.x-b.x),Math.abs(a.y-b.y));
            long rest = Math.max(Math.abs(a.x-b.x),Math.abs(a.y-b.y)) - diagonal;
            return rest+diagonal;
        }
    }

    public long hexDistance(long cell1, long cell2) {
        long ring1=getRingId(cell1);
        coordinates c1 = getCoordinates(cell1, ring1);
        long ring2=getRingId(cell2);
        coordinates c2 = getCoordinates(cell2, ring2);
        return hexDistance(c1, c2);
    }

    @Override
    public void solveProblem() {

        Charset charset = Charset.forName("US-ASCII");
        try  {
            Path file = Paths.get("test.csv");
            BufferedReader reader = Files.newBufferedReader(file, charset);
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] numbers = line.split(",");
                long distance = hexDistance(Long.valueOf(numbers[0].trim()), Long.valueOf(numbers[1].trim()));
                if (distance != Long.valueOf(numbers[2].trim()))
                    System.out.println("Failed test for distance from "+Long.valueOf(numbers[0].trim())+" to "+Long.valueOf(numbers[1].trim()));
                //else
                    //System.out.println("Test OK from "+Long.valueOf(numbers[0].trim())+" to "+Long.valueOf(numbers[1].trim()));
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }
}
