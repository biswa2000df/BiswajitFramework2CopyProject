package FRAMEWORK;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class LunchMainClass extends BrowserClass {

	public static void main(String[] args) throws FilloException, InterruptedException, IOException, ParseException {
		Run();
	}

	public static void Run() throws ParseException, FilloException, InterruptedException, IOException {
		LicenceClass.LicenceCheck(); // Execution Start From This Method
	}
}

//mvn clean package=========create the jarfile

//		conSheet.DataSheetGet();     //Execution Start From This

//		Initialisation();

// ****************************single value get kariba paei object
// ru*******************

//		int size=al.size();
//		
//		for(int i=0;i<size;i++)
//		{
//			 List<Object> row = (List<Object>) al.get(i);
//			 for(Object value : row)
//			 {
//				 System.out.print(value+"\t");
//			 }
//			 System.out.println();
//		}	

///////// **************************************

//	int size=al.size();
//		
//		for(int i=0;i<size;i++)
//		{
//			 List<Object> row = (List<Object>) al.get(i);
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
//		    // Now, the rowString contains the string representation of the current row
//		    System.out.println(rowString.toString());
//		    
//		}

///////////////////////// anotherway to read the data from the row
///////////////////////// wise///////////////////

//		int j;
//		for(int i=0;i<al.size();i++) {
//			
//			 List<Object> row = (List<Object>) al.get(i);
//			 
//			 for( j=1;j<row.size();j++)
//			 {
//				 if(j==1)
//				 {
//					 LOCATOR=(String) row.get(j);
//				 }
//				 
//				 else if(j==2) {
//					 PropertyValue=(String) row.get(j);
//				 }
//				 
//				 else if(j==3) {
//					 Datafield=(String) row.get(j);
//				 }
//				 
//				 else if(j==4) {
//					 Action=(String) row.get(j);
//				 }
//
//			 }
//			 System.out.println(LOCATOR+" "+PropertyValue+" "+Datafield+" "+Action);
//			 
//			 
//			 
//			 xget.xpathpick(LOCATOR, PropertyValue, Datafield, Action, driver);
//			 
//			 
//			
//		}
//		

/////////////////// ************************1st
/////////////////// attempt************************************///////////////////////////////////

//		for (int i = 0; i < al.size(); i = i + 4) {
//
//			LOCATOR = al.get(i);
//			XPATH = al.get(i + 1);
//			Datafield = al.get(i + 2);
//			Action = al.get(i + 3);
//
//			if (LOCATOR != null && !LOCATOR.isEmpty() && XPATH != null && !XPATH.isEmpty()) {
//
//				Element = xget.xpathpick(LOCATOR, XPATH);
//			}
//			
//
//			if (Datafield != null && !Datafield.isEmpty()) {
//				if (true) {
//					Fillo fillo = new Fillo();
//					Connection conn = fillo.getConnection(
//							"C:\\Users\\biswa\\eclipse-workspace\\READ_EXCELSHEET_FRAMEWORK\\kanha30.xlsx");
//					String query = "SELECT * FROM Sheet5";
//					Recordset recordset = conn.executeQuery(query);
//					while (recordset.next()) {
//						hello = recordset.getField(Datafield);
//						System.out.println(
//								"================================================================================="
//										+ hello);
//					}
//				}
//
//			}

//		actcls.actrds(Element, hello, Action); /// call to the function class

//		driver.findElement(By.xpath(XPATH)).sendKeys(hello);

//		driver.quit();
//	}
//
//}
