package NISHassan;

import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Random;

public class DBAppTest {

	@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
	public static void main(String[] args) throws DBAppException {

		String[] names = { "Sofia", "Nahla", "Sara", "Leena", "Basil", "Ramzy", "Khalid", "Wael", "Jimmy", "Taha",
				"Mariam", "Nadeen", "Maysoon", "Sama", "Nourhan", "Mario" };
		double[] gpa = { 0.7, 1.0, 1.8, 1.81, 2.1, 2.5, 2.9, 3.0, 1.68, 1.75, 1.2, 0.8, 1.3, 1.9, 3.25, 2.02 };

		String strTableName = "selectSaraa";
		DBApp dbApp = new DBApp();
		Hashtable htblColNameValue = new Hashtable();
		Page p;

		Hashtable htblColNameType = new Hashtable();
//		htblColNameType.put("id", "java.lang.Integer");
//		htblColNameType.put("name", "java.lang.String");
//		htblColNameType.put("gpa", "java.lang.Double");
		// createTablePolygon(dbApp, strTableName);
		// dbApp.createTable(strTableName, "id", htblColNameType);

		// fillTable(dbApp, strTableName);
//		dbApp.createBTreeIndex(strTableName, "id");
		// dbApp.createBTreeIndex(strTableName, "name");
		// dbApp.createBTreeIndex(strTableName, "gpa");
		// selectTesting(dbApp, strTableName);
		// Hashtable htblColValue = new Hashtable();
		htblColNameValue.put("name", "Poly-b");
		dbApp.deleteFromTable(strTableName, htblColNameValue);
		printTable(strTableName, dbApp);
		// BPTree tree = dbApp.deserializeBPTree(strTableName + ",gpa.ser");
		BPTree tree2 = dbApp.deserializeBPTree(strTableName + ",name.ser");
		System.out.println();
		System.out.println(tree2);
		// System.out.println(tree2);
	}

	@SuppressWarnings("static-access")
	public static void printTable(String strTableName, DBApp dbApp) {

		System.out.println();
		System.out.println("TABLE NAME: " + strTableName);

		Page p1;
		ArrayList<String> arr = dbApp.getPagesNames(strTableName);
		for (int i = 0; i < dbApp.getTableSize(strTableName); i++) {
			System.out.println();

			System.out.println("PAGE " + i);
			// int index = dbApp.getPageIndexinArray(strTableName + i + ".ser",
			// strTableName);
			System.err.println(arr.get(i));
			String filename = arr.get(i);
			p1 = dbApp.deserializePage(filename);

			for (int j = 0; j < p1.row.size(); j++) {

				System.out.println(p1.row.get(j).tuples);
			}

			dbApp.serializePage(p1.getFilename(), p1);

		}

		System.out.println("MAX ROWS" + dbApp.getMaxRows());

	}

	@SuppressWarnings("rawtypes")
	public static void selectTesting(DBApp dbApp, String strTableName) throws DBAppException {

		SQLTerm[] arrSQLTerms = new SQLTerm[3];

		arrSQLTerms[0] = new SQLTerm();
		arrSQLTerms[1] = new SQLTerm();
		arrSQLTerms[2] = new SQLTerm();

		arrSQLTerms[0]._strTableName = strTableName;
		arrSQLTerms[0]._strColumnName = "name";
		arrSQLTerms[0]._strOperator = "=";

		arrSQLTerms[0]._objValue = "Nahla";
		arrSQLTerms[1]._strTableName = strTableName;
		arrSQLTerms[1]._strColumnName = "id";
		arrSQLTerms[1]._strOperator = ">=";
		arrSQLTerms[1]._objValue = 177;

		arrSQLTerms[2]._strTableName = strTableName;
		arrSQLTerms[2]._strColumnName = "gpa";
		arrSQLTerms[2]._strOperator = "=";
		arrSQLTerms[2]._objValue = 1.0;

		String[] strarrOperators = new String[2];
		strarrOperators[0] = "xor";
		strarrOperators[1] = "xor";
		// select * from Student where name = “John Noor” or gpa = 1.5;

		Iterator resultSet = dbApp.selectFromTable(arrSQLTerms, strarrOperators);

		System.out.println();
		System.err.println("results:");
		while (resultSet.hasNext()) {
			rows element = (rows) resultSet.next();

			System.out.println(element.tuples);

		}

		printTable(strTableName, dbApp);
	}

