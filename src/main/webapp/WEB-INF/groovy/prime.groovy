/**
 * Created by peter on 29/12/14.
 */
import services.PrimeService
import services.smallprimes

def org = params['number']

def primeService = new PrimeService()

def loadPrimes() {

//    def entities = datastore.execute {
//        select all from "smallprimes"
//        sort asc by start
//    }
//    def entities = smallprimes.findAll()
    def entities = smallprimes.findAll { sort asc by "start" }
    logger['root'].info("Loaded " + entities.size() + " chunk of data")
    logger['root'].info("from ALL " + smallprimes.count() + " entities")

    def primes = entities.collect { it.list }.flatten()
    logger['root'].info("Loaded " + primes.size() + " primes")
    logger['root'].info("Loaded primes from " + primes[0] + " to " + primes[-1])

    primes
}

def primes = loadPrimes()

def list = primeService.calculatePrimes(org, primes)

request['primes'] = list
if (!localMode) request['geo'] = geo

forward '/WEB-INF/pages/prime.gtpl'






