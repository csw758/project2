package game;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class IntroCanvas extends Canvas {
	StartBackGround bg;
	GameFrame frame;

	public IntroCanvas() {
		bg = new StartBackGround();
	}

	@Override
	public void update(Graphics g) {
		this.paint(g);
	}

	@Override
	public void paint(Graphics g) {

		Image bufImage = createImage(getWidth(), getHeight());
		Graphics bufGraphic = bufImage.getGraphics();

		bg.draw(bufGraphic, this);

		g.drawImage(bufImage, 0, 0, this);
	}
}
