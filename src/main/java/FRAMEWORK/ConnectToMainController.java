package FRAMEWORK;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class ConnectToMainController {

	static String TestFlow_Path = null;

	static String Sr_No = null;
	static String ApplicationID = null;
	static String Module = null;
	static String Browser = null;
	static String process1 = null;
	static ConnectDataSheet conDataSheet;

	// Inside this method ----->MainContolerSheet()
	// In this method first check the file maincontroler file is present or not
	// then runstatus is y
	// check all the filled is filldup not empty if empty then execution is stop
	// then call this MainControlerDataSheet() and pass the process data

	public static void MainContolerSheet() throws FilloException, InterruptedException, IOException {
		conDataSheet = new ConnectDataSheet();

		// TODO Auto-generated method stub

		String MainControler = System.getProperty("user.dir") + File.separator + "Main_Controller.xlsx";
		File file = new File(MainControler);

		if (file.exists()) {

			Fillo fillo = new Fillo();
			Connection conn = fillo.getConnection(MainControler);
			String query = "SELECT * FROM MainController Where RunStatus='Y'";

			List<Object> rowlist = new ArrayList<Object>();

			Recordset recordset = conn.executeQuery(query);

			while (recordset.next()) {
				List<String> columns = recordset.getFieldNames();
				List<Object> rowvalues = new ArrayList<Object>();

				for (String column : columns) {
					rowvalues.add(recordset.getField(column));
				}
				rowlist.add(rowvalues);
			}

//			System.out.println("MainController Row List "+rowlist);    // MainController Row List [[3, y, Chrome, IshinePortal, IshinePortal, IshinePortal, IshinePortal]]   this type it is print row list

			for (int i = 0; i < rowlist.size(); i++) {

				List<Object> row = (List<Object>) rowlist.get(i);

				Sr_No = (String) row.get(0);
				Browser = (String) row.get(2);
				ApplicationID = (String) row.get(3);
				Module = (String) row.get(4);
				process1 = (String) row.get(6);
				try {

					if (Sr_No != null && !Sr_No.isEmpty() && Browser != null && !Browser.isEmpty()
							&& ApplicationID != null && !ApplicationID.isEmpty() && Module != null && !Module.isEmpty()
							&& process1 != null && !process1.isEmpty())

					{
						MainControlerDataSheet(process1); // call this method with processname to the datasheet
					} else {
						System.out.println("Please Filled all the data Properly");
					}

				} catch (Exception e) {
					System.err.println(e.getMessage());
					e.printStackTrace();

				}

//			System.out.println(process1);
			}
		} else {
			System.out.println("MainMain_Controller File is Not Present");
		}

	}

	// Inside this method ------>MainControlerDataSheet(receive process)
	// first the maincontroler process is same as maincontrolerdatasheet process as
	// same
	// if the process is same then then read the all the rowvalue and column value
	// then get the TestFlow_Path or exceldatasheet name
	// then check the file is present or not in the datasheet folder

	public static void MainControlerDataSheet(String process1)
			throws FilloException, InterruptedException, IOException {// this method read the MainController datasheet
		try {

			Fillo fillo = new Fillo();
			Connection conn = fillo.getConnection(System.getProperty("user.dir") + File.separator + "Main_Controller.xlsx");
			String query = "SELECT * FROM Sheet3 Where Process='" + process1 + "'";

			Recordset recordset = conn.executeQuery(query);
			List<Object> rowLists = new ArrayList<Object>();
			while (recordset.next()) {
				List<String> columns = recordset.getFieldNames();
				List<Object> rowvalues = new ArrayList<Object>();

				for (String column : columns) {
					rowvalues.add(recordset.getField(column));
				}
				rowLists.add(rowvalues);
			}

			for (int i = 0; i < rowLists.size(); i++) {
				List<Object> row = (List<Object>) rowLists.get(i);

				TestFlow_Path = (String) row.get(2);

				System.out.println("DataSheetName========================> " + TestFlow_Path);

				fileCheck(TestFlow_Path);///// Call this method sending the datasheet name with the .xlsx format which
											///// is
											///// check

			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}

	/// In this method =============fileCheck(String fileName)//excelsheet file name
	/// or datasheet name
	// check the datasheet folder excelsheet is present or not what the upper method
	/// is called fileCheck(TestFlow_Path) like this...
	// Then call the conDataSheet.DataSheetGet(fileName); to send the file name .

	public static void fileCheck(String fileName) throws FilloException, InterruptedException, IOException {

		String filePath = System.getProperty("user.dir") + File.separator+ "DataSheet" + File.separator + fileName;
//	System.out.println(filePath);

		File file = new File(filePath);
		if (file.exists()) {
//			System.out.println(filePath + " DataSheet File is present");
			conDataSheet.DataSheetGet(fileName);///// this method to call the datasheet folder which is present in the
												///// DataSheet folder.
		} else {
			System.out.println(" DataSheet File is not present");
		}
	}

}
