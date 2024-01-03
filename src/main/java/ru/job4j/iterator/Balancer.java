package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Balancer {
    private static List<ArrayList<Integer>> nodes;
    private static CyclicIterator<ArrayList<Integer>> cyclicNodeIterator;
    private static Iterator<Integer> source;

    public Balancer(List<ArrayList<Integer>> nodes, Iterator<Integer> source) {
        nodes = nodes;
        source = source;
    }

    public static void split(List<ArrayList<Integer>> nodes, Iterator<Integer> source) {
        if (nodes.size() == 0) {
            throw new NoSuchElementException("Empty list of nodes");
        }
        cyclicNodeIterator = new CyclicIterator<>(nodes);
        while (source.hasNext()) {
            cyclicNodeIterator.next().add(source.next());
        }
    }
}
