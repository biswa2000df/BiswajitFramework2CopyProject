package FRAMEWORK;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import io.github.bonigarcia.wdm.WebDriverManager;

public class normalclass {

	public static void main(String[] args) throws FilloException, IOException {
//		// TODO Auto-generated method stub
//
//		Fillo fillo = new Fillo();
//		Connection conn = fillo.getConnection(System.getProperty("user.dir") + "\\Ishine.xlsx");
//		String selectQuery = "SELECT * FROM Sheet2";
//		Recordset r = conn.executeQuery(selectQuery);
//
////		List<Object> al = new ArrayList<Object>();
////
////		while (r.next()) {
////			List<String> columns = r.getFieldNames();
////			for (String column : columns) {
////				al.add(r.getField(column));
////			}
////		}
////
////		for (Object o : al) {
////			System.out.println(o);
////		}
//		
//		List<Object> rowsList = new ArrayList<>();
//
//		while (r.next()) {
//		    List<String> columns = r.getFieldNames();
//		    List<Object> rowValues = new ArrayList<>();
//		    for (String column : columns) {
//		        rowValues.add(r.getField(column));
//		    }
//		    rowsList.add(rowValues);	    
//		}
//		
//		
//		
////		for (List<Object> row : rowsList) {
////		    for (Object value : row) {
////		        System.out.print(value + "\t");
////		    }
////		    System.out.println();
////		}
//		
//		
//		int size=rowsList.size();
//		
//		for(int i=0;i<size;i++)
//		{
//			 List<Object> row = (List<Object>) rowsList.get(i);
//		
//		
////		for (List<Object> row :  rowsList) {
//		    // Create a StringBuilder to build the string representation of the row
//		    StringBuilder rowString = new StringBuilder();
//
//		    for( int j=1;j<row.size();j++)
//			 {
//		        // Convert each column value to string and append it to the rowString
//		        rowString.append(row.get(j).toString()).append(", "); // You can use any delimiter you prefer (e.g., ", ", "\t", "|", etc.)
//		    }
//
//		    // Remove the last delimiter (comma and space) from the end of the rowString
//		    if (rowString.length() > 0) {
//		        rowString.setLength(rowString.length() - 2);
//		    }
//		    
//		    String[] columnValues = rowString.toString().split(", ");
//
//		    // Process each column value individually
//		    for (String columnValue : columnValues) {
//		        // Store each column value in another variable or perform any other processing
//		        // For example, you can add them to a list or use them directly in your code
//		        System.out.println("Column Value: " + columnValue);
//		    }
//
//		    // Now, the rowString contains the string representation of the current row
////		    System.out.println(rowString.toString());
//		}
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
////		int size=rowsList.size();
////		
////		for(int i=0;i<size;i++)
////		{
////			 List<Object> row = (List<Object>) rowsList.get(i);
////			 for(Object value : row)
////			 {
////				 System.out.print(value+"\t");
////			 }
////			 System.out.println();
////		}
////		
//		
//
//		
////		for (Object o : rowsList) {
////			System.out.println(o);
////		}

//		Fillo fillo = new Fillo();
//        Connection conn = fillo.getConnection(System.getProperty("user.dir") + "\\ISHINE.xlsx");
//        String moduleName = "ISHINE"; // Replace this with the desired module name
//        String query = "SELECT * FROM Sheet1 WHERE RUNSTATUS='Y' and MODULE='" + moduleName + "'";
//        
//        List<Object> rowsList = new ArrayList<>();
//
//        
//        if ("ISHINE".equalsIgnoreCase(moduleName)) {
//            Recordset recordset = conn.executeQuery(query);
//            // Process the recordset or perform other operations with the data
//            
//        	
//    		while (recordset.next()) {
//    			List<String> columns = recordset.getFieldNames();
//    			List<Object> rowValues = new ArrayList<>();
//    			for (String column : columns) {
//    				rowValues.add(recordset.getField(column));
//    			}
//    			rowsList.add(rowValues);
//    		}
//    		
//    		for(Object val :rowsList)
//    			System.out.println(val);
//            
//            recordset.close();
//        } else {
//            System.out.println("Module name is not 'ISHINE'. Stopping execution.");
//        }
//
//        conn.close();

//		String filePath = "C:\\Users\\biswa\\eclipse-workspace\\BiswajitFramework\\DataSheet\\ICICIPruAmcSip_PostLogin.xlsx";
//
//		System.out.println(filePath);
//
//		File file = new File(filePath);
//		try {
//
//			if (file.isFile()) {
//				System.out.println("  File is present");
//
//			} else {
//				System.out.println("  File is not present");
//			}
//
//		} catch (Exception e) {
//			// TODO: handle exception
//			System.err.println("An error occurred: " + e.getMessage());
//		}

		Fillo fillo = new Fillo();
		Connection conn = fillo.getConnection(System.getProperty("user.dir") + "\\Subscription.xlsx");
		String selectQuery = "SELECT * FROM Sheet6";
		Recordset r = conn.executeQuery(selectQuery);

		List<Object> rowList = new ArrayList<Object>();
		/*
		 * tale jou logic lekhichi First re sabu column name get kariki list re store
		 * kariba List<String> columnName = r.getFieldNames();
		 * 
		 * Then row value paei gote object type array list kariba gote row ku gote
		 * object type array list
		 * 
		 * Then gote row ra total column value ku get kariki first object type arraylist
		 * re store kariba //rowValue.add(r.getField(s));
		 * 
		 * Then sabu jaka object type array list ku gote object type array list re store
		 * kariba //rowList.add(rowValue); [Srno, Module, PageName, RunStatus, Control,
		 * ObjectType] this is a single object type array list in a single row ,This
		 * type we get the all the row and addd a particular object type array list
		 * 
		 * [[Srno, Module, PageName, RunStatus, Control, ObjectType],[Srno, Module,
		 * PageName, RunStatus, Control, ObjectType],[Srno, Module, PageName, RunStatus,
		 * Control, ObjectType]]
		 * 
		 * this way to store all the row value in the object type to store the
		 * particular objecttype arraylist ...To check it the two square bracket lisk
		 * as[[]] object type array list
		 */

		while (r.next()) {
			List<String> columnName = r.getFieldNames();
//			System.out.println(columnName); // [Srno, Module, PageName, RunStatus, Control, ObjectType, PropertyName,
			// PropertyValue, Datafield, Action, DataField_Type, Action_Type, Test_Case,
			// TestCase_Type, Description, Scenario_ID, Scenario_Description, COLUMN_17]
			List<Object> rowValue = new ArrayList<Object>();
			for (String s : columnName) {
				rowValue.add(r.getField(s));
			}
//			System.out.println(rowValue); // [2, Subscription, Home Page, Y, C, , , , , deleteAllCookies, , , TC_01_01,
			// Positive, Redirects To 5 paisa Home page, SC_01, Ledger purchase flow, ]
			rowList.add(rowValue);
		}

		System.out.println(rowList);

		System.out.println(rowList.size());

		for (int i = 0; i < rowList.size(); i++) {
//			System.out.println(rowList.get(i));
			List<Object> row = (List<Object>) rowList.get(i);
			
			
		}

	}

}
