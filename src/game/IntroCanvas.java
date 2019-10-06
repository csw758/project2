package game;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class IntroCanvas extends Canvas {
	StartBackGround bg;
	GameFrame gameframe;

	public IntroCanvas() {
		bg = new StartBackGround();
		// 이곳에서 초기화하면 밑에서 사용 불가. 왜????
//		gameframe = GameFrame.getInstance();
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
//				gamecontext.gameframe.test();
				// 사용 하기 전에 불러와야 사용 가능.
				gameframe = GameFrame.getInstance();
				gameframe.switchCanvas(new PlayCanvas());

			}
		});
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
