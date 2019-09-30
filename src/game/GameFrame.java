package game;

import java.awt.Canvas;
import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class GameFrame extends Frame implements MouseMotionListener{
   Canvas gamecanvas;

   public GameFrame() {
	  addMouseMotionListener(this);
	   
      setBounds(0, 0, 850, 862);

      gamecanvas = new GameCanvas();
      add(gamecanvas);
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

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}