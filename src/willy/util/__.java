/*
 * García Piña Wilberth David
 * 2BM1
 * Inteligencia artificial
 * 06/04/2022
 */
package willy.util;

/**
 *
 * Esta clase sirve como un "apuntador" por wrappers
 * 
 * @author Willy
 * @param <T> El tipo al que la clase "apunta"
 */
public class __<T> {

    private T ref;

    public __(T ref) {
        this.ref = ref;
    }
    
    public T __get(){
        return ref;
    }
    
    public void __set(T ref){
        this.ref = ref;
    }

    @Override
    public String toString() {
        return "__{" + "ref=" + ref + '}';
    }
    
    
    
}
