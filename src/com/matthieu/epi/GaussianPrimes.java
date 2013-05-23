package com.matthieu.epi;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.ComplexExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class GaussianPrimes implements Solution {
    private static class Complex implements Comparable<Complex> {
        int real;
        int imag;

        public Complex (int a, int b) {
            real=a;
            imag=b;
        }

        @Override
        public String toString() {
            return "("+real+","+imag+")";
        }

        public float norm() {
            return (float) Math.sqrt(real*real + imag*imag);
        }

        @Override
        public int compareTo(Complex complex) {
            if (norm() != complex.norm()) {
                return Float.compare(norm(), complex.norm());
            } if (real != complex.real) {
                return Integer.compare(real, complex.real);
            }
            return Integer.compare(imag, complex.imag);
        }

        public boolean isUnit() {
            if ((real==0) && (imag==1))
                return true;
            if ((real==0) && (imag==-1))
                return true;
            if ((real==1) && (imag==0))
                return true;
            if ((real==-1) && (imag==0))
                return true;
            return false;
        }

        @Override
        public boolean equals(Object o) {
            if (o==null)
                return false;
            if (!(o instanceof Complex))
                return false;
            Complex complex = (Complex) o;
            return (real==complex.real) && (imag==complex.imag);
        }

        @Override
        public int hashCode() {
            int prime=47;
            int hash= prime * real;
            hash = prime * hash + prime*imag;
            return hash;
        }

        public Complex multiply(Complex complex) {
            return new Complex(real*complex.real - imag*complex.imag, real*complex.imag + imag*complex.real);
        }
    }

    public static List<Complex> getGaussianPrimes(int n) {
        PriorityQueue<Complex> candidates = new PriorityQueue<Complex>();
        ArrayList<Complex> res = new ArrayList<Complex>();

        for (int i=-n; i<=n; i++) {
            for (int j=-n; j<=n; j++) {
                candidates.add(new Complex(i,j));
            }
        }

        while(candidates.size()>0) {
            Complex next = candidates.poll();
            if (next.isUnit())
                continue;
            if (next.norm() == 0)
                continue;

            res.add(next);

            int maxNorm = (int) (n / next.norm())+1; // check this!
            for (int i=-maxNorm; i<=maxNorm; i++) {
                for (int j=-maxNorm; j<=maxNorm; j++) {
                    Complex multiplier = new Complex(i,j);
                    if (multiplier.isUnit())
                        continue;
                    candidates.remove(next.multiply(multiplier));
                }
            }
        }
        return res;
    }

    @Override
    public void solveProblem() {
        System.out.println("Gaussian complex with 5:" + getGaussianPrimes(5));
    }
}
