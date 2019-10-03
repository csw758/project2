package game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Hand {
	private int x;
	private int y;
	private int w;
	private int h;

	private int mx;
	private int my;

	private boolean pressed;
	private Weapon weapon;
	private int money;

	private Image img;
	Toolkit tk = Toolkit.getDefaultToolkit();

	public Hand() {
		pressed = false;
		x = 425;
		y = 391;
		mx = 425;
		my = 391;
		w = 35;
		h = 35;
		setMoney(0);
		weapon = new Weapon();
		img = tk.getImage("res/hand.png");
	}

	public void draw(Graphics g, PlayCanvas gameCanvas) {
		if (pressed)
			g.drawImage(img, x, y, x + w - 10, y + h - 10, 0, 0, 188, 214, gameCanvas);
		else
			g.drawImage(img, x, y, x + w, y + h, 0, 0, 188, 214, gameCanvas);

	}

	public void attack(Bug bug) {
		bug.setHp(bug.getHp() - 1);
	}

	public boolean isPressed() {
		return pressed;
	}

	public void setPressed(boolean pressed) {
		this.pressed = pressed;
	}

	public void update() {
		x = mx;
		y = my;
	}

	public void move(int x, int y) {
		mx = x;
		my = y;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
		System.out.println(this.money);
	}
}
