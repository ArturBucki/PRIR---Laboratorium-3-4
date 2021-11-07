import java.util.Scanner;

public class Main
{
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Podaj liczbe prob:");
		int n = sc.nextInt();
		
        MonteCarlo a = new MonteCarlo(0, 0, 2, 2, n);
        MonteCarlo b = new MonteCarlo(2, 0, 4, 2, n);
        MonteCarlo c = new MonteCarlo(0, 2, 2, 4, n);
        MonteCarlo d = new MonteCarlo(2, 2, 4, 4, n);
        
        a.run();
        b.run();
        c.run();
        d.run();
        
        try {
		a.join();
        b.join();
        c.join();
        d.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        double circleField = a.getScore() + b.getScore() + c.getScore() + d.getScore();
        
        circleField = (circleField / ((double)n * 4)) * (2 * 2);
        
        System.out.println("Pole kola przyblizone: " + circleField);
        
        
	}
}

class MonteCarlo extends Thread
{
	int n;
	double score = 0;
	int xStart, yStart, xStop, yStop;
	
	public MonteCarlo(int xStart, int yStart, int xStop, int yStop, int n)
	{
		this.xStart = xStart;
		this.yStart = yStart;
		this.xStop = xStop;
		this.xStop = xStop;
		this.n = n;
	}
	
	public void run()
	{
		int inCircle = 0;
		
		for(int i = 0; i < n; i++)
		{
			double x = Math.random();
            double y = Math.random();
            
            if (x * x + y * y <= 1)
            {
            	inCircle++;
            }
		}
		
	    score = inCircle;
	}
	
    public double getScore()
    {
        return this.score;
    }
}
