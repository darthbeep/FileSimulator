import java.io.File;
import java.util.*;

public class DFolder extends FileNode {

	private ArrayList<FileNode> contents;
	private ArrayList<DFolder> folderList;
	private ArrayList<DFile> fileList;
	
	public DFolder() {
		super();
		setup();
	}

	public DFolder(String n, FileNode p) {
		super(n, p, "folder");
		setup();
	}
	
	public DFolder(String n, FileNode p, String t) {
		super(n, p, t);
		setup();
	}
	
	public DFolder(FileNode f) {
		super(f.getName(),f.getParent(), "folder");
		setup();
	}

	private void setup() {
		contents = new ArrayList<FileNode>();
		folderList = new ArrayList<DFolder>();
		fileList = new ArrayList<DFile>();
	}
	
	
	
	public void add(FileNode f) {
		
		f.setParent(this);
		//System.out.println(f);
		setStorage(new File(f.toString()));
		//storage.mkdir();
		new File(f.toString()).mkdir();
		contents.add(f);
		if (f.getType().equals("folder")) folderList.add(new DFolder(f));
		else fileList.add(new DFile(f));
	}
	
	public void add(DFolder f) {
		f.setParent(this);
		setStorage(new File(f.toString()));
		new File(f.toString()).mkdir();
		contents.add(f);
		if (f.getType().equals("folder")) folderList.add(f);
		else fileList.add(new DFile(f));
	}
	
	public void addFile(String s) {
		DFile f = new DFile(s, this);
		contents.add(f);
		fileList.add(f);
	}
	
	public void addFolder(String s) {
		DFolder f = new DFolder(s, this);
		contents.add(f);
		folderList.add(f);
	}
	
	public void add(String s) {
		FileNode f = new FileNode(s, this);
		contents.add(f);
		if (f.getType().equals("folder")) folderList.add(new DFolder(f));
		else fileList.add(new DFile(f));
	}
	
	public DFolder getChild(String s) {
		for (int i = 0; i < folderList.size(); i++) {
			if (folderList.get(i).getName().equals(s))
				return folderList.get(i);
		}
		return null;
	}
	
	public DFile getFile(String s) {
		for (int i = 0; i < fileList.size(); i++) {
			if (fileList.get(i).getName().equals(s))
				return fileList.get(i);
		}
		return null;
	}
	
	public int getSize() {
		return contents.size();
	}



	public ArrayList<FileNode> getContents() {
		return contents;
	}

	public void setContents(ArrayList<FileNode> contents) {
		this.contents = contents;
	}
	
	public ArrayList<DFolder> getFolderList() {
		return folderList;
	}
	public ArrayList<DFile> getFileList() {
		return fileList;
	}
}
