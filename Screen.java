import java.awt.*;
import java.io.*;
import java.util.*;

import javax.swing.JFrame;

public class Screen {

	private Directory directory;
	private int width;
	private int height;
	private String title;
	public DFolder folder;
	private DFile file;
	private String ScreenType;
	public JFrame window;
	public static boolean canImport = true;
	
	public Screen(int w, int h, DFolder f) {
		directory = new Directory();
		width = w;
		height = h;
		folder = f;
		if (folder == null) folder = directory.getRoot();
		title = folder.getName();
		ScreenType = "folder";
		if (canImport)importDirectory(folder.getName());
		canImport = false;
	}
	
	public Screen(DFile d) {
		if (d == null) d = new DFile("Filenamehere", null);
		width = 800;
		height = 400;
		file = d;
		title = d.getName();
		ScreenType = "file";
		
	}
	
	public Screen() {
		this(1200, 800, (DFolder) null);
	}
	
	public Screen(boolean b) {
		width = 800;
		height = 400;
		title = "Account";
		window = new JFrame();
		createWindow();
		Account a = new Account(this);
		window.add(a);
		window.setVisible(true);
	}
	
	public static void removeWindow(Screen s) {
		s.window.dispose();
	}
	
	public void createWindow() {
		window = new JFrame();
		window.setSize(width, height);
		window.setTitle(title);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//window.setBackground(Color.CYAN);
		//window.setVisible(true);
		//window.setFocusable(true);
	}
	
	public void run() {
		displayFolder();
	}

	public void displayFolder() {
		DisplayFolder d = new DisplayFolder(folder, height);
		//KeyTestClass d = new KeyTestClass(folder, height);
		//second d = new second();
		//second s = new second();
		//KeyListenClass k = new KeyListenClass();
		//window.add(d);
		TextField field = new TextField();
		field.setBounds(25, 55, 200, 35);
		window.add(d);
		//d.add(field);
		//window.add(e);
		window.setVisible(true);
		//while (true) {
			d.repaint();
			window.setVisible(true);
		//}
	}
	
	public void setupD() {
		folder.addFolder("test");
		folder.addFolder("ing");
		folder.addFile("is");
		folder.addFolder("not");
		folder.addFile("not");
		folder.addFile("very");
		folder.addFolder("fun");
		//*
		folder.addFile("but");
		folder.addFolder("I");
		folder.addFolder("do");
		folder.addFile("it");
		folder.addFile("anyway");
		folder.addFile("look");
		folder.addFile("mom");
		folder.addFolder("this");
		folder.addFolder("works");
		folder.getChild("test").addFolder("child");
		//*/
	}
	
	public static void setupFolder(DFolder d) {
		d.addFolder("test");
		d.addFile("ing");
		d.addFile("is");
		d.addFolder("not");
		d.addFile("not");
		d.addFile("very");
		d.addFolder("fun");
		//*
		d.addFile("but");
		d.addFolder("I");
		d.addFolder("do");
		d.addFile("it");
		//d.addFile("anyway");
		//d.addFile("look");
		//d.addFile("mom");
		//d.addFolder("this");
		//d.addFolder("works");
		d.getChild("test").addFolder("child");
	}
	
	public static void createNewFolderDisplay(DFolder d) {
		Screen s = new Screen(810, 400, d);
		s.createWindow();
		s.run();
	}
	
	public static void createNewFileDisplay(DFile d) {
		//System.out.println(d);
		Screen s = new Screen(d);
		s.createWindow();
		DisplayFile ayylmao = new DisplayFile(d);
		s.window.add(ayylmao);
		s.window.setVisible(true);
	}

	public void importDirectory(String filename) {
		try {
			Scanner scan = new Scanner(new File(filename+".txt"));
			while (scan.hasNextLine()) {
				String s = scan.nextLine();
				if (s.substring(0, 1).equals("#")) {
					Directory.addFile(s.substring(1), folder);
				}
				else Directory.addFolder(s, folder);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			PrintWriter p;
			PrintWriter p2;
			try {
				p = new PrintWriter(new File(filename+".txt"));
				p.close();
				Scanner scan = new Scanner(new File("account.txt"));
				String pr = "";
				while (scan.hasNext()) {
					pr += scan.nextLine();
					pr += "\n";
				}
				p = new PrintWriter("account.txt");
				p.write(pr+filename);
				p.close();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		//canImport = true;
		DFolder d = new DFolder("TestAccount", null);createNewFolderDisplay(d);
		
		//PrintWriter p = new PrintWriter(new File("data.txt"));
		//p.close();
		
		

		
		/*Screen s = new Screen(null);
		s.createWindow();
		DisplayFile d = new DisplayFile(s.file);
		s.window.add(d);
		s.window.setVisible(true);
		
		
		/*Screen s = new Screen(400,400,null);
		s.createWindow();
		second k = new second();
		s.window.add(k);
		k.repaint();
		s.window.setVisible(true);*/
		
		// TODO Auto-generated method stub
		//Screen s = new Screen(810, 400, (DFolder) null);
		//s.setupD();
		//System.out.println(s.folder.getChild("test")+ ", " + s.folder.getChild("test").getContents());
		//Screen s2 = new Screen(810, 400, s.folder.getChild("test"));
		//System.out.println(s2.folder);
		//s.createWindow();
		//s2.createWindow();
		//s.setupD();
		//s.run();
		//s2.run();
		//System.out.println(Fonts.);
		//System.out.println("test");
	}

}
