/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package willy.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.function.UnaryOperator;
import willy.structures.WList;

/**
 *
 * @author Willy
 */
public class WRandom {

    private static final BigInteger MAX_INT = new BigInteger(String.valueOf(Integer.MAX_VALUE));

    private BigInteger seed = new BigInteger(String.valueOf(System.currentTimeMillis()));
    private UnaryOperator<BigInteger> algorithm = (BigInteger bi) -> {
        BigInteger result = bi.pow(37);
        //System.out.println(result);
        result = result.add(bi.pow(13));
        //System.out.println(result);
        result = result.add(bigInterize(17));
        //System.out.println(result);
        result = result.multiply(bigInterize(11).pow(3));
        //System.out.println(result);
        result = result.mod(bigInterize(2).pow(60).subtract(BigInteger.ONE));
        //System.out.println(result);

        return result;
    };

    public WRandom() {
    }

    public WRandom(long seed) {
        this.seed = new BigInteger(String.valueOf(seed));
    }

    public WRandom(UnaryOperator<BigInteger> algorithm) {
        this.algorithm = algorithm;
    }

    public WRandom(long seed, UnaryOperator<BigInteger> algorithm) {
        this.seed = new BigInteger(String.valueOf(seed));
        this.algorithm = algorithm;
    }

    /**
     *
     * Arma un algoritmo a través de los datos dados de la siguiente manera:
     *
     * nueva-semilla = (a1 * semilla^p1 + a2 * semilla^p2 + ... + an *
     * semilla^pn) mod modulus
     *
     * @param modulus El módulo aplicado al final de la operación
     * @param coeff Los coeficientes de cada sumando del polinomio
     * @param poliPows Las potencias de cada sumando del polinomio
     * @return Algoritmo para utilizar en WRandom
     *
     * @throws IllegalArgumentException Si la cantidad de coeficientes es
     * diferente a la cantidad de potencias
     */
    public static UnaryOperator<BigInteger> buildAlgorithm(BigInteger modulus, BigInteger coeff[], int[] poliPows) {
        if (coeff.length != poliPows.length) {
            throw new IllegalArgumentException("La cantidad de coeficientes debe ser igual a la cantidad de potencias");
        }

        return (BigInteger bi) -> {
            BigInteger result = BigInteger.ZERO;

            for (int i = 0; i < coeff.length; i++) {
                result = result.add(bi.pow(poliPows[i]).multiply(coeff[i]));
            }

            return result.mod(modulus);
        };
    }

    private static BigInteger bigInterize(int a) {
        return new BigInteger(String.valueOf(a));
    }

    public BigInteger nextBigInt() {
        return seed = algorithm.apply(seed);
    }

    public int nextInt() {
        BigInteger a = nextBigInt();

        return a.mod(MAX_INT).intValue();
    }

    public int nextInt(int min, int max) {
        BigInteger a = nextBigInt();

        return a.
                mod(bigInterize(max + 1 - min)).
                add(bigInterize(min)).
                intValue();
    }

    /**
     *
     * Regresa el siguiente doble pseudo-aleatorio
     *
     * @param floatingNumbers La cantidad de dígitos después del punto
     * @return Un valor double pseudo-aleatorio
     */
    public double nextDouble(int floatingNumbers) {
        BigDecimal a = new BigDecimal(nextBigInt());

        BigDecimal nines = BigDecimal.TEN.
                pow(a.toString().length()).
                subtract(BigDecimal.ONE);

        return a.divide(nines, floatingNumbers, RoundingMode.HALF_UP).doubleValue();
    }

    public double nextDouble() {
        return nextDouble(9);
    }

    public boolean nextBoolean() {
        return nextDouble() > 0.5;
    }
    
    public void randomize(WList list) {
        list.mergeSort((Object o1, Object o2) -> {
            return nextInt(0, 2) - 1;
        });
    }

}
