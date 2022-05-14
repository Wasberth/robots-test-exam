/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package willy.structures;

import willy.structures.nodes.DoubleLinkedNode;
import willy.util.__;

/**
 *
 * @author Willy
 * @param <T> El tipo que se va a guardar
 */
public class CircularDoubleLinkedList<T> implements WList<T> {

    private __<DoubleLinkedNode<T>> last;
    private int size;

    public CircularDoubleLinkedList() {
        size = 0;
        last = new __(null);
    }

    private void forwards(int n) {
        for (int i = 0; i < n; i++) {
            last = last.__get().getNext();
        }
    }

    private void backwards(int n) {
        for (int i = 0; i < n; i++) {
            last = last.__get().getPrev();
        }
    }

    private void fastest(int n) {
        if (size == 0) {
            return;
        }
        
        n = n % size;

        if (n == 0) {
            return;
        }

        if (n > 0) {
            if (n < size / 2) {
                forwards(n);
                return;
            }
            backwards(size - n);
            return;
        }

        if (-n < size / 2) {
            backwards(-n);
            return;
        }
        forwards(size + n);

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void pushFirst(T t) {
        DoubleLinkedNode<T> node = new DoubleLinkedNode(t);
        __<DoubleLinkedNode<T>> nodeP = new __(node);

        if (size == 0) {
            node.setNext(nodeP);
            node.setPrev(nodeP);

            last = nodeP;

            size++;
            return;
        }

        node.setNext(last.__get().getNext());
        node.setPrev(last);

        last.__get().getNext().__get().setPrev(nodeP);
        last.__get().setNext(nodeP);

        size++;
    }

    @Override
    public void pushLast(T t) {
        pushFirst(t);
        last = last.__get().getNext();
    }

    @Override
    public void push(T t, int n) {
        if (n < 0 || n > size) {
            throw new IndexOutOfBoundsException("El índice (" + n + ") debe ser mayor a 0 y menor o igual al tamaño (" + size + ")");
        }

        fastest(n);
        pushFirst(t);
        fastest(-n);
    }

    @Override
    public T popFirst() {
        final T popped = last.__get().getNext().__get().getValue();

        if (size == 1) {
            last = new __(null);
            size--;
            return popped;
        }

        last.__get().getNext().__get().getNext().__get().setPrev(last);
        last.__get().setNext(last.__get().getNext().__get().getNext());
        size--;

        return popped;
    }

    @Override
    public T popLast() {
        fastest(-1);
        T popped = popFirst();
//        fastest(1);
//        System.out.println("debug: " + this);
        
        return popped;
    }

    @Override
    public T pop(int n) {
        fastest(n);
        T popped = popFirst();
        fastest(-n);
        return popped;
    }

    @Override
    public T getFirst() {
        return last.__get().getNext().__get().getValue();
    }

    @Override
    public T getLast() {
        return last.__get().getValue();
    }

    @Override
    public T get(int n) {
        fastest(n);
        T gotten = getFirst();
        fastest(-n);
        
        return gotten;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public CircularDoubleLinkedList<T> copy(int left, int right) {
        CircularDoubleLinkedList<T> c = new CircularDoubleLinkedList<>();
        
        for (int i = left; i < right; i++) {
            c.pushLast(get(i));
        }
        
        return c;
    }

    @Override
    public String toString() {
        String s = "[";

        __<DoubleLinkedNode<T>> first = last;

        for (int i = 0; i < size(); i++) {
            first = first.__get().getNext();

            s = s + first.__get().getValue();

            if (i != size() - 1) {
                s = s + ", ";
            }
        }

        return s + "]";
    }

}
