package FRAMEWORK;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;
import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class ConnectDataSheet extends BrowserClass {

	public static String Si_No;
	public static String MODULE;
	public static String PageName;
	public static String PropertyName;
	public static String PropertyValue;
	public static String Datafield;
	public static String Action;
	public static String Action_Type;
	public static String Test_Case;
	public static String Description;
	public static String Scenario_ID;

	public static LocatorClass locatorClass;
	public static ConnectDataSheet connectDatasheet;
	public static UtilScreenshotAndReport utilClass;
	public static String destFileScrnshot;
	public static String status;

	public static WebElement webElement;
	public static List<WebElement> webElements;

	public static String DataSheet2Value;
	public static ActionClass actClass;

	public static String sTest_Case = null;

	public static int totalTest = 0, pass = 0, fail = 0;

	// In this method DataSheetGet(String fileName) recive the datasheet file name
	// check the maincontroler module is same as datasheet module name
	// then read all the datasheet and iterate all the row in the object type
	// use forloop and if the forloop of i==0 then call the extendreport
	// then iterate all the row value in the single single variable
	// then pass the testcase_id and the all the description to the extentreport to
	// print the info.
	// then call the this xpathpick() and pass the all value what i have retrived to
	// the row and column value.
	// after retrive the all the value the call to the extentflush() to stop the
	// generate report.

	public static void DataSheetGet(String fileName) throws FilloException, InterruptedException, IOException {
		connectDatasheet = new ConnectDataSheet();
		locatorClass = new LocatorClass();
		utilClass = new UtilScreenshotAndReport();

		Fillo fillo = new Fillo();
		Connection conn = fillo.getConnection(
				System.getProperty("user.dir") + File.separator + "DataSheet" + File.separator + fileName);
		try {

			String query = "SELECT * FROM Sheet3 WHERE RUNSTATUS='Y' and MODULE='" + ConnectToMainController.Module
					+ "'";
			Recordset recordset = conn.executeQuery(query);

			// new type and logic explanation***************************************

			/*
			 * This is Most Important
			 * 
			 * 
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

			List<Object> rowsList = new ArrayList<>();

			while (recordset.next()) {
				List<String> columns = recordset.getFieldNames();
				List<Object> rowValues = new ArrayList<>();
				for (String column : columns) {
					rowValues.add(recordset.getField(column));
				}
				rowsList.add(rowValues);
			}

			// this type is working properly****************************

			utilClass.extentReport(); // call the extent report method
			utilClass.CsvFileCreate();
			utilClass.WriteCSVFileHeading("Test_Case", "Description", "ExpectedResult", "ActualResult", "Status",
					"Date", "Time", "Screenshot_File_Location", "BrowserType", "IP", "HOST", "ZONE");
			utilClass.IP_HOST();

			int i;
			for (i = 0; i < rowsList.size(); i++) {

				destFileScrnshot = null;
				status = "PASS";

				List<Object> row = (List<Object>) rowsList.get(i);

				Si_No = (String) row.get(0);
				MODULE = (String) row.get(1);
				PageName = (String) row.get(2);
				PropertyName = (String) row.get(4);
				PropertyValue = (String) row.get(5);
				Datafield = (String) row.get(6);
				Action = (String) row.get(7);
				Action_Type = (String) row.get(8);
				Test_Case = (String) row.get(9);
				Description = (String) row.get(10);
				Scenario_ID = (String) row.get(11);

				System.out.println();
				System.out.println("SI_No             ====================> " + Si_No);
				System.out.println("Scenario_ID       ====================> " + Scenario_ID);
				System.out.println("PropertyName      ====================> " + PropertyName);
				System.out.println("PropertyValue     ====================> " + PropertyValue);
				System.out.println("Datafield         ====================> " + Datafield);
				System.out.println("ActionType        ====================> " + Action);

				if (!Test_Case.equals(sTest_Case)) {
					utilClass.testCaseCreate();
				}
				sTest_Case = Test_Case;

				try {
					totalTest++;
					locatorClass.xpathpick();

					pass = totalTest - fail;
					System.out.println("TotalTest = " + totalTest + " Pass = " + pass + " Fail = " + fail);

				} catch (Exception e) {
//					UtilScreenshotAndReport.test.fail(e);
					e.printStackTrace();
					fail++;
					System.out.println("TotalTest = " + totalTest + " Pass = " + pass + " Fail = " + fail);
				}

				// Write the Data Inside the csv File
				if (Action.equalsIgnoreCase("CheckVisibility"))
					utilClass.WriteCSVFileData(Test_Case, Description, DataSheet2Value,
							String.valueOf(ActionClass.ActualResult), status, utilClass.yearFormat, utilClass.time, destFileScrnshot,
							ConnectToMainController.Browser, utilClass.IP, utilClass.HostName, utilClass.ZoneName);

			}
			if (i == rowsList.size()) {

				System.out.println("\n");

				System.out.println("TotalTest = " + totalTest + " Pass = " + pass + " Fail = " + fail);

				System.out.println("********************  Successfully Completed  ********************" + "\n");
			}
		} catch (Exception e) {
//			System.err.print(e.getMessage());
			e.printStackTrace();
		}

		finally {
			utilClass.ExtentFlush();
			UtilScreenshotAndReport.CreateHtmlTable();
		}

	}

	//////////////////////////////////////// DataField Read
	//////////////////////////////////////// kariba
	//////////////////////////////////////// sheet2//////////////////////////////

	// In this method DataFieldRead(to receive the datasheet filed value to get the
	// sheet2 value)
	// first check the datafield if it is not null then get the datafield value and
	// pass the value to this method actrds();

	public static void DataFieldRead() throws FilloException, InterruptedException, Exception {

		actClass = new ActionClass();

		if (Datafield != null && !Datafield.isEmpty()) {
			Fillo fillo = new Fillo();
			Connection conn = fillo.getConnection(System.getProperty("user.dir") + File.separator + "DataSheet"
					+ File.separator + ConnectToMainController.TestFlow_Path);
			String query = "SELECT * FROM Sheet2";
			Recordset recordset = conn.executeQuery(query);

			while (recordset.next()) {
				DataSheet2Value = recordset.getField(Datafield);
				System.out.println("DataFiels For Sheet2==================================================== "
						+ DataSheet2Value + "\n");
//				ActionClass.actrds();
			}

			UtilScreenshotAndReport.testcaseInfoWithDataField();
			ActionClass.actrds();
		}

		else {
			UtilScreenshotAndReport.testcaseInfoWithoutDataField();
			ActionClass.actrds();

		}

	}

}
