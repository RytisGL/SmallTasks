package org.example;

import com.google.common.collect.Range;
import com.google.common.primitives.Ints;

public class Main {
    public static void main(String[] args) {

        Range<Integer> closed = Range.closed(1, 12);
        Range<Integer> open = Range.open(1, 22);
        System.out.println(closed.contains(2));
        System.out.println(open.contains(23));
        System.out.println(closed.span(open));
        System.out.println(closed.intersection(open));
        int[] ints = {1,2,3,4,5,6};
        System.out.println(Ints.min(ints));
        System.out.println(Ints.max(ints));
        System.out.println(Ints.contains(ints, 1));
        Integer[] ints2 = new Integer[ints.length];
        Ints.asList(ints).toArray(ints2);
        System.out.println(ints2[1]);
    }
}