import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Main
{
	public static void main(String args[]) throws IOException
	{
		File input = new File("Pikatchu.jpg");
		BufferedImage zdjecie = ImageIO.read(input);
		int szerokosc = zdjecie.getWidth();
		int wysokosc = zdjecie.getHeight();
		Negatyw a = new Negatyw(zdjecie, 0, szerokosc / 2, 0, wysokosc / 2);
		
		Negatyw b = new Negatyw(zdjecie, szerokosc / 2, szerokosc, 0, wysokosc / 2);
		
		Negatyw c = new Negatyw(zdjecie, 0, szerokosc / 2, wysokosc / 2, wysokosc);
		
		Negatyw d = new Negatyw(zdjecie, szerokosc / 2, szerokosc, wysokosc / 2, wysokosc);
		
		a.zamienZdjecie();
		b.zamienZdjecie();
		c.zamienZdjecie();
		d.zamienZdjecie();
		
		
    	File wyjscie = new File("negativ.jpg");
    	ImageIO.write(zdjecie, "jpg", wyjscie);
	}
}

class Negatyw extends Thread
{
	private BufferedImage image;
    private int szer_start;
    private int szer_koniec;
    private int wys_start;
    private int wys_koniec;
    
    public Negatyw(BufferedImage image, int szer_start, int szer_koniec, int wys_start, int wys_koniec)
    {
    	this.image = image;
    	this.szer_start = szer_start;
    	this.szer_koniec = szer_koniec;
    	this.wys_start = wys_start;
    	this.wys_koniec = wys_koniec;
    }
    
    public void zamienZdjecie()
    {
    	for(int i = wys_start; i < wys_koniec; i++)
    	{
    		for(int j = szer_start; j < szer_koniec; j++)
    		{
    			Color c = new Color(image.getRGB(j, i));
    			int red = (int)(c.getRed());
    			int green = (int)(c.getGreen());
    			int blue = (int)(c.getBlue());
    			int redFinal, greenFinal, blueFinal;
    
    			redFinal = 255 - red;
    			greenFinal = 255 - green;
    			blueFinal = 255 - blue;
    			Color newColor = new Color(redFinal, greenFinal, blueFinal);
    			image.setRGB(j, i, newColor.getRGB());
    		} 
    	}

    }
}
