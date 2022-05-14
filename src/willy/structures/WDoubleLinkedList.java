/*
 * García Piña Wilberth David
 * 2BM1
 * Inteligencia artificial
 * 02/05/2022
 */
package willy.structures;

import willy.structures.nodes.DoubleLinkedNode;
import willy.util.__;

/**
 *
 * @author Willy
 * @param <T> El tipo que se va a guardar en la lista
 */
public class WDoubleLinkedList<T> implements WList<T> {

    private __<DoubleLinkedNode<T>> first;
    int size;

    public WDoubleLinkedList() {
        size = 0;
        first = new __<>(null);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void pushFirst(T t) {
        DoubleLinkedNode<T> nn = new DoubleLinkedNode<>(t);
        nn.setNext(first);

        __<DoubleLinkedNode<T>> nnp = new __<>(nn);

        if (first.__get() != null) {
            first.__get().setPrev(nnp);
        }

        first = nnp;
        size++;
    }

    @Override
    public void pushLast(T t) {
        if (size == 0) {
            push(t, 0);
            return;
        }

        __<DoubleLinkedNode<T>> last = first;
        
        for (int i = 0; i < size - 1; i++) {
            last = last.__get().getNext();
        }
        
        DoubleLinkedNode<T> nl = new DoubleLinkedNode<>(t);
        __<DoubleLinkedNode<T>> nlp = new __<>(nl);
        nl.setPrev(last);
        last.__get().setNext(nlp);

        size++;
    }

    @Override
    public void push(final T t, final int n) {
        if (n < 0 || n >= size) {
            throw new IndexOutOfBoundsException("El índice (" + n + ") debe ser mayor a 0 y menor al tamaño (" + size + ")");
        }

        if (n == 0) {
            pushFirst(t);
            return;
        }

        if (n == size) {
            pushLast(t);
            return;
        }

        __<DoubleLinkedNode<T>> node = first;
        DoubleLinkedNode<T> nn = new DoubleLinkedNode<>(t);
        __<DoubleLinkedNode<T>> nnp = new __<>(nn);

        for (int i = 0; i < n; i++) {
            node = node.__get().getNext();
        }

        nn.setNext(node);
        nn.setPrev(node.__get().getPrev());
        node.__get().getPrev().__get().setNext(nnp);
        node.__get().setPrev(nnp);

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

        __<DoubleLinkedNode<T>> node = first;

        for (int i = 0; i < n; i++) {
            node = node.__get().getNext();
        }

        return node.__get().getValue();
    }

    @Override
    public T popFirst() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("La lista está vacia");
        }
        
        T popped = first.__get().getValue();
        
        if (size == 1) {
            first = new __(null);
            size--;
            return popped;
        }
        
        first = first.__get().getNext();
        first.__get().setPrev(new __(null));
        
        size--;
        
        return popped;
    }

    @Override
    public T popLast() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("La lista está vacia");
        }
        
        T popped;
        
        if (size == 1) {
            popped = first.__get().getValue();
            first = new __(null);
            size--;
            return popped;
        }
        
        __<DoubleLinkedNode<T>> last = first;
        
        for (int i = 0; i < size - 1; i++) {
            last = last.__get().getNext();
        }
        
        popped = last.__get().getValue();
        
        last.__get().getPrev().__get().setNext(new __(null));
        size--;
        
        return popped;
    }

    @Override
    public T pop(int n) {
        if (size == 0) {
            throw new IndexOutOfBoundsException("La lista está vacia");
        }

        if (n < 0 || n >= size) {
            throw new IndexOutOfBoundsException("El índice (" + n + ") debe ser mayor a 0 y menor al tamaño (" + size + ")");
        }
        
        if (n == 0) {
            return popFirst();
        }
        
        if (n == size - 1) {
            return popLast();
        }

        T popped;
        __<DoubleLinkedNode<T>> node = first;

        for (int i = 0; i < n; i++) {
            node = node.__get().getNext();
        }

        popped = node.__get().getValue();

        if (size == 1) {
            first = new __(null);
            size--;
            return popped;
        }
        
        if (node.__get().getPrev().__get() != null) {
            node.__get().getPrev().__get().setNext(node.__get().getNext());
        }
        
        if (node.__get().getNext().__get() != null) {
            node.__get().getNext().__get().setPrev(node.__get().getPrev());
        }
        
        //node.__set(node.__get().getNext().__get());

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

        __<DoubleLinkedNode<T>> node = first;
        while (node.__get() != null) {
            //System.out.println(node);
            s = s + node.__get().getValue() + (node.__get().getNext().__get() == null ? "" : ", ");
            node = node.__get().getNext();
        }

        return s + "]";
    }

    @Override
    public WDoubleLinkedList<T> copy(int left, int right) {
        final WDoubleLinkedList<T> list = new WDoubleLinkedList<>();
        
        for (int i = left; i < right; i++) {
            list.pushLast(get(i));
        }
        
        return list;
    }

}
