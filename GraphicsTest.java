import java.awt.*;
import javax.swing.*;;

public class GraphicsTest {

	public GraphicsTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JFrame window = new JFrame();
		window.setSize(600, 600);
		window.setTitle("look mom I'm a window");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//window.setVisible(true);
		second s = new second();
		Rectangle rect = new Rectangle(5, 10, 100, 100);
		ComponentTest ct = new ComponentTest();
		//KeyTestClass k = new KeyTestClass();
		//DFolder d = new DFolder("Display folder", null);
		//Screen.setupFolder(d);
		//Screen.createNewFolderDisplay(d);
		Screen screen = new Screen(200,200,null);
		screen.createWindow();
		DisplayFolder d = new DisplayFolder(screen.folder, 200);
		screen.window.add(d);
		d.repaint();
		screen.window.setVisible(true);
		//ComponentTest ct2 = new ComponentTest();
		//Graphics2D g = new Graphics2D();
		//ct.paintc
		//window.add(ct);
		//window.add(d);
		//window.setVisible(true);
		window.add(s);
		window.setVisible(true);
		/*String fonts[] = 
			      GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

			    for ( int i = 0; i < fonts.length; i++ )
			    {
			      System.out.println(fonts[i]);
			    }*/
		//while (true) {
			//if (KeyTest.isWPressed()) System.out.println(1);
			//ct.paintComponent(null);
			//window.add(ct);
			
			//window.add(ct2);
			//window.setVisible(true);
			//JFrame.ABORT;
			//window = new JFrame();
			//window.setSize(640, 480);
			//window.setTitle("test2");
			//SwingUtilities.updateComponentTreeUI(ct);
			//if (KeyTest.isWPressed())
			//ct.repaint();
			//s.repaint();
			//if (KeyTest.isWPressed()) System.out.println(1);
			window.setVisible(true);
			//if (IsKeyPressed.isWPressed())
			//ct.paintComponent(rect);
			//System.out.println(1);
			
		//}
	}

}
