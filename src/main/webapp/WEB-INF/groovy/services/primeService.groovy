package services

import groovyx.gaelyk.GaelykBindings

/**
 * Created by peter on 01/01/15.
 */

@GaelykBindings
public class PrimeService{

    static def calculatePrimes(org){
        BigInteger number = org as BigInteger
        BigDecimal max = Math.sqrt(number)+1

        def list = []
        int a=2
        while ( a < max && number > 1) {
            while (number % a == 0) {
                list.add(a)
                number = number / a
            }
            a++
        }
        if (number > 1) list.add(number)
        list
    }
}