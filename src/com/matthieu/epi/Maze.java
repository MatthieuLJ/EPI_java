package com.matthieu.epi;

import java.util.*;

public class Maze implements Solution {
    public static class Coords {
        int x;
        int y;
        public Coords(int x, int y){
            this.x=x;
            this.y=y;
        }

        @Override
        public String toString() {
            return "("+x+","+y+")";
        }
        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Coords))
                return false;
            if (o == null)
                return false;
            Coords other = (Coords) o;
            return (other.x == x) && (other.y == y);
        }

        @Override
        public int hashCode() {
            return new Integer(x).hashCode() ^ new Integer(y).hashCode();
        }
    }

    public static List<Coords> findPath(int [][] maze, Coords start, Coords end) {
        Coords previous[][] = new Coords[maze.length][maze[0].length];
        for (int i=0; i<previous.length; i++) {
            Arrays.fill(previous[i], null);
        }

        Queue<Coords> toExplore = new ArrayDeque<Coords>();
        toExplore.offer(start);
        Coords next=start;
        while (toExplore.size()>0) {
            next = toExplore.poll();
            if (next.equals(end))
                break;
            if ((next.x > 0) && (maze[next.y][next.x-1] == 0) && (previous[next.y][next.x-1]==null)) {
                previous[next.y][next.x-1] = next;
                toExplore.offer(new Coords(next.x-1, next.y));
            }
            if ((next.y > 0) && (maze[next.y-1][next.x] == 0) && (previous[next.y-1][next.x])==null) {
                previous[next.y-1][next.x] = next;
                toExplore.offer(new Coords(next.x, next.y-1));
            }
            if ((next.x < maze[0].length-1) && (maze[next.y][next.x+1] == 0) && (previous[next.y][next.x+1]==null)) {
                previous[next.y][next.x+1] = next;
                toExplore.offer(new Coords(next.x+1, next.y));
            }
            if ((next.y < maze.length-1) && (maze[next.y+1][next.x] == 0) && (previous[next.y+1][next.x]==null)) {
                previous[next.y+1][next.x] = next;
                toExplore.offer(new Coords(next.x, next.y+1));
            }
        }
        if (next.equals(end)) {
            List<Coords> res = new ArrayList<Coords>();
            Coords current=end;
            while (!current.equals(start)) {
                res.add(previous[current.y][current.x]);
                current = previous[current.y][current.x];
            }
            Collections.reverse(res);
            return res;
        }
        else
            return null;

    }

    @Override
    public void solveProblem() {
        int [][] maze = new int[][] {
                {1,0,0,0,0,0,1,1,0,0},
                {0,0,1,0,0,0,0,0,0,0},
                {1,0,1,0,0,1,1,0,1,1},
                {0,0,0,1,1,1,0,0,1,0},
                {0,1,1,0,0,0,0,0,0,0},
                {0,1,1,0,0,1,0,1,1,0},
                {0,0,0,0,1,0,0,0,0,0},
                {1,0,1,0,1,0,1,0,0,0},
                {1,0,1,1,0,0,0,1,1,1},
                {0,0,0,0,0,0,0,1,1,0}
        };

        System.out.println("Solving the maze returns "+findPath(maze, new Coords(0,9), new Coords(9,0)));
    }
}
