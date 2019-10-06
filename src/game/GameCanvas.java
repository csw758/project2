package game;

import java.awt.Canvas;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameCanvas extends Canvas {
	/*
	 * 이 캔버스로 frame에서 띄우고 띄우고자 하는 canvas를 여기에 부착시켜 띄우기.
	 * 
	 * switching을 이곳에서 진행?
	 * 
	 * 
	 * 
	 */
	GameFrame frame;

	public GameCanvas() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				frame.switchCanvas(new IntroCanvas());
			}
		});

	}

}