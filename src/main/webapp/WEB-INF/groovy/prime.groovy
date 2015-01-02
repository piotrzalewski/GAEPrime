/**
 * Created by peter on 29/12/14.
 */
import services.PrimeService
import services.smallprimes

def org = params['number']

def primeService = new PrimeService()

def loadPrimes() {
    def cached=getPrimesFromCache()
    if(cached) return cached
    def entities = smallprimes.findAll { sort asc by "start" }
    log.info("Loaded " + entities.size() + " chunk of data")
    log.info("from ALL " + smallprimes.count() + " entities")

    def primes = entities.collect { it.list }.flatten()
    log.info("Loaded " + primes.size() + " primes")
    log.info("Loaded primes from " + primes[0] + " to " + primes[-1])
    cachePrimes(primes)
    primes
}

def cachePrimes(primes){
    def number_of_chunks=primes.size()/10_000+1;
    for(int a=0;a<number_of_chunks;a++){
        memcache['primes'+a]=primes[a*10_000,(a+1)*10_000-1]
    }
    memcache['primes_count']=number_of_chunks;
}

def getPrimesFromCache(){
    if('primes_count' in memcache){
        def number_of_chunks=memcache['primes_count']
        def primes=[]
        for (int a=0;a<number_of_chunks;a++){
            if('primes'+a in memcache){
                primes.add(memcache['primes'+a])
            }else{
                return null
            }
        }
        primes.flatten()
    }
}

def primes = loadPrimes()

def list = primeService.calculatePrimes(org, primes)

request['primes'] = list
if (!localMode) request['geo'] = geo

forward '/WEB-INF/pages/prime.gtpl'






