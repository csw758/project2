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
	private float tempX, tempY;

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
		x = random.nextInt(800);
		y = -20;
		w = h = 13;
		tempX = x;
		tempY = y;
		setHp(1);
		img = tk.createImage("res/bullet.png");
		isAlive = true;
	}

	public Bug(int x, int y) {
		this();
		this.x = x;
		this.y = y;
		tempX = x;
		tempY = y;
	}

	public void draw(Graphics g, GameCanvas gamecanvas) {
//		g.drawImage(img, x, y, x + w, y + h, x, y, x + w, y + h, gamecanvas);
		g.drawImage(img, x, y, gamecanvas);
	}

	public void move() {
		// v(dx,dy)
		// 침대 중앙 : 114,690
		// 침대 왼쪽 아래 : 30,804
		// +,-
		float dx = (x - 30);
		float dy = (y - 804);
		// 벡터의 길이.
		float dis = (float) Math.sqrt(dx * dx + dy * dy);

		// 정규화 = 단위벡터 생성.
		tempX = dx / dis * random.nextFloat() * 17;
		tempY = dy / dis * random.nextFloat() * 17;

		x += (int) tempX * (-1);
		y += (int) tempY * (-1);
		
		/*
		 * 벌레의 움직임이 좀 더 커야할것 같다.
		 * 벌레가 하나씩 출현하게 
		 * */
	}

	public void die() {
		tempX = 0;
		tempY = 0;
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
