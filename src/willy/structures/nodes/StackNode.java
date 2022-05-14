/*
 * García Piña Wilberth David
 * 2BM1
 * Inteligencia artificial
 * 06/04/2022
 */
package willy.structures.nodes;

import willy.util.__;

/**
 * Nodo para la pila
 *
 * @author Willy
 * @param <T> Es el tipo de variable que se va a guardar en el nodo
 */
public class StackNode<T> {

    private final T value;
    private final __<StackNode<T>> previous;

    public StackNode(final T value, final __<StackNode<T>> previous) {
        this.value = value;
        this.previous = previous;
    }

    public StackNode(final T value) {
        this.value = value;
        this.previous = null;
    }

    public T getValue() {
        return value;
    }

    public __<StackNode<T>> getPrevious() {
        return previous;
    }

    @Override
    public String toString() {
        return "StackNode{" + "value=" + value + ", previous=" + previous + '}';
    }

}
