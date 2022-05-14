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
public class BiQueueNode<T> {

    private final T value;
    private __<BiQueueNode<T>> next;
    private __<BiQueueNode<T>> previous;

    public BiQueueNode(final T value) {
        this.value = value;
        this.next = null;
        this.previous = null;
    }

    public T getValue() {
        return value;
    }

    public __<BiQueueNode<T>> getNext() {
        return next;
    }

    public void setNext(__<BiQueueNode<T>> next) {
        this.next = next;
    }

    public __<BiQueueNode<T>> getPrevious() {
        return previous;
    }

    public void setPrevious(__<BiQueueNode<T>> previous) {
        this.previous = previous;
    }

    public String nextToString(){
        String sn = null;
        if (next != null) {
            sn = next.__get().nextToString();
        }
        return "BiQueueNode{" + "value=" + value + ", next=" + sn + '}';
    }
    
    public String prevToString(){
        String sp = null;
        if (previous != null) {
            sp = previous.__get().prevToString();
        }
        return "BiQueueNode{" + "value=" + value + ", previous=" + sp + '}';
    }
    
    @Override
    public String toString() {
        String sp = null;
        String sn = null;
        
        if (previous != null) {
            sp = previous.__get().prevToString();
        }
        
        if (next != null) {
            sn = next.__get().nextToString();
        }
        
        return "BiQueueNode{" + "value=" + value +  ", prev=" + sp + ", next=" + sn + '}';
    }

}
