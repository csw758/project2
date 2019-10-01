package game;

import java.awt.Canvas;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameFrame extends Frame {
	Canvas gamecanvas;

	public GameFrame() {
		setBounds(0, 0, 850, 862);

		gamecanvas = new GameCanvas();
		add(gamecanvas);
		((GameCanvas) gamecanvas).start();
		gamecanvas.setFocusable(true);
		gamecanvas.requestFocus();
		setVisible(true);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
	}

}