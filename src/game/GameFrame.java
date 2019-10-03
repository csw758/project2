package game;

import java.awt.Canvas;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameFrame extends Frame {
	Canvas canvas;

	public static GameFrame frame;

	public static GameFrame getInstance() {
		if (frame != null) {
			return frame;
		}
		frame = new GameFrame();
		return frame;
	}

	public GameFrame() {
		setBounds(0, 0, 850, 862);
		canvas = new PlayCanvas();
		add(canvas);
		canvas.setFocusable(true);
		canvas.requestFocus();
		setVisible(true);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
	}// 생성자 종료

	public void switching(Canvas fromcanvas, Canvas tocanvas) {
		remove(fromcanvas);

//		gameStart.setVisible(false);
//		gameMethod.setVisible(false);
//		gameExit.setVisible(false);

		add(tocanvas);
		tocanvas.setFocusable(true);
		tocanvas.requestFocus();
		revalidate();
	}
}