package game;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class PlayCanvas extends Canvas {
	BackGround bg;
	Hand hand;
	GameFrame gameframe;

	List<Bug> bugs;

	float gentime;
	int countBug;
	int countDieBug;

	public PlayCanvas() {
		gameframe = GameFrame.getInstance();
		bg = new BackGround();
		bugs = new ArrayList<Bug>();
		countBug = 3;
		gentime = 1f;
		countDieBug = 0;

		for (int i = 0; i < countBug; i++) {
			bugs.add(new Bug());
		}
		hand = new Hand();

		addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				// 현재 무기 이미지가 마우스를 따라감.
				hand.move(e.getX(), e.getY());
				repaint();
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				// 마우스를 누른 상태에서도 무기 이미지가 따라감
				hand.move(e.getX(), e.getY());
				// 마우스를 누른 상태에서 벌레에 닿으면 그 벌레는 죽음.
				catchBug(e);
				repaint();
			}
		});

		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// 마우스가 눌려져 있는 동안 공격이 활성화.
				hand.setPressed(true);
				hand.move(e.getX(), e.getY());
				catchBug(e);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				hand.setPressed(false);
			}
		});

		new Thread(() -> {
			while (true) {
				try {
					// 매 프레임마다 마우스의 위치를 업데이트
					hand.update();
					bugControl();
					Thread.sleep(16);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				repaint();
			}
		}).start();
	} // 생성자 종료

	@Override
	public void update(Graphics g) {
		// 벌레가 하나씩 생성되도록 하기 위해
		// 일정 간격의 시간을 추가함.
		genTimeUpdate();
		gameClear();
		this.paint(g);
	}

	@Override
	public void paint(Graphics g) {
		Image bufImage = createImage(getWidth(), getHeight());
		Graphics bufGraphic = bufImage.getGraphics();

		bg.draw(bufGraphic, this);
		hand.draw(bufGraphic, this);

		for (Bug bug : bugs) {
			if (bug != null)
				bug.draw(bufGraphic, this);
		}

		g.drawImage(bufImage, 0, 0, this);
	}

	public void genTimeUpdate() {
		if (gentime > 0)
			gentime += -0.02f;
	}

	private void gameClear() {
		if (countDieBug >= countBug) {
//			gameframe.switchCanvas(new IntroCanvas());
		}
	}

	// 생성된 벌레들이 움직일 수 있는지 확인해서 실행
	private void bugControl() {
		for (Bug bug : bugs) {
			if (bug != null) {
				if (bug.isGen()) {
					if (bug.isAlive())
						bug.move();
				} else if (gentime <= 0) {
					bug.gen();
					gentime = 1f;
				}
				if (bug.getX() < 261 && bug.getY() > 511) {
//					gameframe.switchCanvas(new EndingCanvas());
//					System.out.println("ending");
					break;
				}
			}
		}
	}

	private void catchBug(MouseEvent e) {
		for (Bug bug : bugs) {
			if (bug.contains(e.getX(), e.getY())/**/
					&& bug.isGen() && bug.isAlive()) {
				hand.attack(bug);
				countDieBug++;
				System.out.println("attack");
			}
		}
	}
}