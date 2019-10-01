package game;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class GameCanvas extends Canvas {
	BackGround bg;
	List<Bug[]> bugs;
	Bug[] bug;
	Hand hand;

	public GameCanvas() {
		bg = new BackGround();
		bugs = new ArrayList<>();
		bug = new Bug[20];
		for (int i = 0; i < bug.length; i++) {
			bug[i] = new Bug();
		}
		hand = new Hand();

		addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				hand.move(e.getX(), e.getY());
				repaint();
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				hand.move(e.getX(), e.getY());
				for (int i = 0; i < bug.length; i++) {
					if (bug[i].contains(e.getX(), e.getY())) {
						hand.attack(bug[i]);
						System.out.println("attack");
					}
				}
				repaint();
			}
		});

		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				hand.setPressed(true);
				hand.move(e.getX(), e.getY());
				System.out.println("attack");
				// 벌레에서 자리 판정
				for (int i = 0; i < bug.length; i++) {
					if (bug[i] != null)
						if (bug[i].contains(e.getX(), e.getY())) {
							hand.attack(bug[i]);
							System.out.println("attack");
						}

				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				hand.setPressed(false);
			}
		});

	}

	@Override
	public void update(Graphics g) {
		for (int i = 0; i < bug.length; i++) {
			if (bug[i] != null)
				if (bug[i].getHp() <= 0)
					bug[i].die();
		}
		this.paint(g);
	}

	@Override
	public void paint(Graphics g) {
		Image bufImage = createImage(getWidth(), getHeight());
		Graphics bufGraphic = bufImage.getGraphics();

		bg.draw(bufGraphic, this);
		hand.draw(bufGraphic, this);
		for (int i = 0; i < bug.length; i++) {
			if (bug[i] != null)
				bug[i].draw(bufGraphic, this);
		}

		g.drawImage(bufImage, 0, 0, this);
	}

	public void start() {
		new Thread(() -> {
			while (true) {
				try {
					hand.update();
					for (int i = 0; i < bug.length; i++) {
						if (bug[i] != null)
							if (bug[i].isAlive())
								bug[i].move();
					}
					Thread.sleep(16);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				repaint();
			}
		}).start();
	}
}
