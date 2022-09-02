package ConcurentThread;

import Collection.Personne;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

public class ConcurentThread
{
    Personne p=new Personne();

//public static ArrayList<String> stringArrayList=new ArrayList<>();
    public static void main (String args[]) throws InterruptedException {
       // AtomicInteger atomicInteger=new AtomicInteger(0);//point faible achaque iteration on fait synchronisation memoir local et memoir partagé
       // LongAdder longAdder=new LongAdder();
      //  LongAccumulator longAccumulator=new LongAccumulator((x,y)->x+y,0);
        int NbreCore=Runtime.getRuntime().availableProcessors();
        LongAccumulator longAccumulator=new LongAccumulator((x,y)->x*y,1);
        ExecutorService executorService= Executors.newFixedThreadPool(NbreCore);
        for(int i=0;i<10;i++)
      //  executorService.submit(new ToDo(atomicInteger));
       // executorService.submit(new ToDo(longAdder));
            executorService.submit(new ToDo(longAccumulator));
        executorService.shutdown();
        if(executorService.awaitTermination(10, TimeUnit.SECONDS))// si l'executions des thread est deja terminé ou bien 10 second apres le start
            {
                // System.out.println(stringArrayList);
              //  System.out.println("la valeur finale du compteur atomicInteger==="+atomicInteger.get());
              //  System.out.println("la valeur finale du compteur longAdder===="+longAdder.sum());
                System.out.println("la valeur finale du compteur LongAcumulator==="+longAccumulator.get());
        }

    }
    static class ToDo implements Runnable
    {
private  AtomicInteger atomicInteger;
private  LongAdder longAdder;
private LongAccumulator longAccumulator;

        public ToDo(LongAccumulator longAccumulator) {
            this.longAccumulator = longAccumulator;
        }

        public ToDo(LongAdder longAdder) {
            this.longAdder = longAdder;
        }

        public ToDo(AtomicInteger atomicInteger)
        {
            this.atomicInteger = atomicInteger;
        }

        @Override
        public void run()
        {
          // atomicInteger.incrementAndGet();
           // longAdder.increment();
            longAccumulator.accumulate(2);
        }
    }
}
