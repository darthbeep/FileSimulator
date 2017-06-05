import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class DFile extends FileNode {

	private String text;
	
	
	public DFile() {
		// TODO Auto-generated constructor stub
	}
	
	public DFile(FileNode f) {
		super(f.getName(), f.getParent(), "file");
		setText("");
	}

	public DFile(String n, FileNode p) {
		super(n, p, "file");
		setText("");
	}

	public void write() {
		try {
			PrintWriter file = new PrintWriter(new File(this + ".txt"));
			//file.flush();
			file.print(text);
			file.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void appendText(String s) {
		text+=s;
		write();
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
		write();
	}
	
	
}
