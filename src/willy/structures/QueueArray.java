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
 * @param <T> El tipo de variables que se van a guardar en la cola
 */
public class QueueArray<T>{
    
    private final WArray<T> array;
    private int counter = 0;
    private int start = 0;
    private int end = 0;
    
    public QueueArray(Class<T> c, int size) {
        array = new WArray<>(c, size);
    }
    
    public void add(T t) {
        if (end == start && array.get(end) != null) {
            throw new IndexOutOfBoundsException();
        }
        
        array.set(end, t);
        this.counter++;
        increaseEnd();
    }

    public T remove() {
        if (this.counter == 0) {
            return null;
        }

        final T r = array.get(start);
        array.set(start, null);
        this.counter--;
        increaseStart();
        
        return r;

    }
    
    public T peek(){
        if (this.counter == 0) {
            return null;
        }
        
        return array.get(start);
    }
    
    private void increaseEnd(){
        this.end = (this.end + 1) % array.getSize();
    }
    
    private void increaseStart(){
        this.start = (this.start + 1) % array.getSize();
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
            a = this.remove();
            System.out.println("Desencolando: " + a);
        }
    }
    
    @Override
    public String toString() {
        return array.toString();
    }
    
}
