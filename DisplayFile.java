import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.*;

import javax.swing.*;

public class DisplayFile extends JComponent implements ActionListener, MouseListener {

	Timer t;
	private DFile file;
	private String text;
	public static Font menlo;
	private TextArea field;


	public DisplayFile(DFile d) {
		// TODO Auto-generated constructor stub
		//System.out.println(2);
		t = new Timer(5, this);
		
		addMouseListener(this);
		//System.out.println(d);
		file = d;
		text = d.getText();
		//System.out.println(1);
		try {
			Font m = Font.createFont(Font.TRUETYPE_FONT, new File("menlo.ttf"));
			menlo = m.deriveFont(30f);


		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		field = new TextArea();
		field.setText(text);
		field.setBounds(0, 50, 800, 350);
		field.setFont(menlo);
		field.setVisible(true);
		this.add(field);


	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Rectangle2D.Double savesurround = new Rectangle2D.Double(0, 0, 200, 50);
		g2.fill(savesurround);
		g2.setFont(menlo);
		g2.setColor(Color.WHITE);
		g2.drawString("Save", 50, 40);
		
	}
	
	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getX() > 0 && e.getX() < 200 && 
				e.getY() > 0 && e.getY() < 50) {
			//field.setText(field.getText() + "ayy lmao\n");
			text = field.getText();
			file.setText(text);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		repaint();
	}


}