	// creates a table with a polygon column with 4 insertions
	public static void createTablePolygon(DBApp dbApp, String strTableName) throws DBAppException {

		Hashtable htblColNameType = new Hashtable();

		htblColNameType.put("id", "java.awt.Polygon");
		htblColNameType.put("name", "java.lang.String");
		htblColNameType.put("POLYGON-AREA", "java.lang.Double");
		// dbApp.createTable(strTableName, "id", htblColNameType);

		Hashtable htblColNameValue = new Hashtable();

		Polygon a = new Polygon();
		a.addPoint(1, 2);
		a.addPoint(2, 3);
		a.addPoint(4, 5);

		Polygon k = new Polygon();
		k.addPoint(0, 0);
		k.addPoint(0, 3);
		k.addPoint(3, 3);
		k.addPoint(3, 0);
		// System.out.println("AREA a= " + dbApp.calculatePolygonArea(a));

		Polygon d = new Polygon();
		d.addPoint(1, 2);
		d.addPoint(2, 4);
		d.addPoint(3, 5);
		// System.out.println("AREA d= " + dbApp.calculatePolygonArea(d));

		Polygon b = new Polygon();
		b.addPoint(0, 0);
		b.addPoint(3, 3);
		b.addPoint(33, 5);
		// System.out.println("AREA b= " + dbApp.calculatePolygonArea(b));

		Polygon c = new Polygon();
		c.addPoint(1, 0);
		c.addPoint(2, 2);
		c.addPoint(8, 8);
		// System.out.println("AREA c= " + dbApp.calculatePolygonArea(c));

		Polygon e = new Polygon();
		e.addPoint(0, 0);
		e.addPoint(3, 8);
		e.addPoint(33, 5);
		// System.out.println("AREA e= " + dbApp.calculatePolygonArea(e));

		Polygon f = new Polygon();
		f.addPoint(1, 0);
		f.addPoint(2, 2);
		f.addPoint(8, 10);
		// System.out.println("AREA f= " + dbApp.calculatePolygonArea(f));

		htblColNameValue.clear();
		htblColNameValue.put("id", a);
		htblColNameValue.put("name", new String("Poly-a"));
		htblColNameValue.put("POLYGON-AREA", dbApp.calculatePolygonArea(a));

		dbApp.insertIntoTable(strTableName, htblColNameValue);

		htblColNameValue.clear();
		htblColNameValue.put("id", b);
		htblColNameValue.put("name", new String("Poly-b"));
		htblColNameValue.put("POLYGON-AREA", dbApp.calculatePolygonArea(b));
		dbApp.insertIntoTable(strTableName, htblColNameValue);
		// dbApp.insertIntoTable(strTableName, htblColNameValue);
//		dbApp.insertIntoTable(strTableName, htblColNameValue);
//		
		htblColNameValue.clear();
		htblColNameValue.put("id", c);
		htblColNameValue.put("name", new String("Poly-c"));
		htblColNameValue.put("POLYGON-AREA", dbApp.calculatePolygonArea(c));
		dbApp.insertIntoTable(strTableName, htblColNameValue);

		htblColNameValue.clear();
		htblColNameValue.put("id", d);
		htblColNameValue.put("name", new String("Poly-d"));
		htblColNameValue.put("POLYGON-AREA", dbApp.calculatePolygonArea(d));
		dbApp.insertIntoTable(strTableName, htblColNameValue);

		htblColNameValue.clear();
		htblColNameValue.put("id", e);
		htblColNameValue.put("name", new String("Poly-e"));
		htblColNameValue.put("POLYGON-AREA", dbApp.calculatePolygonArea(e));
		dbApp.insertIntoTable(strTableName, htblColNameValue);

		htblColNameValue.clear();
		htblColNameValue.put("id", f);
		htblColNameValue.put("name", new String("Poly-f"));
		htblColNameValue.put("POLYGON-AREA", dbApp.calculatePolygonArea(f));
		dbApp.insertIntoTable(strTableName, htblColNameValue);

		htblColNameValue.clear();
		htblColNameValue.put("id", k);
		htblColNameValue.put("name", new String("Poly-k"));
		htblColNameValue.put("POLYGON-AREA", dbApp.calculatePolygonArea(k));
		dbApp.insertIntoTable(strTableName, htblColNameValue);

		printTable(strTableName, dbApp);
	}

	public static void fillTable(DBApp dbApp, String strTableName) throws DBAppException {

		String[] names = { "Sofia", "Nahla", "Sara", "Leena", "Basil", "Ramzy", "Khalid", "Wael", "Jimmy", "Taha",
				"Mariam", "Nadeen", "Maysoon", "Sama", "Nourhan", "Mario" };
		double[] gpa = { 0.7, 1.0, 1.8, 1.81, 2.1, 2.5, 2.9, 3.0, 1.68, 1.75, 1.2, 0.8, 1.3, 1.9, 3.25, 2.02 };

		Hashtable htblColNameValue = new Hashtable();

		for (int i = 0; i < 66; i++) {

			htblColNameValue.clear();
			Random rand = new Random();
			int id = rand.nextInt(201);
			htblColNameValue.put("id", (Integer) id);

			Random rand1 = new Random();
			int Index = rand1.nextInt(16);
			htblColNameValue.put("name", names[Index]);
			htblColNameValue.put("gpa", gpa[Index]);
			dbApp.insertIntoTable(strTableName, htblColNameValue);
		}
	}

}