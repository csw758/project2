package game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class DieBug {
	private int x, y;
	private Image img;

	private static Toolkit tk;
	static {
		tk = Toolkit.getDefaultToolkit();
	}
	
	public DieBug(int x, int y) {
		this.x = x;
		this.y = y;
		img = tk.getImage("res/diemogiBig.png");
	}
	
	public void draw(Graphics g, PlayCanvas playcanvas) {
//		g.drawImage(img, x, y, playcanvas);
		g.drawImage(img, x, y, 40, 35, 0, 0, 1023, 752, playcanvas);
	}

}
