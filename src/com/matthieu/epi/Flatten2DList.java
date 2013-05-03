package com.matthieu.epi;

import java.util.*;

public class Flatten2DList implements Solution {
    public static class List2DIterator<T> implements Iterator<T> {
        List<List<T>> masterList;
        Iterator<List<T>> topIterator;
        Iterator<T> listIterator;
        Iterator<T> nextIterator;

        public List2DIterator(List<List<T>> list) {
            masterList = list;
            topIterator = list.iterator();
            moveToNextList();
        }

        private void moveToNextList() {
            if (nextIterator != null) {
                listIterator=nextIterator;
                nextIterator=null;
                return;
            }

            List<T> currentList=null;
            while (((currentList==null) || (currentList.size()==0)) && (topIterator.hasNext())) {
                currentList = topIterator.next();
            }
            if ((currentList==null) || (currentList.size()==0)) {
                listIterator=null;
                return;
            }

            listIterator = currentList.iterator();
        }

        private void findNextList() {
            if (nextIterator!=null)
                return;

            List<T> currentList=null;
            while (((currentList==null) || (currentList.size()==0)) && (topIterator.hasNext())) {
                currentList = topIterator.next();
            }
            if ((currentList==null) || (currentList.size()==0)) {
                nextIterator=null;
            }
            else {
                nextIterator = currentList.iterator();
            }
        }

        @Override
        public boolean hasNext() {
            if (listIterator==null)
                return false;
            if (listIterator.hasNext())
                return true;
            findNextList();
            if (nextIterator==null)
                return false;
            return nextIterator.hasNext();
        }

        @Override
        public T next() {
            if (listIterator==null)
                throw new NoSuchElementException();
            if (listIterator.hasNext())
                return listIterator.next();
            moveToNextList();
            if (listIterator==null)
                throw new NoSuchElementException();
            if (listIterator.hasNext())
                return listIterator.next();
            throw new NoSuchElementException();
        }

        @Override
        public void remove() {
            if (listIterator==null)
                return;
            listIterator.remove();
        }
    }

    @Override
    public void solveProblem() {
        List<List<Integer>> list = new LinkedList<List<Integer>>();
        list.add(new ArrayList<Integer>());
        list.add(Arrays.asList(1,2,3,4));
        list.add(new ArrayList<Integer>());
        list.add(Arrays.asList(5,6,7));
        list.add(new ArrayList<Integer>());

        Iterator<Integer> iter = new List2DIterator(list);
        while (iter.hasNext()) {
            System.out.println("Next in list is "+iter.next());
        }

    }
}
