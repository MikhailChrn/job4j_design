package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Balancer {
    private static List<ArrayList<Integer>> nodes;
    private static Iterator<Integer> source;

    public Balancer(List<ArrayList<Integer>> nodes, Iterator<Integer> source) {
        nodes = nodes;
        source = source;
    }

    public static void split(List<ArrayList<Integer>> nodes, Iterator<Integer> source) {
        if (nodes.size() == 0) {
            throw new NoSuchElementException("Empty list of nodes");
        }

        int index = 0;
        while (source.hasNext()) {
            if (index == nodes.size()) {
                index = 0;
            }
            nodes.get(index++).add(source.next());
        }
    }
}
