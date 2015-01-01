/**
 * Created by peter on 29/12/14.
 */
import groovyx.gaelyk.spock.*
import com.google.appengine.api.datastore.*
import static com.google.appengine.api.datastore.FetchOptions.Builder.*

class PrimeSpec extends ConventionalGaelykUnitSpec {

    def "test distributions of the prime numbers"() {
        given:
            prime.params=[number:a]
            prime.get()

        expect:
            prime.request['primes']==b

        where:
         a ||b
         20||[2,2,5]
         23||[23]
    }

}
