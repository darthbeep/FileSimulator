import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;

import javax.swing.*;

public class second extends JComponent implements ActionListener, KeyListener {

	Timer t = new Timer (5, this);
	double x = 50, y = 50, velx = 0, vely = 0;
	Color c = Color.BLACK;
	String s = "";
	
	public second() {
		t.start();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(true);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(c);
		Ellipse2D.Double e = new Ellipse2D.Double(x, y, 40, 40);
		//g2.fill(e);
		//g2.draw((Shape) ayyLmao());
		//g2.
		Font f = new Font("LucidaBright", Font.BOLD, 40);
		g2.setFont(f);
		g2.drawString(s,(float) x, (float) y);
		
		//g2.draw(e);
	}
	

	public void up() {
		velx = 0;
		vely = -1;
	}
	
	public void down() {
		velx = 0;
		vely = 1;
	}
	
	public void left() {
		velx = -1;
		vely = -0;
	}
	
	public void right() {
		velx = 1;
		vely = 0;
	}
	
	public TextArea ayyLmao() {
		TextArea t = new TextArea();
		t.setText("AyyLmao");
		
		return t;
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void type(KeyEvent key) {
		if (key.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
			if (!s.isEmpty()) s = s.substring(0, s.length()-1);
		}
		else if (key.getKeyCode() == KeyEvent.VK_ENTER) {
			c = new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
		}
		else if (key.getKeyCode() == KeyEvent.VK_SHIFT);
 		else {
			s += key.getKeyChar();
		}
	}


	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getKeyChar());
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_UP) up();
		else if (code == KeyEvent.VK_DOWN) down();
		else if (code == KeyEvent.VK_LEFT) left();
		else if (code == KeyEvent.VK_RIGHT) right();
		else type(e);
		//if (code == KeyEvent.VK_SPACE) c = new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		velx = 0;
		vely = 0;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		repaint();
		x += velx;
		y += vely;
	}

}
