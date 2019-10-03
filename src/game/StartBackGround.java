package game;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class StartBackGround {
	protected int x;
	protected int y;
	protected Image img;

	static Toolkit tk;
	static {
		tk = Toolkit.getDefaultToolkit();
	}

	public StartBackGround() {
		x = 0;
		y = 0;
		img = tk.getImage("res/gameStartBackGround.jpg");
	}

	public void draw(Graphics g, IntroCanvas introCanvas) {
		g.drawImage(img, x, y, x + 850, y + 862, x, y, x + 850, y + 900, introCanvas);
	}
}
