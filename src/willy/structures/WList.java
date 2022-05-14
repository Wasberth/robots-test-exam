/*
 * García Piña Wilberth David
 * 2BM1
 * Inteligencia artificial
 * 28/04/2022
 */
package willy.structures;

import java.util.Comparator;

/**
 *
 * @author Willy
 * @param <T> El tipo que se va a guardar en la lista
 */
public interface WList<T> {

    public int size();

    public void pushFirst(T t);
    public void pushLast(T t);
    public void push(T t, int n);

    public T popFirst();
    public T popLast();
    public T pop(int i);

    public default void setFirst(T t) {
        if(size() != 0) popFirst();
        pushFirst(t);
    }
    public default void setLast(T t) {
        if(size() != 0) popLast();
        pushLast(t);
    }
    public default void set(T t, int n) {
        if (n == size()) {
            pushLast(t);
            return;
        }
        
        pop(n);
        push(t, n);
    }
    
    public T getFirst();
    public T getLast();
    public T get(int i);

    public boolean isEmpty();
    
    public default <U extends WList<T>> U copy(){
        return copy(0, size());
    }
    
    public default <U extends WList<T>> U copy(int right){
        return copy(0, right);
    }
    
    public <U extends WList<T>> U copy(int left, int right);
    
    public default void selectionSort(Comparator<? super T> c){
        int selected;

        for (int i = 0; i < size() - 1; i++) { // Para todos los elementos del array
            selected = i;

            for (int j = i + 1; j < size(); j++) {// Se busca todos los elementos que le siguen
                if (c.compare(get(i), get(j)) > 0) {
                    selected = j; // Y se elige el más pequeño
                }
            }
            final T temp = get(i); // Se respalda
            set(get(selected), i); // Se asigna al que se respaldó
            set(temp, selected); // Se asigna el respaldo
        }
    }
    
    public default void bubbleSort(Comparator<? super T> c){
        for (int i = 0; i < size() - 1; i++) {
            for (int j = 0; j < size() - i - 1; j++) {
                if (c.compare(get(j), get(j + 1)) > 0) {
                    final T temp = get(j);
                    set(get(j + 1), j);
                    set(temp, j + 1);
                }
            }
        }
    }
    
    public default void insertionSort(Comparator<? super T> c){
        for (int i = 1; i < size(); i++) {
            int j = i - 1;
            final T temp = get(i);

            while (j >= 0 && c.compare(get(j), temp) > 0) {
                set(get(j), j + 1);
                j--;
            }

            set(temp, j + 1);
        }
    }
    
    public default void merge(final int l, final int m, final int r, Comparator<? super T> c) { // 113n + 23
        int i, j, k;
        int n1 = m - l + 1;
        int n2 = r - m;

        /* create temp arrays */
        WList<T> Lar = copy(l, m + 1);
        WList<T> Rar = copy(m + 1, r + 1);

        /* Merge the temp arrays back into arr[l..r]*/
        i = 0; // Initial index of first subarray
        j = 0; // Initial index of second subarray
        k = l; // Initial index of merged subarray
        while (i < n1 && j < n2) {
            if(c.compare(Rar.get(j), Lar.get(i)) > 0){ // if (L[i], R[j])
                set(Lar.get(i++), k++); // arr[k] = L[i];
                continue;
            }

            set(Rar.get(j++), k++); // arr[k] = R[j];
        }

        /* Copy the remaining elements of L[], if there are any */
        while (i < n1) {
            set(Lar.get(i++), k++); // arr[k] = L[i];
        }

        /* Copy the remaining elements of R[], if there are any */
        while (j < n2) {
            set(Rar.get(j++), k++);
        }
    }
    
    public default void sortingAndMerging(final int l, final int r, Comparator<? super T> c) { // (113n + 29)(2^n)
        // T(n) = (2^n) (6 + T(merge))
        if (l >= r) {
            return;
        }

        // Same as (l + r) / 2, but avoids overflow for
        // large l and h
        int m = l + (r - l) / 2;

        // Sort first and second halves
        sortingAndMerging(l, m, c);
        sortingAndMerging(m + 1, r, c);

        merge(l, m, r, c);
    }
    
    public default void mergeSort(Comparator<? super T> c){
        sortingAndMerging(0, size() - 1, c);
    };
    
    public default void clear(){
        while(!isEmpty()){
            popFirst();
        }
    }
    
    public default void add(WList<T> list, int start, int end){
        if (end > list.size()) {
            throw new IllegalArgumentException("El índice final debe ser menor o igual al tamaño de la lista");
        }
        
        for (int i = start; i < end; i++) {
            this.pushLast(list.get(i));
        }
    }
    
    public default void add(WList<T> list, int end){
        add(list, 0, end);
    }
    
    public default void add(WList<T> list){
        add(list, 0, list.size());
    }
    
    public default int indexOf(T t){
        for (int i = 0; i < size(); i++) {
            if (t.equals(get(i))) {
                return i;
            }
        }
        
        return -1;
    }
}
