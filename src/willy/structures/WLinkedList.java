/*
 * García Piña Wilberth David
 * 2BM1
 * Inteligencia artificial
 * 28/04/2022
 */
package willy.structures;

import willy.structures.nodes.LinkedNode;
import willy.util.__;

/**
 *
 * @author Willy
 * @param <T> El tipo que se va a guardar en la lista
 */
public class WLinkedList<T> implements WList<T> {

    private __<LinkedNode<T>> first;
    int size;

    public WLinkedList() {
        size = 0;
        first = new __<>(null);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void pushFirst(T t) {
        LinkedNode<T> nn = new LinkedNode<>(t);
        nn.setNext(first);
        
        first = new __<>(nn);
        size++;
    }

    @Override
    public void pushLast(T t) {
        push(t, size);
    }

    @Override
    public void push(final T t, final int n) {
        if (n < 0 || n > size) {
            throw new IndexOutOfBoundsException("El índice (" + n + ") debe ser mayor a 0 y menor al tamaño (" + size + ")");
        }

        if (n == 0) {
            pushFirst(t);
            return;
        }

        __<LinkedNode<T>> prevNode = first;
        LinkedNode<T> nn = new LinkedNode<>(t);

        for (int i = 0; i < n - 1; i++) {
            prevNode = prevNode.__get().getNext();
        }

        if (prevNode.__get() == null) {
            nn.setNext(new __<>(null));
            prevNode.__set(nn);

            size++;
            return;
        }
        nn.setNext(prevNode.__get().getNext());
        prevNode.__get().setNext(new __<>(nn));

        size++;
    }

    @Override
    public T getFirst() {
        return first.__get().getValue();
    }

    @Override
    public T getLast() {
        return get(size - 1);
    }

    @Override
    public T get(int n) {
        //System.out.println("willy.structures.WLinkedList.get(" + n + ")");
        if (size == 0) {
            throw new IndexOutOfBoundsException("La lista está vacia");
        }
        
        if (n < 0 || n >= size) {
            throw new IndexOutOfBoundsException("El índice (" + n + ") debe ser mayor a 0 y menor al tamaño (" + size + ")");
        }
        
        __<LinkedNode<T>> node = first;
        
        for (int i = 0; i < n; i++) {
            node = node.__get().getNext();
        }
        
        return node.__get().getValue();
    }

    @Override
    public T popFirst() {
        return pop(0);
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
        __<LinkedNode<T>> node = first;
        
        for (int i = 0; i < n; i++) {
            node = node.__get().getNext();
        }
        
        popped = node.__get().getValue();
        
        node.__set(node.__get().getNext().__get());
        
        size--;
        return popped;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        String s = "[";

        __<LinkedNode<T>> node = first;
        while (node.__get() != null) {
            s = s + node.__get().getValue() + (node.__get().getNext().__get() == null ? "" : ", ");
            node = node.__get().getNext();
        }

        return s + "]";
    }
    
    @Override
    public WLinkedList<T> copy(int left, int right) {
        final WLinkedList<T> list = new WLinkedList<>();
        
        for (int i = left; i < right; i++) {
            list.pushLast(get(i));
        }
        
        return list;
    }

}
