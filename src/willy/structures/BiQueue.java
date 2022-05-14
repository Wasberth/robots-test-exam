/*
 * García Piña Wilberth David
 * 2BM1
 * Inteligencia artificial
 * 22/04/2022
*/
package willy.structures;

import willy.structures.nodes.BiQueueNode;
import willy.util.__;

/**
 *
 * @author Willy
 * @param <T> El tipo que se va a guardar en la pila
 */
public class BiQueue<T> {

    private __<BiQueueNode<T>> next;
    private __<BiQueueNode<T>> last;
    private int counter;

    public BiQueue() {
        this.next = null;
        this.last = null;
        this.counter = 0;
    }

    public void addEnd(final T t) {
        __<BiQueueNode<T>> nl = new __<>(new BiQueueNode<>(t));
        nl.__get().setPrevious(last);
        
        if (counter == 0) {
            next = nl;
            last = next;
            counter++;
            return;
        }

        last.__get().setNext(nl);
        last = nl;
        counter++;
    }
    
    public void addStart(final T t) {
        __<BiQueueNode<T>> nn = new __<>(new BiQueueNode<>(t));
        nn.__get().setNext(next);
        
        if (counter == 0) {
            next = nn;
            last = next;
            counter++;
            return;
        }

        next.__get().setPrevious(nn);
        next = nn;
        counter++;
    }

    public T removeStart() {
        if (counter == 0) {
            return null;
        }

        final __<BiQueueNode<T>> node = next;
        this.next = this.next.__get().getNext();
        if (this.next != null) {
            this.next.__get().setPrevious(null);
        }
        this.counter--;
        
        if (counter == 0) {
            next = null;
            last = null;
        }
        return node.__get().getValue();
    }
    
    public T removeEnd() {
        if (counter == 0) {
            return null;
        }

        final __<BiQueueNode<T>> node = last;
        this.last = this.last.__get().getPrevious();
        if (this.last != null) {
            this.last.__get().setNext(null);
        }
        this.counter--;
        
        if (counter == 0) {
            next = null;
            last = null;
        }
        return node.__get().getValue();
    }

    public T peekStart() {
        if (this.counter == 0) {
            return null;
        }

        return next.__get().getValue();
    }
    
    public T peekEnd() {
        if (this.counter == 0) {
            return null;
        }

        return last.__get().getValue();
    }
    
    public int getSize() {
        return counter;
    }

    public boolean isEmpty() {
        return counter == 0;
    }
    
    public void emptyfyStart() {
        T a;
        while (counter > 0) {
            a = this.removeStart();
            System.out.println("Eliminando: " + a);
        }
    }
    
    public void emptyfyEnd() {
        T a;
        while (counter > 0) {
            a = this.removeEnd();
            System.out.println("Eliminando: " + a);
        }
    }

    @Override
    public String toString() {        
        String s = "";
        __<BiQueueNode<T>> node = next;

        while (node != null && node.__get() != null) {
            s = s + node.__get().getValue() + " ";
            node = node.__get().getNext();
        }

        return "[ " + s + "]";
    }
    
}
