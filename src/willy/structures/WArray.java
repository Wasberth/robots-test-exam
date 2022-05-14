/*
 * García Piña Wilberth David
 * 2BM1
 * Inteligencia artificial
 * 06/04/2022
*/
package willy.structures;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 *
 * @author Willy
 * @param <T> El tipo de la variable que va a hacer de array
 */
public class WArray<T> {

    protected Class<T> c;
    private T[] array;
    private int size;

    public WArray(Class<T> c, int size) {
        this.c = c;
        this.array = (T[]) Array.newInstance(c, size);
        this.size = size;
    }

    public T get(int i) {
        if (i >= size) {
            throw new ArrayIndexOutOfBoundsException(i);
        }
        return array[i];
    }

    public void set(int i, T val) {
        if (i >= size) {
            throw new ArrayIndexOutOfBoundsException(i);
        }
        array[i] = val;
    }

    public void realloc(int size) {
        final T[] newArray = (T[]) Array.newInstance(c, size);
        int j = size < this.size ? size : this.size;

        System.arraycopy(array, 0, newArray, 0, j);

        this.array = newArray;
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return Arrays.deepToString(array);
    }
}
