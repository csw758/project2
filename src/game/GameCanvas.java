package game;

import java.awt.Canvas;

public class GameCanvas extends Canvas {
	/*
	 * 이 캔버스로 frame에서 띄우고 띄우고자 하는 canvas를 여기에 부착시켜 띄우기.
	 * 
	 * switching을 이곳에서 진행.
	 * 
	 * 
	 * 
	 */

	Canvas canvas;
	GameFrame frame;

	public GameCanvas() {
		frame = GameFrame.getInstance();
		canvas = new IntroCanvas();
		frame.remove(frame.canvas);
		frame.add(canvas);
	}
}