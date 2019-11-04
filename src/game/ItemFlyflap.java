package game;

import java.awt.Graphics;

public class ItemFlyflap extends Item {
	public ItemFlyflap() {
		setImg(getTk().getImage("res/idle.png"));
		setW(290);
		setH(240);
	}

	public void draw(Graphics g, GameCanvas gamecanvas) {
		g.drawImage(getImg(), 350, 700, 350 + 50, 700 + 50, 0, 0, getW(), getH(), gamecanvas);
	}
}
