package org.example;

import com.google.common.collect.Range;

public class Main {
    public static void main(String[] args) {

        Range<Integer> closed = Range.closed(1, 12);
        Range<Integer> open = Range.open(1, 22);
        System.out.println(closed.contains(2));
        System.out.println(open.contains(23));
        System.out.println(closed.span(open));
        System.out.println(closed.intersection(open));
    }
}