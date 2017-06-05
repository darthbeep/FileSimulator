import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

public class Account extends JComponent implements ActionListener, MouseListener {

	private Timer t = new Timer(5, this);
	
	private String username, password;
	private TextField fUsername, fPassword;
	private ArrayList<String> accs;
	private DFolder temp;
	public Font menlo14, menlo30, menlo48;
	private int heightchange;

	private boolean scroll;
	private int origheight;

	private int maxHeight;

	private int height;
	private Screen screen;
	
	
	public Account(Screen sc) {
		t.start();
		addMouseListener(this);
		username = "";
		password = "";
		fUsername = new TextField();
		fPassword = new TextField();
		this.add(fUsername);
		//this.add(fPassword);
		accs = new ArrayList<String>();
		try {
			Scanner scan = new Scanner(new File("account.txt"));
			while (scan.hasNextLine()) {
				String s = scan.nextLine();
				accs.add(s);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			
			try {
				PrintWriter p;
				p = new PrintWriter(new File("account.txt"));
				p.close();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
		Font m = null;
		try {
			m = Font.createFont(Font.TRUETYPE_FONT, new File("menlo.ttf"));
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		menlo14 = m.deriveFont(14f);
		menlo30 = m.deriveFont(30f);
		menlo48 = m.deriveFont(48f);
		
		scroll = false;
		origheight = 0;
		maxHeight = accs.size() * 50;
		height = 300;
		screen = sc;
	}
	

	
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, 800, 400);
		g2.setColor(Color.BLACK);
		//Create new
		g2.setFont(menlo30);
		g2.drawString("Create new account", 0, 30);
		
		
		Rectangle2D.Double splitv = new Rectangle2D.Double(400, 0, 10, 400);
		Rectangle2D.Double splith = new Rectangle2D.Double(0, 40, 800, 10);
		g2.fill(splitv);
		g2.fill(splith);
		
		g2.drawString("Folder system name:", 30, 100);
		fUsername.setBounds(100, 135, 210, 30);
		fUsername.setVisible(true);
		
		//g2.drawString("Password:", 10, 150);
		fPassword.setBounds(185, 125, 200, 30);
		//fPassword.setVisible(true);
		
		Rectangle2D.Double createb = new Rectangle2D.Double(50, 200, 300, 100);
		g2.fill(createb);
		g2.setColor(Color.WHITE);
		g2.setFont(menlo48);
		g2.drawString("Create", 110, 275);
		
		// Open previous
		g2.setColor(Color.BLACK);
		g2.drawRect(420, 70, 370, 300);
		g2.setFont(menlo30);
		for (int i = 0; i < accs.size(); i++) {
			if (accs.size() > 6) g2.drawRect(420, 70 + (i*50) + heightchange, 360, 50);
			else g2.drawRect(420, 70 + (i*50) + heightchange, 370, 50);
			g2.drawString(accs.get(i), 440, 110 + i*50 + heightchange);
		}
		g2.clearRect(420, 371, 380, 10);
		g2.clearRect(420, 0, 380, 40);
		g2.clearRect(420, 50, 380, 20);
		g2.drawString("Choose existing", 420, 30);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Screen s = new Screen(true);
		
	}
	
	public static String encrypt(String s) {
		String ans = s+"ayylmao";
		return ans;
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getX() > 50 && e.getX()< 350
				&& e.getY() > 200 && e.getY() < 300) {
			username = fUsername.getText();
			password = encrypt(fPassword.getText());
			DFolder d = new DFolder(username, null);
			Screen.createNewFolderDisplay(d);
			Screen.removeWindow(screen);
		}
		//g2.drawRect(420, 70, 370, 300);
		if (e.getX() > 420 && e.getX() < 420 + 370 &&
				e.getY() > 70 && e.getY() < 70 + 300) {
			for (int i = 0; i < accs.size(); i++) {
				//g2.drawRect(420, 70 + (i*50) + heightchange, 360, 50);
				if (e.getY() > 70 + (i * 50) + heightchange && e.getY() < 120 + i*50 + heightchange) {
					DFolder d = new DFolder(accs.get(i), null);
					Screen.createNewFolderDisplay(d);
					Screen.removeWindow(screen);
				}
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getX() > 780 && e.getX() < 790) {
			//System.out.println(e.is);
			scroll = true;
			origheight = e.getY();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if (scroll) {
			scroll = false;
			int change = origheight - e.getY();
			//System.out.println(change);
			heightchange = heightchange + change;
			if (-heightchange > maxHeight - height) {
				heightchange = -(maxHeight - height);
			}
			if (-heightchange < 0) {
				heightchange = 0;
			}
			//heightchange = -heightchange;
			//surround = new Rectangle2D.Double(xCors.get(surroundi), yCors.get(surroundi) + heightchange, 50, 50);
			origheight = heightchange;
			//currentTop = currentTop - heightchange;
			//currentBottom = currentBottom - heightchange;
		}
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
