package ŒÂ◊”∆Â;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
//…Ë÷√∆Â≈Ã
public class qipan {
	public void qipu() throws IOException {
		BufferedImage bfimg = new BufferedImage(600, 600, BufferedImage.TYPE_INT_RGB);
		Graphics g = bfimg.getGraphics();
		g.setColor(Color.yellow);
		int w = 20;
		int h = 20;
		for (int x = 1; x < 29; x++) {
			for (int y = 1; y < 29; y++) {
				g.drawRect(20 * x, 20 * y, w, h);
			}
		}
		ImageIO.write(bfimg, "jpg", new File("C:/Users/gewenchao/Desktop/c.jpg"));
	}
}
//ª≠∞◊∆Â,∫Ï∆Â
class baiorhongqi {
	public void qi() throws IOException {
		BufferedImage bfimg = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB);
		Graphics g = bfimg.getGraphics();
		g.setColor(Color.white);
		g.fillOval(0, 0, 10, 10);
		// ImageIO.write(bfimg, "jpg", new File("C:/Users/gewenchao/Desktop/i.jpg"));

		ImageIO.write(bfimg, "jpg", new File("C:/Users/gewenchao/Desktop/q.jpg"));

	}
}
