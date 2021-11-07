public class Samochod extends Thread
{
	private String nrRej;
	private int pojZbiornika;
	private int paliwo;
	private boolean drive = false;
	
	public Samochod(String nrRej, int pojZbiornika)
	{
		this.nrRej = nrRej;
		this.pojZbiornika = pojZbiornika;
	}
	
	public void tankowanie(int paliwo)
	{
		if(this.paliwo + paliwo > pojZbiornika)
		{
			this.paliwo = pojZbiornika;
		}
		else
		{
			this.paliwo = paliwo + this.paliwo;
		}
	}
	
	public void startt()
	{
		drive = true;
	}
	
	public void stopp()
	{
		drive = false;
	}
	
	public void run()
	{
		startt();
		
			while(drive == true)
			{
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				paliwo--;
				System.out.println(nrRej + ": " + paliwo);
				if(paliwo <= 0)
				{
					drive = false;
				}
				
			}
	}
}

class TestSamochod
{
	public static void main(String args[])
	{
		Samochod A = new Samochod("A", 100);
		Samochod B = new Samochod("B", 200);
		Samochod C = new Samochod("C", 300);
		
		A.tankowanie(100);
		B.tankowanie(10);
		C.tankowanie(400);
		
		A.start();
		B.start();
		C.start();
		
	}
}
