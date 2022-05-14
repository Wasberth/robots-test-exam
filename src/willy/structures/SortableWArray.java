/*
 * García Piña Wilberth David
 * 2BM1
 * Inteligencia artificial
 * 22/04/2022
*/
package willy.structures;

import willy.structures.nodes.Sortable;

/**
 *
 * @author Willy
 * @param <T> El tipo de la variable que va a hacer de array, debe ser Sortable
 */
public class SortableWArray<T extends Sortable> extends WArray<T> {

    private boolean order = Sortable.SMALLER_FIRST;
    private boolean sorted = false;

    public SortableWArray(Class<T> c, int size) {
        super(c, size);
    }

    public void setOrder(boolean order) {
        this.order = order;
    }

    public boolean isSorted() {
        return sorted;
    }

    public void startSelectionSort() { // T(n) = 1 + 
        int selected;

        for (int i = 0; i < getSize() - 1; i++) { // Para todos los elementos del array
            selected = i;

            for (int j = i + 1; j < getSize(); j++) {// Se busca todos los elementos que le siguen
                if (get(j).goesFirst(get(i), order)) {
                    selected = j; // Y se elige el más pequeño
                }
            }
            final T temp = get(i); // Se respalda
            set(i, get(selected)); // Se asigna al que se respaldó
            set(selected, temp); // Se asigna el respaldo
        }

        sorted = true;
    }

    public void startBubbleSort() {
        for (int i = 0; i < getSize() - 1; i++) {// Para todos los elementos del array
            for (int j = 0; j < getSize() - i - 1; j++) { // Se compara con todos los demás
                if (get(j + 1).goesFirst(get(j), order)) { // Y si se cumple la condición
                    final T temp = get(j); // Se cambian
                    set(j, get(j + 1));
                    set(j + 1, temp);
                }
            }
        }
        sorted = true;
    }

    public void startInsertionSort() {
        for (int i = 1; i < getSize(); i++) { // Para todos los elementos del array, excepto el primero
            int j = i - 1;
            final T temp = get(i);

            while (j >= 0 && temp.goesFirst(get(j), order)) {
                set(j + 1, get(j));
                j--;
            }

            set(j + 1, temp);
        }
        sorted = true;
    }

    private void merge(final int l, final int m, final int r) { // 113n + 23
        int i, j, k;
        int n1 = m - l + 1;
        int n2 = r - m;

        /* create temp arrays */
        WArray<T> Lar = new WArray(c, n1);
        WArray<T> Rar = new WArray(c, n2);

        /* Copy data to temp arrays Lar[] and Rar[] */
        for (i = 0; i < n1; i++) {
            Lar.set(i, get(l + i)); // Lar[i] = arr[l + i]
        }
        for (j = 0; j < n2; j++) {
            Rar.set(j, get(m + 1 + j)); // Rar[j] = arr[m + 1 + j];
        }

        /* Merge the temp arrays back into arr[l..r]*/
        i = 0; // Initial index of first subarray
        j = 0; // Initial index of second subarray
        k = l; // Initial index of merged subarray
        while (i < n1 && j < n2) {
            if (Lar.get(i).goesFirst(Rar.get(j), order)) { // if (L[i], R[j])
                set(k++, Lar.get(i++)); // arr[k] = L[i];
                continue;
            }

            set(k++, Rar.get(j++)); // arr[k] = R[j];
        }

        /* Copy the remaining elements of L[], if there are any */
        while (i < n1) {
            set(k++, Lar.get(i++)); // arr[k] = L[i];
        }

        /* Copy the remaining elements of R[], if there are any */
        while (j < n2) {
            set(k++, Rar.get(j++));
        }
    }

    private void mergeSort(final int l, final int r) { // (113n + 29)(2^n)
        // T(n) = (2^n) (6 + T(merge))
        if (l >= r) {
            return;
        }

        // Same as (l + r) / 2, but avoids overflow for
        // large l and h
        int m = l + (r - l) / 2;

        // Sort first and second halves
        mergeSort(l, m);
        mergeSort(m + 1, r);

        merge(l, m, r);
    }

    public void startMergeSort() { // (113n + 29)(2^n) + 1
        // T(n) = T(mergeSort) + 1
        mergeSort(0, getSize() - 1);
        sorted = true;
    }

    public int startSecuentialSearch(T searched) { // 3 + 15n
        // T(n) = 3 + n((ds) + g + 2)

        for (int i = 0; i < getSize(); i++) {
            if (get(i).compareTo(searched) != 0) {
                continue; // if(array[i] != searched)
            }
            return i;
        }

        return -1;
    }

    public int binarySearch(T searched, int i, int j) { // 8 + 17log(n)
        if (!sorted) {
            throw new IllegalStateException("El array tiene que estar ordenado antes de usar la búsqueda binaria");
        }

        // 8 + (8 + 2g + 2ds)log(n)
        int m;

        while (i < j) {
            m = i + (j - i) / 2;
            System.out.println("TESTING: " + i + " " + m + " " + j);

            if (get(m).goesFirst(searched, order)) {
                i = m + 1;
                continue;
            }

            j = m;
        }

        System.out.println("i: " + i + " j: " + j);

        if (i != j) {
            return -1;
        }

        if (get(i).compareTo(searched) == 0) {
            return i;
        }

        return -1;
    }
    
    public int startBinarySearch(T searched){
        int i = 0, j = getSize() - 1;
        
        return binarySearch(searched, i, j);
    }

}
