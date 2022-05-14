/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package willy.structures;

import willy.structures.nodes.LinkedNode;
import willy.util.__;

/**
 *
 * @author Willy
 * @param <T> El tipo que se va a guardar en la lista
 */
public class CircularLinkedList<T> implements WList<T> {

    __<LinkedNode<T>> last;
    int size;

    public CircularLinkedList() {
        last = new __(null);
        size = 0;
    }
    
    private void forwards(int n){
        for (int i = 0; i < n; i++) {
            last = last.__get().getNext();
        }
    }
    
    private void backwards(int n){
        for (int i = 0; i < size() - n; i++) {
            last = last.__get().getNext();
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void pushFirst(T t) {
        LinkedNode<T> node = new LinkedNode<>(t);
        __<LinkedNode<T>> nodeP = new __(node);

        if (size == 0) {
            last = nodeP;
            node.setNext(last);
            size++;
            return;
        }

        node.setNext(last.__get().getNext());
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

        forwards(n);
        pushFirst(t);
        backwards(n);
    }

    @Override
    public T popFirst() {
        final T popped = last.__get().getNext().__get().getValue();

        if (size == 1) {
            last = new __(null);
            size--;
            return popped;
        }

        last.__get().setNext(last.__get().getNext().__get().getNext());
        size--;

        return popped;
    }

    @Override
    public T popLast() {
        return pop(size - 1);
    }

    @Override
    public T pop(int n) {
        if (size == 0) {
            throw new IndexOutOfBoundsException("La lista está vacia");
        }

        if (n < 0 || n >= size) {
            throw new IndexOutOfBoundsException("El índice (" + n + ") debe ser mayor a 0 y menor al tamaño (" + size + ")");
        }
        
        T popped;

        forwards(n);
        popped = popFirst();
        backwards(n);

        return popped;
    }

    @Override
    public T getFirst() {
        return last.__get().getNext().__get().getValue();
    }

    @Override
    public T getLast() {
        return get(size - 1);
    }

    @Override
    public T get(int n) {
        if (size == 0) {
            throw new IndexOutOfBoundsException("La lista está vacia");
        }

        if (n < 0 || n >= size) {
            throw new IndexOutOfBoundsException("El índice (" + n + ") debe ser mayor a 0 y menor al tamaño (" + size + ")");
        }
        
        T value;
        forwards(n);
        value = getFirst();
        backwards(n);
        
        return value;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public CircularLinkedList<T> copy(int left, int right) {
        CircularLinkedList<T> c = new CircularLinkedList<>();
        
        for (int i = left; i < right; i++) {
            c.pushLast(get(i));
        }
        
        return c;
    }

    @Override
    public String toString() {
        String s = "[";

        __<LinkedNode<T>> first = last;

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
