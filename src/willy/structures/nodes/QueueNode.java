/*
 * García Piña Wilberth David
 * 2BM1
 * Inteligencia artificial
 * 22/04/2022
 */
package willy.structures.nodes;

import willy.util.__;

/**
 *
 * @author Willy
 * @param <T> El tipo que se va a guardar en la pila
 *
 */
public class QueueNode<T> {

    private final T value;
    private __<QueueNode<T>> next;

    public QueueNode(final T value) {
        this.value = value;
        this.next = null;
    }

    public T getValue() {
        return value;
    }

    public __<QueueNode<T>> getNext() {
        return next;
    }

    public void setNext(__<QueueNode<T>> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "QueueNode{" + "value=" + value + ", next=" + next + '}';
    }

}
