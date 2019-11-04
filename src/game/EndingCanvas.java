package game;

import java.awt.AlphaComposite;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.Timer;

public class EndingCanvas extends Canvas implements ActionListener {
	GameFrame frame;
	Image myImage = new ImageIcon("res/gameover.png").getImage();

	Timer timer = new Timer(10, this);

	private float alpha = 0f;

	public EndingCanvas() {
		frame = GameFrame.getInstance();
		timer.start();
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				frame.switchCanvas(new IntroCanvas());
				System.out.println("tointro");
			}
		});

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.switchCanvas(new IntroCanvas());
			}

			@Override
			public void mousePressed(MouseEvent e) {

			}
		});

	}

	public void actionPerformed(ActionEvent e) {
		alpha += +0.15f;
		if (alpha >= 1) {
			alpha = 1;
			timer.stop();
		}
		repaint();
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;

		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		g2d.drawImage(myImage, 0, 0, 850, 862, 0, 0, 850, 862, this);

		Font font = new Font("궁서체", Font.BOLD, 32);

		g.setFont(font);
		g.setColor(Color.white);
		g.drawString("Press any key", 300, 400);
	}

}
