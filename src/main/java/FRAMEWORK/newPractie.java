package FRAMEWORK;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import com.opencsv.CSVWriter;

public class newPractie {

	public static String Srno;
	public static String Module;
	public static String PageName;
	public static String RunStatus;
	public static String PropertyName;
	public static String PropertyValue;
	public static String Datafield;
	public static String Action;
	public static String Action_Type;
	public static String Test_Case;
	public static String Description;
	public static String Scenario_ID;

	public static String sTest_Case = null;

	// Extentreport
	public static ExtentHtmlReporter htmlReport;
	public static ExtentReports extent;
	public static ExtentTest test;

	public static void ma() throws FilloException, IOException {

		htmlReport = new ExtentHtmlReporter("normal.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReport);

		htmlReport.config().setDocumentTitle("Biswajit FrameWork Report");// Title of the report
		htmlReport.config().setReportName("Automation Report");// Name of the report
		htmlReport.config().setTestViewChartLocation(ChartLocation.BOTTOM);
		htmlReport.config().setChartVisibilityOnOpen(false);
		htmlReport.config().setProtocol(Protocol.HTTP);
		htmlReport.config().setTheme(Theme.DARK);

		extent.setSystemInfo("Comapny Name", "APMOSYS");
		extent.setSystemInfo("FrameWork", "Biswajit Framework");
		extent.setSystemInfo("Project Name", "ISHINE");
		extent.setSystemInfo("Test Lead", "Prabhat Padhy");
		extent.setSystemInfo("OS", "Window11");
		extent.setSystemInfo("Tester Name", "Biswajit");
		extent.setSystemInfo("Browser", "Chrome");
		extent.setSystemInfo("Application URL", "https://www.google.com");

//		Fillo fillo = new Fillo();
//		Connection conn = fillo.getConnection(System.getProperty("user.dir") + File.separator + "DataSheet" + File.separator + "IshinePortal.xlsx");
////		Connection conn=fillo.getConnection("‪C:\\Users\\biswa\\eclipse-workspace\\BiswajitFramework\\Subscription.xlsx");
//		String selectQuery = "SELECT * FROM Sheet3";
//		Recordset r = conn.executeQuery(selectQuery);
//
//		List<Object> rowList = new ArrayList<Object>();
//
//		while (r.next()) {
//			List<String> columnName = r.getFieldNames();
////			System.out.println(columnName);
//			List<Object> rowValue = new ArrayList<Object>();
//			for (String s : columnName) {
//				rowValue.add(r.getField(s));
//
//			}
//			rowList.add(rowValue);
//		}
//
//		System.out.println(rowList.size());
//
//		for (int i = 0; i < rowList.size(); i++) {
////			System.out.println(rowList.get(i));
//			List<Object> row = (List<Object>) rowList.get(i);
//
//			Srno = (String) row.get(0);
//			Module = (String) row.get(1);
//			PageName = (String) row.get(2);
//			RunStatus = (String) row.get(3);
//			PropertyName = (String) row.get(4);
//			PropertyValue = (String) row.get(5);
//			Datafield = (String) row.get(6);
//			Action = (String) row.get(7);
//			Action_Type = (String) row.get(8);
//			Test_Case = (String) row.get(9);
//			Description = (String) row.get(10);
//			Scenario_ID = (String) row.get(11);
//			
//
//			System.out.println("Srno================================================" + Srno);
//			System.out.println("Module==============================================" + Module);
//			System.out.println("PageName============================================" + PageName);
//			System.out.println("RunStatus===========================================" + RunStatus);
//			System.out.println("PropertyName========================================" + PropertyName);
//			System.out.println("PropertyValue=======================================" + PropertyValue);
//			System.out.println("Datafield===========================================" + Datafield);
//			System.out.println("Action==============================================" + Action);
//			System.out.println("Action_Type=========================================" + Action_Type);
//			System.out.println("Test_Case===========================================" + Test_Case);
//			System.out.println("Description=========================================" + Description);
//			System.out.println("Scenario_ID=========================================" + Scenario_ID);
//
//			System.out.println("\n");
//
//			if (!Test_Case.equals(sTest_Case)) {
//
//				extentrepost();
//			}
//			sTest_Case = Test_Case;
//			info(i);
//		}
//		flush1();

	}
	
public static int i=0;
	
	public static void extentrepost() {
		
		i++;

//		test = extent.createTest(
//				"<font color=\"Blue\"><b>" + Scenario_ID + "</b></font> - <font color=\"Brown\"><b>" + Module
//						+ "</b></font> - <font color=\"Green\"><b>" + Test_Case + "</b></font> ( " + Description + " )",
//				"</br><h4><font color=\"Lime\"><b>" + Module.toUpperCase() + "</b></font></h4>")
//				.createNode("My_Child_Test - "+ i ).assignCategory("BISWAJIT");

		test = extent.createTest("kanha");
	}

	public static void info(int i) {

		test.log(Status.INFO,
				"<font color=\"Blue\"><b>Module - </b></font>" + Module + " "
						+ "<font color=\"Lime\"><b>Step - </b></font>" + Srno + " "
						+ "<font color=\"Aqua\"><b>Data Field - </b></font>" + Datafield);

		/*
		 * try { int a=9/i; } catch (Exception e) { test.fail(e); }
		 * 
		 */

		test.log(Status.INFO, "<h6><br><font color=\"Red\"><b> Expected Result is - </b></font></h6>" + "true"
				+ " <h6><br> <font color=\"Red\"><b>Actual Result is - </b></font><h6>" + "False");
//					+ "<br>" + Framework.extent.addScreenCapture(Framework.ScreenshotfileLocation));

	}

	public static void flush1() {
		extent.flush();
	}

}
