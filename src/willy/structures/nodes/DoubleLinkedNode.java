/*
 * García Piña Wilberth David
 * 2BM1
 * Inteligencia artificial
 * 02/05/2022
 */
package willy.structures.nodes;

import willy.util.__;

/**
 *
 * @author Willy
 * @param <T> El tipo que se va a guardar en la pila
 *
 */
public class DoubleLinkedNode<T> {

    private final T value;
    private __<DoubleLinkedNode<T>> next;
    private __<DoubleLinkedNode<T>> prev;

    public DoubleLinkedNode(final T value) {
        this.value = value;
        this.next = new __<>(null);
        this.prev = new __<>(null);
    }

    public T getValue() {
        return value;
    }

    public __<DoubleLinkedNode<T>> getNext() {
        return next;
    }

    public void setNext(__<DoubleLinkedNode<T>> next) {
        this.next = next;
    }

    public __<DoubleLinkedNode<T>> getPrev() {
        return prev;
    }

    public void setPrev(__<DoubleLinkedNode<T>> prev) {
        this.prev = prev;
    }
    
    public String prevString(){
        //System.out.println("c: " + value);
        if (prev != null && prev.__get() != null) {
            //System.out.println("previ: " + prev.__get().value);
        }
        
        String ps = "null";
        
        if (prev != null && prev.__get() != null) {
            ps = prev.__get().prevString();
        }
        
        return "DoubleLinkedNode{" + "value=" + value + ", prev=" + ps + '}';
    }
    
    public String nextString(){
        String ns = "null";
        
        if (next != null && next.__get() != null) {
            ns = next.__get().nextString();
        }
        
        return "DoubleLinkedNode{" + "value=" + value + ", next=" + ns + '}';
    }

    @Override
    public String toString() {
        String ps = "null", ns = "null";
        
        if (prev != null && prev.__get() != null) {
            ps = prev.__get().prevString();
        }
        
        if (next != null && next.__get() != null) {
            ns = next.__get().nextString();
        }
        
        return "DoubleLinkedNode{" + "prev=" + ps + ", value=" + value + ", next=" + ns + '}';
    }

}