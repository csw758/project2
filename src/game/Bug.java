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
	private int startX, startY;

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
		startX = x;
		startY = y;
		setHp(1);
		img = tk.createImage("res/bullet.png");
		isAlive = true;
	}

	public Bug(int x, int y) {
		this();
		this.x = x;
		this.y = y;
		startX = x;
		startY = y;
	}

	public void draw(Graphics g, GameCanvas gamecanvas) {
//		g.drawImage(img, x, y, x + w, y + h, x, y, x + w, y + h, gamecanvas);
		g.drawImage(img, x, y, gamecanvas);
	}

	public void move() {
		float dx = (x - 230) / 10;
		float dy = (y - 700) / 10;
		float dis = (float) Math.sqrt(dx * dx + dy * dy);

		vx = (int) (w / dis * random.nextInt(7));
		vy = (int) (h / dis * random.nextInt(7));

		if (startX <= 230)
			vx = random.nextInt(7);
		else if (startY >= 600)
			vy = random.nextInt(7);

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
