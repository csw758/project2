package game;

import java.awt.Canvas;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameFrame extends Frame {

	Canvas canvas;
	Canvas tempcanvas;

	private static class LazyHolder {
		public static final GameFrame FRAMEINSTANCE = new GameFrame();
	}

	public static GameFrame getInstance() {
		return LazyHolder.FRAMEINSTANCE;
	}

//	 싱글톤 (single ton) 디자인 패턴
//	 GamePrj는 됬는데
//	  이 프로젝트에서는 초기 생성시 frame이 할당되지 않음.
//	  흠..
//	 스레드 동기화를 하여 확실하게 해당 자원을 할당?

//	
//	private static GameFrame gameframe = new GameFrame();
//	
//	public static GameFrame getInstance() {
////		if(gameframe == null)
////			gameframe = new GameFrame();
//		return gameframe;
//	}

	private GameFrame() {
		setBounds(0, 0, 850, 862);
		canvas = new IntroCanvas();
		add(canvas);
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}// 생성자 종료

	public void switching(Canvas fromcanvas, Canvas tocanvas) {
		remove(canvas);
		tempcanvas = tocanvas;
//		gameStart.setVisible(false);
//		gameMethod.setVisible(false);
//		gameExit.setVisible(false);

		add(tempcanvas);
		tempcanvas.setFocusable(true);
		tempcanvas.requestFocus();
		revalidate();
	}

	public void switchCanvas(Canvas tocanvas) {
		System.out.println("Switching");
		remove(canvas);
		canvas = tocanvas;
		add(canvas);
		canvas.setFocusable(true);
		canvas.requestFocus();
		revalidate();
	}
}