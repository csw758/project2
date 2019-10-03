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
	GameFrame frame;

	List<Bug> bugs;

	float gentime;
	int countBug;
	int dieBug;

	public PlayCanvas() {
		frame = GameFrame.getInstance();
		bg = new BackGround();
		bugs = new ArrayList<Bug>();
		countBug = 3;
		gentime = 1f;
		dieBug = 0;

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
				for (int i = 0; i < countBug; i++) {
					catchBug(bugs, i, e);
					gameClear();
				}
				repaint();
			}
		});

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// 마우스가 눌려져 있는 동안 공격이 활성화.
				hand.setPressed(true);
				hand.move(e.getX(), e.getY());
				// 벌레에서 자리 판정
				for (int i = 0; i < countBug; i++) {
					catchBug(bugs, i, e);
					gameClear();
				}
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

		// 벌레가 죽었는지 살았는지 판별
		// -> 죽은 벌레를 관리하는 클래스가 필요함.
		for (int i = 0; i < countBug; i++) {
			bugDie(bugs, i);
		}
		this.paint(g);
	}

	@Override
	public void paint(Graphics g) {
		Image bufImage = createImage(getWidth(), getHeight());
		Graphics bufGraphic = bufImage.getGraphics();

		bg.draw(bufGraphic, this);
		hand.draw(bufGraphic, this);

		for (int i = 0; i < countBug; i++) {
			if (bugs.get(i) != null)
				bugs.get(i).draw(bufGraphic, this);
		}

		g.drawImage(bufImage, 0, 0, this);
	}

	public void genTimeUpdate() {
		if (gentime > 0)
			gentime += -0.02f;
	}

	private void bugDie(List<Bug> bugs, int i) {
		if (bugs.get(i).getHp() <= 0) {
			bugs.get(i).die();
		}
	}

	private void catchBug(List<Bug> bugs, int i, MouseEvent e) {
		if (bugs.get(i).contains(e.getX(), e.getY()) /**/
				&& bugs.get(i).isGen() && bugs.get(i).isAlive()) {
			hand.attack(bugs.get(i));
			dieBug++;
			System.out.println("attack" + i);
		}

	}

	private void gameClear() {

	}

	// 생성된 벌레들이 움직일 수 있는지 확인해서 실행
	private void bugControl() {
		for (int i = 0; i < countBug; i++) {
			bugMovement(bugs, i);
		}
	}

	private void bugMovement(List<Bug> bugs, int i) {
		// 벌레가 가지고 있어야 할 거 같은뎈ㅋ
		if (bugs.get(i) != null) {
			if (bugs.get(i).isGen()) {
				bugAlive(bugs, i);
			} else if (gentime <= 0)
				bugGen(bugs, i);

			gameOver(bugs, i);
		}
	}

	private void gameOver(List<Bug> bugs, int i) {
		if (bugs.get(i).getX() < 261 && bugs.get(i).getY() > 511) {
		}
	}

	private void bugAlive(List<Bug> bugs, int i) {
		if (bugs.get(i).isAlive())
			bugs.get(i).move();
	}

	private void bugGen(List<Bug> bugs, int i) {

		bugs.get(i).gen();
		gentime = 1f;

	}
}