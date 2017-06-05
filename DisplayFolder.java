import java.awt.*;
import java.awt.event.*;

import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.*;


import java.awt.event.*;

public class DisplayFolder extends JComponent implements ActionListener, KeyListener, MouseListener {

	private Timer t = new Timer (5, this);
	private DFolder folder;
	private int size;
	private ArrayList<Integer> xCors;
	private ArrayList<Integer> yCors;
	private ArrayList<String> names;
	private ArrayList<String> types;
	private int currentTop;
	private int height;
	private int maxHeight;
	private int heightchange;
	private int origheight;
	private boolean scroll;
	public static final File FOLDERIMAGE = new File("foldericon.png");
	public static final File FILEIMAGE = new File("fileimage.png");
	public static Image FileImage;
	public static Image FolderImage;
	public static Font comicsans;
	public static Font menlo;
	boolean typeNewFileName;
	private int createmode; //0 is defealt, 1 is creating folder, 2 is creating file
	private boolean highlight;
	private Rectangle2D.Double surround;
	private Rectangle2D.Double sidebar;
	private int surroundi;
	private String nameString;
	private TextField field;



	public DisplayFolder(DFolder d, int h) {
		t.start();

		//setFocusable(true);
		//addKeyListener(this);
		addMouseListener(this);
		//setFocusable(true);
		//setFocusTraversalKeysEnabled(true);
		folder = d;
		size = d.getContents().size();

		xCors = new ArrayList<Integer>();
		yCors = new ArrayList<Integer>();
		names = new ArrayList<String>();
		types = new ArrayList<String>();

		for (int i = 0; i < size; i++) {
			if ((i*4) > size-1) break;

			for (int j = 0; j < 4; j++) {

				if ((i*4) + j > size-1) break;

				yCors.add(i * 200 + 100);
				xCors.add(j * 200 + 75);
				names.add(folder.getContents().get(i*4 + j).getName());
				types.add(folder.getContents().get((i*4) + j).getType());

			}

		}

		currentTop = h;
		height = h;
		maxHeight =(int) (Math.ceil(size/(double)4)*200);
		//System.out.println(maxHeight + ", " + currentTop);
		heightchange = 0;
		origheight = 0;
		scroll = false;
		highlight = false;

		try {
			FileImage = ImageIO.read(FILEIMAGE).getScaledInstance(50, 50, Image.SCALE_DEFAULT);
			FolderImage = ImageIO.read(FOLDERIMAGE).getScaledInstance(50, 50, Image.SCALE_DEFAULT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Font c = Font.createFont(Font.TRUETYPE_FONT, new File("comicsans.ttf"));
			comicsans = c.deriveFont(14f);
			Font m = Font.createFont(Font.TRUETYPE_FONT, new File("menlo.ttf"));
			menlo = m.deriveFont(14f);


		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		typeNewFileName = false;
		//Font.cre
		createmode = 0;
		surroundi = 0;
		nameString = "";

		field = new TextField();
		//field.setBounds(75+95+50, 25, 200, 35);
		field.setVisible(false);
		field.setFont(menlo);
		this.add(field);
	}


	public void paintComponent(Graphics g) {
		//xCors.set(0, xCors.get(0) + 20);


		Graphics2D g2 = (Graphics2D) g;
		g2.setFont(menlo);
		for (int i = 0; i < size; i++) {
			Image img = null;
			try {
				if (types.get(i).equals("folder")) img = FolderImage;
				else img =FileImage;

			}
			catch(Exception e) {
				System.out.println("no");
			}
			g2.drawImage(img, xCors.get(i), yCors.get(i) + heightchange, null);
			g2.drawString(names.get(i), xCors.get(i) + 10, yCors.get(i) + 60 + heightchange);

		}
		if (highlight) g2.draw(surround);

		Rectangle2D.Double sideh = new Rectangle2D.Double(800, 0, 10, height);
		double sideheight = ((double)((double)currentTop/maxHeight)) * height;

		sidebar = new Rectangle2D.Double(800, -heightchange * (double) ((double) height / (double) maxHeight), 10, sideheight);
		g2.draw(sideh);
		g2.fill(sidebar);
		g2.draw(new Rectangle2D.Double(475, 500, 50, 50));
		// Starting the button for new stuff here
		Font f = menlo.deriveFont(25f);
		g2.setFont(f);
		if (createmode == 0) {
			Rectangle2D.Double folderSurround = new Rectangle2D.Double(75, 25 + heightchange, 260, 35);
			Rectangle2D.Double fileSurround = new Rectangle2D.Double(475, 25 + heightchange, 225, 35);
			g2.fill(folderSurround);
			g2.fill(fileSurround);
			g2.setColor(Color.WHITE);			
			g2.drawString("Create new folder", 75, 55 + heightchange);
			g2.drawString("Create new file", 475, 55 + heightchange);
			typeNewFileName = true;
			nameString = "";
		}
		else if (createmode == 1 || createmode == 2) {
			Rectangle2D.Double namesuggest = new Rectangle2D.Double(75, 25+heightchange, 95, 35);
			g2.fill(namesuggest);
			Rectangle2D.Double namespace = new Rectangle2D.Double(75+95+50, 25, 200, 35);
			Rectangle2D.Double createarea = new Rectangle2D.Double(75+95+50+200+75, 25+heightchange, 95, 35);
			g2.fill(createarea);
			//g2.draw(namespace);
			field.setBounds(75+95+50, 25+heightchange, 200, 35);
			field.setVisible(true);


			//g2.drawString(nameString, 75+95+50, 55);
			g2.setColor(Color.WHITE);
			g2.drawString("Name:", 80, 55+heightchange);
			g2.drawString("Create", 75+95+50+200+75, 55+heightchange);


		}


	}


	public void createNewFolder() {
		try {
			String s = field.getText();
			folder.addFolder(s);
			int i = (size/4);
			int j = (size%4);
			yCors.add(i * 200 + 100);
			xCors.add(j * 200 + 75);
			names.add(folder.getContents().get(size).getName());
			types.add(folder.getContents().get(size).getType());
			field.setText("");
			field.setVisible(false);
			surround = new Rectangle2D.Double(xCors.get(size), yCors.get(size) + heightchange, 50, 50);
			size++;
			maxHeight =(int) (Math.ceil(size/(double)4)*200);
			Scanner scan = new Scanner(new File(folder.getFileName()+".txt"));
			String pr = "";
			while (scan.hasNext()) {
				pr += scan.nextLine();
				pr += "\n";
			}
			PrintWriter p = new PrintWriter(folder.getFileName()+".txt");
			p.write(pr + folder.getContents().get(size-1).toString());
			p.close();
		}
		catch (Exception e) {
			System.out.println("lol you messed up");
		}
	}

	public void createNewFile() {
		try {
			String s = field.getText();
			folder.addFile(s);
			int i = (size/4);
			int j = (size%4);
			yCors.add(i * 200 + 100);
			xCors.add(j * 200 + 75);
			names.add(folder.getContents().get(size).getName());
			types.add(folder.getContents().get(size).getType());
			field.setText("");
			field.setVisible(false);
			surround = new Rectangle2D.Double(xCors.get(size), yCors.get(size) + heightchange, 50, 50);
			size++;
			maxHeight =(int) (Math.ceil(size/(double)4)*200);
			Scanner scan = new Scanner(new File(folder.getFileName()+".txt"));
			String pr = "";
			while (scan.hasNext()) {
				pr += scan.nextLine();
				pr += "\n";
			}
			PrintWriter p = new PrintWriter(folder.getFileName()+".txt");
			p.write(pr + "#" + folder.getContents().get(size-1).toString());
			p.close();
			// TODO Auto-generated catch block

		}
		catch (Exception e) {
			System.out.println("lol you messed up again");
		}
	}




	@Override
	public void mouseClicked(MouseEvent e) {
		boolean canOpen = highlight;
		highlight = false;

		//System.out.println("XCors" + e.getX() + ", " + e.getY());
		for (int i = 0; i < size; i++) {
			if (e.getX() > xCors.get(i) && e.getX() < xCors.get(i) + 50 && 
					e.getY() > yCors.get(i) + heightchange && e.getY() < yCors.get(i) + heightchange + 50) {
				if (surroundi == i && folder.getContents().get(i).getType().equals("folder") && canOpen)
					Screen.createNewFolderDisplay(folder.getChild(folder.getContents().get(i).getName()));
				try {
					if (surroundi == i && folder.getContents().get(i).getType().equals("file") && canOpen) {
						Screen.createNewFileDisplay(folder.getFile(folder.getContents().get(i).getName()));
					}
				}
				catch (Exception ex) {
					System.out.println("why");
				}
				highlight = true;
				surround = new Rectangle2D.Double(xCors.get(i), yCors.get(i) + heightchange, 50, 50);
				surroundi = i;
			}
		}

		if (createmode == 0 &&
				e.getX() > 75 && e.getX() < 75 + 260  && 
				e.getY() > 25 + heightchange && e.getY() < 25 + heightchange + 35) {
			createmode = 1;
		}
		//Rectangle2D.Double fileSurround = new Rectangle2D.Double(475, 25 + heightchange, 220, 35);

		else if (createmode == 0 &&
				e.getX() > 475 && e.getX() < 475 + 225 && 
				e.getY() > 25 + heightchange && e.getY() < 25 + heightchange + 35) {
			createmode = 2;

		}



		//Rectangle2D.Double createarea = new Rectangle2D.Double(75+95+50+200+75, 25+heightchange, 95, 35);
		else if (createmode == 1 &&
				e.getX() > 75+95+50+200+75 && e.getX() < 75+95+50+200+75 + 95  && 
				e.getY() > 25 + heightchange && e.getY() < 25 + heightchange + 35) {
			createmode = 0;
			createNewFolder();
		}
		else if (createmode == 2 &&
				e.getX() > 75+95+50+200+75 && e.getX() < 75+95+50+200+75 + 95  && 
				e.getY() > 25 + heightchange && e.getY() < 25 + heightchange + 35) {
			createmode = 0;
			createNewFile();
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getX() > 790) {
			//System.out.println(e.is);
			scroll = true;
			origheight = e.getY();


		}


	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("Height: "+(maxHeight-height) + ", heightchange: " + heightchange);
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
			surround = new Rectangle2D.Double(xCors.get(surroundi), yCors.get(surroundi) + heightchange, 50, 50);
			origheight = heightchange;
			//currentTop = currentTop - heightchange;
			//currentBottom = currentBottom - heightchange;
		}
	}

	public void type(KeyEvent key) {
		if (key.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
			if (!nameString.isEmpty()) nameString = nameString.substring(0, nameString.length()-1);
		}
		else if (key.getKeyCode() == KeyEvent.VK_SHIFT);
		else {
			nameString += key.getKeyChar();
		}
		System.out.println(nameString);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		//System.out.println(4);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		//System.out.println(5);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println(1);

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println(2);
		if (typeNewFileName) type(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println(3);
	}

	//@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		repaint();
		//System.out.println(e.toString());
	}

	public void actionPerformed(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println(6);
	}

}
