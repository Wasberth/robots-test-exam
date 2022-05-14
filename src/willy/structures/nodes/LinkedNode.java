/*
 * García Piña Wilberth David
 * 2BM1
 * Inteligencia artificial
 * 28/04/2022
 */
package willy.structures.nodes;

import willy.util.__;

/**
 *
 * @author Willy
 * @param <T> El tipo que se va a guardar en la pila
 *
 */
public class LinkedNode<T> {

    private final T value;
    private __<LinkedNode<T>> next;

    public LinkedNode(final T value) {
        this.value = value;
        this.next = new __<>(null);
    }

    public T getValue() {
        return value;
    }

    public __<LinkedNode<T>> getNext() {
        return next;
    }

    public void setNext(__<LinkedNode<T>> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "LinkedNode{" + "value=" + value + ", next=" + next + '}';
    }

}