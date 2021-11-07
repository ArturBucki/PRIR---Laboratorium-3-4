
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Filozof extends Thread
{
    public static int MAX = 100;
    static Semaphore[] widelec = new Semaphore[MAX];
    int mojNum;

    public Filozof()
    {
    }

    public Filozof(int nr)
    {
        mojNum = nr;
    }

    public static void ustawMax(int iloscFilozofow)
    {
        MAX = iloscFilozofow;
    }
}




class Filozof1 extends Filozof 
{
    public Filozof1(int nr)
    {
        super(nr);
    }

    public void run()
    {
        while (true)
        {

            System.out.println("Mysle nr: " + mojNum);
            try {
                Thread.sleep((long) (7000 * Math.random()));
            } catch (InterruptedException e) {
            }
            
            widelec[mojNum].acquireUninterruptibly(); //przechwycenie L widelca
            widelec[(mojNum + 1) % MAX].acquireUninterruptibly(); //przechwycenie P widelca

            System.out.println("Zaczyna jesc nr: " + mojNum);
            try {
                Thread.sleep((long) (5000 * Math.random()));
            } catch (InterruptedException e) {
            }
            
            System.out.println("Konczy jesc nr: " + mojNum);
            widelec[mojNum].release(); //zwolnienie L widelca
            widelec[(mojNum + 1) % MAX].release(); //zwolnienie P widelca
        }
    }
}




class Filozof2 extends Filozof
{
    public Filozof2(int nr)
    {
        super(nr);
    }

    public void run ( )
    {
        while ( true )
        {

            System.out.println ( "Mysl nr: " + mojNum) ;
            try {
                Thread.sleep((long)(5000 * Math.random()));
            } catch ( InterruptedException e ) {
            }
            if (mojNum == 0)
            {
                widelec [(mojNum+1)%MAX].acquireUninterruptibly();
                widelec [mojNum].acquireUninterruptibly();
            }
            else
            {
                widelec [mojNum].acquireUninterruptibly();
                widelec [(mojNum+1)%MAX].acquireUninterruptibly();
            }

            System.out.println ("Zaczyna jesc nr: " + mojNum);
            try {
                Thread.sleep((long)(3000 * Math.random()));
            } catch (InterruptedException e) {
            }
            System.out.println("Konczy jesc nr: " + mojNum);
            widelec [mojNum].release();
            widelec [(mojNum+1)%MAX].release();
        }
    }
}





class Filozof3 extends Filozof
{
    Random losuj;

    public Filozof3(int nr)
    {
        super(nr);
        this.losuj = new Random(mojNum);
    }

    public void run()
    {
        while (true)
        {
            System.out.println("Mysle nr: " + mojNum);
            try {
                Thread.sleep((long) (5000 * Math.random()));
            } catch (InterruptedException e) {
            }
            int strona = losuj.nextInt(2);
            boolean podnioslDwaWidelce = false;
            do {
                if (strona == 0)
                {
                    widelec[mojNum].acquireUninterruptibly();
                    if (!(widelec[(mojNum + 1) % MAX].tryAcquire()))
                    {
                        widelec[mojNum].release();
                    } 
                    else
                    {
                        podnioslDwaWidelce = true;
                    }
                } 
                else
                {
                    widelec[(mojNum + 1) % MAX].acquireUninterruptibly();
                    if (!(widelec[mojNum].tryAcquire()))
                    {
                        widelec[(mojNum + 1) % MAX].release();
                    } 
                    else
                    {
                        podnioslDwaWidelce = true;
                    }
                }
            } while (podnioslDwaWidelce == false);
            System.out.println("Zaczyna jesc nr: " + mojNum);
            try {
                Thread.sleep((long) (3000 * Math.random()));
            } catch (InterruptedException e) {
            }
            System.out.println("Konczy jesc nr: " + mojNum);
            widelec[mojNum].release();
            widelec[(mojNum + 1) % MAX].release();
        }
    }
}





class Test
{
    public static void main(String[] args)
    {
        Filozof filozof = new Filozof();
        int wariant = pobierzWariant();
        int filozofowie = pobierzFilozofow();
        filozof.ustawMax(filozofowie);

        for (int i = 0; i < filozof.MAX; i++)
        {
            filozof.widelec[i] = new Semaphore(1);
        }

        for (int i = 0; i < filozof.MAX; i++)
        {
            if (wariant == 1)
            {
                new Filozof1(i).start();
            }
            else if (wariant == 2)
            {
                new Filozof2(i).start();
            } 
            else if (wariant == 3)
            {
                new Filozof3(i).start();
            }
        }
    }

    private static int pobierzWariant()
    {
        System.out.print("Podaj wariant 1, 2 lub 3: ");

        Scanner wariant = new Scanner(System.in);
        int wybranyWariant = wariant.nextInt();

        while (wybranyWariant != 1 && wybranyWariant != 2 && wybranyWariant != 3)
        {
            System.out.println("Podaj wariant 1, 2 lub 3: ");
            wybranyWariant = wariant.nextInt();
        }

        return wybranyWariant;
    }

    private static int pobierzFilozofow()
    {
        System.out.print("Wybierz ilosc filozofow (2-100): ");

        Scanner ileFilozofow = new Scanner(System.in);
        int iloscFilozofow = ileFilozofow.nextInt();

        while (iloscFilozofow < 2 || iloscFilozofow > 100)
        {
            System.out.println("Wybierz ilosc filozofow (2-100):");
            iloscFilozofow = ileFilozofow.nextInt();
        }

        return iloscFilozofow;
    }
}
