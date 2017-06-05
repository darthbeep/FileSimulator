import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Directory {

	private DFolder root;

	public Directory() {
		this("root");
	}

	public Directory(String rName) {
		root = new DFolder(rName, null);
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Directory d = new Directory();
		d.addFolder("root", "test");
		System.out.println(d.getRoot().getChild("test").toString());
		System.out.println("test");
		d.addFolder(d.getRoot().getChild("test").toString(), "ing");
		System.out.println(d.findFile("ing"));
		d.addFolder(d.findFile("ing").get(0).toString(), "ayy");
		d.addFile(d.findFile("ayy").get(0).toString(), "lmao");
	}

	public ArrayList<FileNode> findFile(String d) {
		return findFileHelper(d, root, new ArrayList<FileNode>());
	}

	private ArrayList<FileNode> findFileHelper(String d, DFolder f, ArrayList<FileNode> a) {

		for (int i = 0; i < f.getContents().size(); i++) {
			if(f.getContents().get(i).getName().equals(d)) {
				a.add(f.getContents().get(i));
				return a;
			}
			//if (f.getContents().get(i).getType().equals("folder")) return findFileHelper(d,(DFolder) f.getContents().get(i), a);
		}
		for (int i = 0; i < f.getFolderList().size(); i++) {
			return findFileHelper(d, f.getFolderList().get(i), a);
		}
		return a;
	}


	public void addFolder(String path, String child) {
		ArrayList<String> pathway = new ArrayList<String>();
		int s = 0;
		for (int i = 0; i < path.length()-1; i++) {
		
			if (path.substring(i, i+1).equals("/")) {
				pathway.add(path.substring(s, i));
				s = i + 1;
			}
		}
		pathway.add(path.substring(s));
		DFolder curr = root;
		for (int i = 0; i < pathway.size()-1; i++) {
			curr = curr.getChild(pathway.get(i+1));
		}
		curr.add(child);
	}

	public static void addFolder(String path, DFolder root) {
		ArrayList<String> pathway = new ArrayList<String>();
		int s = 0;
		for (int i = 0; i < path.length()-1; i++) {
		
			if (path.substring(i, i+1).equals("/")) {
				pathway.add(path.substring(s, i));
				s = i + 1;
			}
		}
		pathway.add(path.substring(s));
		DFolder curr = root;
		for (int i = 0; i < pathway.size()-2; i++) {
			curr = curr.getChild(pathway.get(i+1));
		}
		curr.addFolder(pathway.get(pathway.size()-1));
	}
	
	public static void addFile(String path, DFolder root) {
		ArrayList<String> pathway = new ArrayList<String>();
		int s = 0;
		for (int i = 0; i < path.length()-1; i++) {
		
			if (path.substring(i, i+1).equals("/")) {
				pathway.add(path.substring(s, i));
				s = i + 1;
			}
		}
		pathway.add(path.substring(s));
		DFolder curr = root;
		for (int i = 0; i < pathway.size()-2; i++) {
			curr = curr.getChild(pathway.get(i+1));
		}
		
		try {
			Scanner scan = new Scanner(new File(path + ".txt"));
			String pr = "";
			while(scan.hasNextLine()) pr += scan.nextLine() + "\n";
			curr.addFile(pathway.get(pathway.size()-1));
			curr.getFile(pathway.get(pathway.size()-1)).setText(pr);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void addFile(String path, String child) {
		ArrayList<String> pathway = new ArrayList<String>();
		int s = 0;
		for (int i = 0; i < path.length()-1; i++) {
		
			if (path.substring(i, i+1).equals("/")) {
				pathway.add(path.substring(s, i));
				s = i + 1;
			}
		}
		pathway.add(path.substring(s));
		DFolder curr = root;
		for (int i = 0; i < pathway.size()-1; i++) {
			curr = curr.getChild(pathway.get(i+1));
		}
		curr.addFile(child);
	}

	public DFolder getRoot() {
		return root;
	}

	public void setRoot(DFolder root) {
		this.root = root;
	}

}
