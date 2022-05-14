/*
 * García Piña Wilberth David
 * 2BM1
 * Inteligencia artificial
 * 06/04/2022
*/
package willy.structures.nodes;

/**
 *
 * @author Willy
 * @param <T> Es el tipo hacia el cual se va a comparar
 */
public abstract class Sortable<T> implements Comparable<T> {

    public static final boolean SMALLER_FIRST = true;
    public static final boolean LARGER_FIRST = false;

    /**
     *
     * @param s Es el valor al que va a ser comparado
     * @param order Indica si se ordena de menor a mayor, usar las constantes
     * SMALLER_FIRST o LARGER_FIRST
     * @return Retorna si esta instancia debería ir antes que la otra con el
     * orden indicado
     */
    public boolean goesFirst(final T s, final boolean order) {
        return (order && this.compareTo(s) < 0) || (!order && this.compareTo(s) > 0);
    }

}
