package game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Bug {
	private int x, y;
	private int w, h;
	private int hp;
	private int vx, vy;

	private Random random;
	private boolean isAlive;

	private Image img;
	private static Toolkit tk;
	static {
		tk = Toolkit.getDefaultToolkit();
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public int getH() {
		return h;
	}

	public int getW() {
		return w;
	}

	public int getX() {
		return x;
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

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public Bug() {
		random = new Random();
		x = random.nextInt(300) + 300;
		y = 20;
		w = h = 13;
		setHp(1);
		img = tk.createImage("res/bullet.png");
		isAlive = true;
	}

	public void draw(Graphics g, GameCanvas gamecanvas) {
//		g.drawImage(img, x, y, x + w, y + h, x, y, x + w, y + h, gamecanvas);
		g.drawImage(img, x, y, gamecanvas);
	}

	public void move() {
		float dx = x - 230;
		float dy = y - 700;
		float dis = (float) Math.sqrt(dx * dx + dy * dy);

		vx = (int) (w / dis * random.nextInt(177));
		vy = (int) (h / dis * random.nextInt(157));

		x += -vx;
		y += vy;
	}

	public void die() {
		vx = 0;
		vy = 0;
		isAlive = false;
		img = tk.getImage("res/missile.png");
	}

	public boolean contains(int mx, int my) {
		if (x <= mx && mx <= x + w /**/
				&& y <= my && my <= y + h)
			return true;
		else
			return false;

	}

}
