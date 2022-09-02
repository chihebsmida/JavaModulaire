package ConcurentThread;

import Collection.Personne;

import java.lang.reflect.Method;
import java.util.concurrent.locks.StampedLock;

public class Point
{
    private  int x;
    private  int y;
private StampedLock stampedLock;
    public Point() {
    }
public static void main(String args[]) throws NoSuchMethodException {

    Class<Personne>c= (Class<Personne>) new Personne().getClass();
    System.out.println(c.getName());
    Method m=c.getMethod("dubliquer", String.class);
    m.setAccessible(true);
   System.out.println("la vesibiliter de la methode est==="+m.isAccessible());
}
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }
  /*  public synchronized void move(int deltax,int deltay)
    {
        this.x+=deltax;
        this.y+=deltay;
    }

   */
    public void move(int deltax,int deltay)
    {
        Long stamp=stampedLock.writeLock();
        try {
            x+=deltax;
            y+=deltay;
        }
        finally {
            stampedLock.unlockWrite(stamp);
        }
    }


  /*  public synchronized double distance()
    {
        return Math.hypot(getX(),getY());
    }

   */
    public double distance()
    {
        //une simple lecture
        //essayer d'obtenir un tampon de lecture Optimiste (aucun thread en mode ecriture)
        long stamp=stampedLock.tryOptimisticRead();
        if(!stampedLock.validate(stamp)) // si ona obtenu un tampon de lecture
        {
            stamp=stampedLock.readLock();// on bloque toute ecriture et on ne bloque pas les autres thread de faire lecture
            try {
                return Math.hypot(getX(),getY());
            }
            finally {
                stampedLock.unlockRead(stamp);
            }
        }
        return 0;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    //avantage Synchronized
    //1 facile a utiliser
    //2 leur liberation Automatique garantie par le compilateur et le jvm  a la fin du bloc du code
    // inconvenients
    //1 ils sont bloquonts (initule d'executer en boucle plusieurs thread une fonction f)
    //2 il n'est pas possible de verifier l'etat du moniteur
    //3 le niveau de veroullage est le meme pour le traitement ecriture ou lecture)

    //stampedLock
    // la methode writeLock bloque eventuellement l'attente d'un acces exclusive
    // la methode readLock bloque eventuellement l'attente d'un acces  non exclusive
}
