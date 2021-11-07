import java.io.IOException;

public class Main
{
	public static void main(String args[])
	{
		Czasomierz czasomierz = new Czasomierz();
		czasomierz.start();
	}
}

class Czasomierz extends Thread
{
	
	public void run()
	{
		int sec = 0;
		int min = 0;
		int hrs = 0;
		
		while(true)
		{
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			sec++;
			
			if(sec == 60)
			{
				min++;
				sec = 0;
			}
			if(min == 60)
			{
				hrs++;
				min = 0;
			}
			if(hrs == 24)
			{
				sec = 0;
				min = 0;
				hrs = 0;
			}
			
			System.out.println("Hours:" + hrs + " Minutes:" + min + " Seconds:" + sec);
			
		}
	}
}
