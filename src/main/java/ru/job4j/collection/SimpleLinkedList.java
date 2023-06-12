package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements LinkedList<E> {

    private Node<E> head;
    private int size;
    private int modCount;

    @Override
    public void add(E value) {
        Node<E> newNode = new Node<>(value, null);
        modCount++;
        size++;
        if (head == null) {
            head = newNode;
            return;
        }
        Node<E> tail = head;
        while (tail.getNext() != null) {
            tail = tail.getNext();
        }
        tail.setNext(newNode);
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> point = head;
        int i = 0;
        while (i < index) {
            point = point.getNext();
            i++;
        }
        return point.getItem();
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> iNode = head;
            private int iteratorControler = modCount;
            @Override
            public boolean hasNext() {
                if (iteratorControler != modCount) {
                    throw new ConcurrentModificationException();
                }
                return iNode != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E item = iNode.getItem();
                iNode = iNode.getNext();
                return item;
            }
        };
    }

    private static class Node<E> {
        private E item;
        private Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }

        public Node<E> getNext() {
            return next;
        }

        public E getItem() {
            return item;
        }
    }
}
