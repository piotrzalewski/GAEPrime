/**
 * Created by peter on 29/12/14.
 */
import static services.PrimeService.*

def org = params['number']

def list=calculatePrimes(org)

request['primes']=list
if (!localMode ) request['geo']=geo

forward '/WEB-INF/pages/prime.gtpl'






