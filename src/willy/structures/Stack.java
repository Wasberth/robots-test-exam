/*
 * García Piña Wilberth David
 * 2BM1
 * Inteligencia artificial
 * 22/04/2022
*/
package willy.structures;

import willy.util.__;
import willy.structures.nodes.StackNode;

/**
 *
 * @author Willy
 * @param <T> El tipo de variables que se van a guardar en la pila
 */
public class Stack<T> {

    private __<StackNode<T>> top;
    private int counter;

    public Stack() {
        this.top = null;
        this.counter = 0;
    }

    public Stack(final T t) {
        this.top = new __<>(new StackNode<>(t));
        this.counter = 1;
    }

    public void push(final T t) {
        final StackNode<T> newNode = new StackNode(t, this.top);
        this.top = new __<>(newNode);
        this.counter++;
    }

    public T pop() {
        if (this.counter == 0) {
            return null;
        }

        final __<StackNode<T>> node = top;
        this.top = this.top.__get().getPrevious();
        this.counter--;
        return node.__get().getValue();
    }
    
    public T peek(){
        if (this.counter == 0) {
            return null;
        }
        
        return top.__get().getValue();
    }

    public int getSize() {
        return counter;
    }

    public boolean isEmpty() {
        return counter == 0;
    }

    public void emptyfy() {
        T a;
        while (top != null) {
            a = this.pop();
            System.out.println("Desapilando: " + a);
        }
    }

    @Override
    public String toString() {
        String s = "";
        __<StackNode<T>> node = top;

        while (node != null && node.__get() != null) {
            s = node.__get().getValue() + " " + s;
            node = node.__get().getPrevious();
        }

        return "[ " + s + "]";
    }

}
