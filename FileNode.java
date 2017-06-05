import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileNode {
	
	private String name;
	private String type;
	private FileNode parent;
	private File storage;
	private boolean highlighted;
	private int id;
	private static ArrayList<String> namesUsed = new ArrayList<String>();
	private static ArrayList<Integer> idNum = new ArrayList<Integer>();
	
	

	public FileNode() {
		this("", null, "folder");
	}
	
	public FileNode(String n, FileNode p) {
		// TODO Auto-generated constructor stub
		this(n, p, "folder");
	}
	
	public FileNode(String n, FileNode p, String t) {
		// TODO Auto-generated constructor stub
		name = n;
		parent = p;
		type = t;
		highlighted = false;
		if(type.equals("folder")) {
			storage = new File(this.toString());
			//storage.mkdir();
			new File(this.toString()).mkdir();
		}
		else if(type.equals("file")) {
			storage = new File(this.toString()+".txt");
			try {
				new FileWriter(this.toString()+".txt");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}
		if (namesUsed.contains(name)) {
			int temp = namesUsed.indexOf(name);
			idNum.set(temp, idNum.get(temp) + 1);
			id = idNum.get(temp);
		}
		else {
			id = 0;
			namesUsed.add(name);
			idNum.add(0);
		}
		
	}
	
	public FileNode(String n, FileNode p, String t, int i) {
		name = n;
		parent = p;
		type = t;
		highlighted = false;
		if(type.equals("folder")) {
			storage = new File(this.toString());
			//storage.mkdir();
			new File(this.toString()).mkdir();
		}
		else if(type.equals("file")) {
			storage = new File(this.toString()+".txt");

		}
		id = i;
	}
	
	public static void main(String[] args) throws IOException {
		FileNode a = new FileNode("a", null);
		FileNode b = new FileNode("b", a);
		System.out.println(b);
		FileNode c = new FileNode("c", b);
		FileNode d = new FileNode("d", b);
		FileNode e = new FileNode("e", d);
		d.setName("test");
		System.out.println(d);
		DFolder f = new DFolder("Folder",e);
		FileNode g = new DFolder("Folder2", f);
		System.out.println(g);
		DFolder h = new DFolder("Folder3",d);
		d.setName("test2");
		((DFolder) g).add(new FileNode("Sub",d));
		/*FileNode container = new FileNode("container", null, "folder");
		DFolder r = new DFolder("root", container, "folder");
		DFolder p1 = new DFolder("parent", null);
		DFolder p2 = new DFolder("parent2", null);
		DFolder c1 = new DFolder("child1", null);
		DFolder c2 = new DFolder("child2", null);
		DFolder c3 = new DFolder("child3", null);
		r.add(p1);
		r.add(p2);
		p1.add(c1);
		p1.add(c2);
		p2.add(c3);*/
		DFolder root = new DFolder("root", null);
		root.add("parent1");
		root.getChild("parent1").add("child1");
		root.getChild("parent1").add("child2");
		root.getChild("parent1").getChild("child1").add("grandchild");
		root.add("parent2");
		root.getChild("parent1").getChild("child2").add("fred");
		//DFile file = new DFile("file", root);
		//file.setText("The quick brown fox jumped over the\nlazy dog");
	}
	
	public String toString() {
		return toStringHelper("");
	}
	
	private String toStringHelper(String ans) {
		if (parent != null) {
			
			ans = parent.toStringHelper(ans) + "/"+ name;
		}
		else ans += name;
		return ans;
	}

	public String getName() {
		return name;
	}

	public void setName(String newName) {
		//this.name = name;
		name = newName;
		File store = storage;
		storage = new File(this.toString());
		//new File(this.toString()).mkdirs();
		store.renameTo(storage);
		//storage = store;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setParent(FileNode p) {
		parent = p;
	}
	
	public FileNode getParent() {
		return parent;
	}
	
	public File getStorage() {
		return storage;
	}

	public void setStorage(File storage) {
		this.storage = storage;
	}

	public boolean isHighlighted() {
		return highlighted;
	}

	public void setHighlighted(boolean highlighted) {
		this.highlighted = highlighted;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static ArrayList<String> getNamesUsed() {
		return namesUsed;
	}

	public static void setNamesUsed(ArrayList<String> namesUsed) {
		FileNode.namesUsed = namesUsed;
	}

	public static ArrayList<Integer> getIdNum() {
		return idNum;
	}

	public static void setIdNum(ArrayList<Integer> idNum) {
		FileNode.idNum = idNum;
	}
	
	public String getFileName() {
		String ans;
		FileNode curr = this;
		while (curr.parent != null) {
			curr = curr.getParent();
		}
		return curr.name;
	}

}
