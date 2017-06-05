import java.awt.event.*;

import javax.swing.*;

public class KeyListenClass extends JPanel implements KeyListener, ActionListener {
	
	Timer t = new Timer(5, this);
	
	
	public KeyListenClass() {
		t.start();
		
		setFocusable(true);
		addKeyListener(this);
		setFocusTraversalKeysEnabled(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//System.out.println(1);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println(2);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println(3);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println(4);
	}
	
}