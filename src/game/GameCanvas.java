package game;

import java.awt.Canvas;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

//내가 한번 바꿔볼게~!~!~!~!
public class GameCanvas extends Canvas {
	BackGround bg;
	Hand hand;

	public GameCanvas() {
		bg = new BackGround();
		hand = new Hand();

		addMouseMotionListener(new MouseAdapter() {

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				hand.move(e.getX(), e.getY());
//				System.out.println(e.getX() + ", " + e.getY());
				repaint();
			}

		});
		addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				hand.move(e.getX(), e.getY());
				System.out.println(e.getX() + ", " + e.getY());
				// kill bug
			}
		});

		new Thread(() -> {
			while (true) {
				try {

					hand.update();
					Thread.sleep(1); // �빟 60�봽�젅�엫
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				repaint();
			}
		}).start();

	}

	@Override
	public void update(Graphics g) {
		// TODO Auto-generated method stub
		this.paint(g);
	}

	@Override
	public void paint(Graphics g) {

		Image bufImage = createImage(getWidth(), getHeight());
		Graphics bufGraphic = bufImage.getGraphics();

		bg.draw(bufGraphic, this);
		hand.draw(bufGraphic, this);
		g.drawImage(bufImage, 0, 0, this);
	}

}