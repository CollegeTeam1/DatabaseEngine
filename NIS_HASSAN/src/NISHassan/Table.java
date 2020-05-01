package NISHassan;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;

public class Table implements Serializable{

	String strTableName;
	String filename;
	int noOfPages=0;
	transient Vector<Page> pages = new Vector<Page>();
	ArrayList<String> pageNames;
	//Hashtable<String, Comparable[]> pageMinMax;

	public Table(String strTableName) {
		this.strTableName = strTableName;
		createFile(strTableName);
		pageNames= new ArrayList<String>();
		
	}

	public String getName() {
		return this.strTableName;
	}

	public Vector<Page> getPages() {
		return this.pages;
	}
	public ArrayList<String> getPageNames() {
		return pageNames;
	}

	public void setPageNames(ArrayList<String> pageNames) {
		this.pageNames = pageNames;
	}
	
	public void createFile(String tableName) {
		this.filename = tableName  + ".ser";

		try {
			FileOutputStream fileOut = new FileOutputStream(filename, false);

			fileOut.close();
		} catch (IOException i) {
			i.printStackTrace();
		}

	}

	public String getFilename() {
		return filename;
	}
	
	public int getnoOfPages() {
		return this.noOfPages;
	}
	
	public void appendPages() {
		this.noOfPages++;
	}
	
	public void decPages() {
		this.noOfPages--;
	}
	
	


	public static void main(String[] args) {
//		String strTableName = "Student";
//		Hashtable htblColNameType = new Hashtable();
//		htblColNameType.put("id", "java.lang.Integer");
//		htblColNameType.put("name", "java.lang.String");
//		htblColNameType.put("gpa", "java.lang.double");
//		writemetadataCSV(strTableName, "id", htblColNameType);
//		
		Integer x = new Integer(1);
		String f = "java.lang.Integer";
		System.out.println(x.getClass().getName().equals(f));
	}

}
