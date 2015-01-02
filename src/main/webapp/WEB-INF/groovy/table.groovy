/**
 * Created by peter on 29/12/14.
 */
import services.smallprimes
import  services.PrimeService

def primeService=new PrimeService()
def org = params['number'] ?:0

int SIZE=100_000
Long start=(org as Long)*SIZE*10

for (int a=0;a<10;a++) {
    def list = primeService.getTableOfPrimes(start+a*SIZE+1, start+(a+1)*SIZE)
//    def e=new Entity('primes')
//    e.index=a
//    e.unindexed.list=list
//    e.size=SIZE
//    e.start=start
    def e=new smallprimes()
    e.list=list
    e.size=SIZE
    e.index=start+a*SIZE
    e.start=start+a*SIZE
    e.save()
    log.info("Ended:"+a)
}


if (!localMode ) request['geo']=geo

forward '/WEB-INF/pages/table.gtpl'

