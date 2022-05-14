/*
 * García Piña Wilberth David
 * 2BM1
 * Inteligencia artificial
 * 22/04/2022
 */
package willy.structures;

/**
 *
 * @author Willy
 * @param <T> El tipo de variables que se van a guardar en la pila
 */
public class StackArray<T> {

    private final WArray<T> array;
    private int counter = 0;

    public StackArray(Class<T> c, int size) {
        array = new WArray<>(c, size);
    }

    public void push(T t) {
        array.set(counter, t);
        this.counter++;
    }

    public T pop() {
        if (this.counter == 0) {
            return null;
        }

        this.counter--;
        final T r = array.get(counter);
        array.set(counter, null);
        return r;

    }
    
    public T peek(){
        if (this.counter == 0) {
            return null;
        }
        
        return array.get(counter - 1);
    }

    public int getMaxSize() {
        return array.getSize();
    }

    public int getSize() {
        return counter;
    }

    public boolean isEmpty() {
        return counter == 0;
    }

    public boolean isFull() {
        return counter == array.getSize();
    }

    public void emptyfy() {
        T a;

        while (counter > 0) {
            a = this.pop();
            System.out.println("Desapilando: " + a);
        }
    }

    @Override
    public String toString() {
        return array.toString();
    }

}
