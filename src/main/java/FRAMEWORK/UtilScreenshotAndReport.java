package FRAMEWORK;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.opencsv.CSVWriter;

public class UtilScreenshotAndReport extends ConnectDataSheet {

	ExtentHtmlReporter htmlReport;
	ExtentReports extent;
	static ExtentTest test;
	static String yearFormat;
	static String time;
	public static String Extent_ReportFile;
	public static String CSV_ReportFile;
	public static String ssDatafield = null;
	public static String ssDataSheet2Value = null;
	public static String onlyDate;
	public static String onlyMonth;

	static String IP = null;
	static String HostName = "";
	static String ZoneName = null;

	public String takeScreenShot(WebDriver driver, String TestCase_No) throws IOException {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destPath = getFormat("YYYY", "MMMM", "dd", "Screenshot");
		ConnectDataSheet.destFileScrnshot = destPath + File.separator + time + "_" + TestCase_No + ".png";
		FileUtils.copyFile(srcFile, new File(ConnectDataSheet.destFileScrnshot));
		return ConnectDataSheet.destFileScrnshot;
	}

	public void ScreenshotPathFormat() {
		Date date = new Date();
		SimpleDateFormat tm = new SimpleDateFormat("yyyy-MM-dd");
		yearFormat = tm.format(date);
		SimpleDateFormat tm1 = new SimpleDateFormat("HH_mm_ss");
		time = tm1.format(date);
	}

	public static String getFormat(String Year, String Month, String Date, String Type) {

		Date date = new Date();
		SimpleDateFormat yer = new SimpleDateFormat(Year);
		SimpleDateFormat mnt = new SimpleDateFormat(Month);
		SimpleDateFormat dat = new SimpleDateFormat(Date);
		SimpleDateFormat tm = new SimpleDateFormat("HH_mm_ss");
		SimpleDateFormat fullyer = new SimpleDateFormat("yyyy-MM-dd");
		yearFormat = fullyer.format(date);

		String year = yer.format(date);
		String Mnth = mnt.format(date);
		String Dt = dat.format(date);
		time = tm.format(date);

//		System.out.println(year);
//		System.out.println(Mnth);
//		System.out.println(Dt);

		String f = System.getProperty("user.dir") + File.separator + "RESULT" + File.separator + year + File.separator
				+ Mnth + File.separator + Dt + File.separator + Type;
		new File(f).mkdirs();
//		System.out.println(f);
		return f;
	}

	public static void OnlyDateMonthFormate() {
		Date date = new Date();
		SimpleDateFormat formateDate = new SimpleDateFormat("dd");
		onlyDate = formateDate.format(date);
		SimpleDateFormat formateMonth = new SimpleDateFormat("MMMM");
		onlyMonth = formateMonth.format(date);
	}

	public void extentReport() throws IOException {

		String htmlFile = "students.html";

		String destFile = getFormat("YYYY", "MMMM", "dd", "Report");
		Extent_ReportFile = destFile + File.separator + time + "_" + htmlFile;
		htmlReport = new ExtentHtmlReporter(Extent_ReportFile);
		extent = new ExtentReports();
		extent.attachReporter(htmlReport);

		htmlReport.config().setDocumentTitle("Biswajit FrameWork Report");// Title of the report
		htmlReport.config().setReportName("Automation Report");// Name of the report
		htmlReport.config().setTestViewChartLocation(ChartLocation.BOTTOM);
		htmlReport.config().setChartVisibilityOnOpen(false);
		htmlReport.config().setTheme(Theme.DARK);

		extent.setSystemInfo("Comapny Name", "APMOSYS");
		extent.setSystemInfo("FrameWork", "Biswajit Framework");
		extent.setSystemInfo("Project Name", "ISHINE");
		extent.setSystemInfo("Test Lead", "Prabhat Padhy");
		extent.setSystemInfo("OS", System.getProperty("os.name"));
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Tester Name", "Biswajit");
		extent.setSystemInfo("Browser", ConnectToMainController.Browser);
		extent.setSystemInfo("Application URL", "https://www.google.com");

	}

	public static void testcaseInfoWithDataField() {
		ssDatafield = Datafield;
		ssDataSheet2Value = DataSheet2Value;
		test.log(Status.INFO,
				"<font color=\"Blue\"><b>Module - </b></font>" + MODULE + " "
						+ "<font color=\"Lime\"><b>Step - </b></font>" + Si_No + " "
						+ "<font color=\"Red\"><b>Data Field - </b></font>" + ssDatafield.toUpperCase() + " "
						+ "<font color=\"Lime\"><b>Test Data - </b></font>" + ssDataSheet2Value);
	}

	public static void testcaseInfoWithoutDataField() {
		test.log(Status.INFO, "<font color=\"Blue\"><b>Module - </b></font>" + MODULE + " "
				+ "<font color=\"Lime\"><b>Step - </b></font>" + Si_No);
	}

	public void testCaseCreate() {
		test = extent.createTest(
				"<font color=\"Blue\"><b>" + Scenario_ID + "</b></font> - <font color=\"Brown\"><b>" + MODULE
						+ "</b></font> - <font color=\"Green\"><b>" + Test_Case + "</b></font> ( " + Description + " )",
				"</br><h4><font color=\"Lime\"><b>" + MODULE.toUpperCase() + "</b></font></h4>")
				.createNode("<h5><b>" + Description + "</b></h5>").assignCategory("BISWAJIT");
	}

	public void passTestCase() throws IOException {

		test.log(Status.PASS,
				"<h6><br><font color=\"Red\"><b> Expected Result is - </b></font></h6>" + ssDataSheet2Value
						+ "	 <h6><br> <font color=\"Red\"><b>Actual Result is - </b></font><h6>"
						+ ActionClass.ActualResult + "<br>",
				MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(driver, Scenario_ID)).build());
	}

	public void failTestCase() throws IOException {
//		test.log(Status.INFO, MarkupHelper.createLabel(Neg_Description, ExtentColor.RED));
//		test.log(Status.FAIL, "",
//				MediaEntityBuilder.createScreenCaptureFromPath(
//						"<h6><br><font color=\"Red\"><b> Expected Result is - </b></font></h6>" + "True"
//								+ "	 <h6><br> <font color=\"Red\"><b>Actual Result is - </b></font><h6>" + "False"
//								+ "<br>" , takeScreenShot(driver, Scenario_ID))
//						.build());

		test.log(Status.FAIL,
				"<h6><br><font color=\"Red\"><b> Expected Result is - </b></font></h6>" + ssDataSheet2Value
						+ "	 <h6><br> <font color=\"Red\"><b>Actual Result is - </b></font><h6>"
						+ ActionClass.ActualResult + "<br>",
				MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(driver, Scenario_ID)).build());

	}

	public void ExtentFlush() {
		extent.flush();
	}

	/////// CSV FILE CREATE///////////////////////////

	String csvFile = "students.csv";
	String destFile;

	public void CsvFileCreate() throws IOException {

		/*
		 * Date date = new Date(); SimpleDateFormat tm = new
		 * SimpleDateFormat("yyyy-MM-dd"); String year = tm.format(date);
		 * SimpleDateFormat tm1 = new SimpleDateFormat("HH_mm_ss"); String time =
		 * tm1.format(date); destFile = System.getProperty("user.dir") + File.separator
		 * + "CSVFile" + File.separator + year + File.separator + time; new
		 * File(destFile).mkdirs();
		 */

		destFile = getFormat("YYYY", "MMMM", "dd", "CSVFile");

	}

	static FileWriter fileWriter;

	public void WriteCSVFileHeading(String Test_Case, String Description, String ExpectedResult, String ActualResult,
			String Status, String Date, String Time, String Screenshot_File_Location, String BrowserType, String IP,
			String HOST, String ZONE) throws IOException {

		CSV_ReportFile = destFile + File.separator + csvFile;
		fileWriter = new FileWriter(CSV_ReportFile, true);
		CSVWriter cw = new CSVWriter(fileWriter);
		String line1[] = { Test_Case, Description, ExpectedResult, ActualResult, Status, Date, Time,
				Screenshot_File_Location, BrowserType, IP, HOST, ZONE };

		// Writing data to the csv file
		cw.writeNext(line1);
		// close the file
		cw.close();

//		DataBaseConnection.DataBase(Si_No, TestCase_No, Status, Screenshot_Path);
	}

	public void WriteCSVFileData(String Test_Case, String Description, String ExpectedResult, String ActualResult,
			String Status, String Date, String Time, String Screenshot_File_Location, String BrowserType, String IP,
			String HOST, String ZONE) throws IOException {
		CSV_ReportFile = destFile + File.separator + csvFile;
		fileWriter = new FileWriter(CSV_ReportFile, true);
		CSVWriter cw = new CSVWriter(fileWriter);
		String line1[] = { Test_Case, Description, ExpectedResult, ActualResult, Status, Date, Time,
				"=HYPERLINK(\"" + Screenshot_File_Location + "\")", BrowserType, IP, HOST, ZONE };

//		String line2[] = { Si_No, TestCase_No, Status, "=HYPERLINK(\"" + Screenshot_Path + "\")" };

		// Writing data to the csv file
		cw.writeNext(line1);
		// close the file
		cw.close();

//		DataBaseConnection.DataBase(Si_No, TestCase_No, Status, Screenshot_Path);
	}

	// Create html Table For Mail Sent
	public static void CreateHtmlTable() throws IOException {

		String htmlTable = getFormat("YYYY", "MMMM", "dd", "HtmlTable");
		String filename = htmlTable + File.separator + "SummaryTable.html";
		FileWriter writer = new FileWriter(filename);
		writer.write("<!DOCTYPE html>\n<html>\n<head>\n");
		writer.write("<style> table { border-collapse: collapse; width: 50%; margin: auto; margin-top: 20px; }");
		writer.write(" th, td { border: 1px solid black; padding: 8px; text-align: center; }");
		writer.write("th {  background-color: #f2f2f2; } </style>");
		writer.write("</head>\n<body>\n");
		writer.write("<table border=\"1\">\n");
		writer.write(
				"<tr> <th><font color=\"Lime\">Project</font></th><th><font color=\"Blue\">Total TCs</font></th><th><font color=\"Green\">Passed TCs</font></th><th><font color=\"Red\">Failed TCs</font></th><th>Report</th><th>CSV_File</th></tr>");
		writer.write("<td>" + ConnectToMainController.Module + "</td><td>" + totalTest + "</td><td>" + pass
				+ "</td><td>" + fail + "</td><td><a href=" + Extent_ReportFile
				+ " target=_blank>Extent_Report</a></td><td><a href=" + CSV_ReportFile + " target=_blank>CSV</a></td>");

//	            for (int i = 0; i < numRows; i++) {
//	                writer.write("<tr>\n");
//	                for (int j = 0; j < numCols; j++) {
//	                    writer.write("<td>Row " + (i + 1) + ", Column " + (j + 1) + "</td>\n");
//	                }
//	                writer.write("</tr>\n");
//	            }

		writer.write("</table>\n");
		writer.write("</body>\n</html>");
		writer.close();
//		System.out.println("HTML table has been generated in " + filename);
	}

	public void IP_HOST() {
		String IP_HOST_ZONE = "";
		try {
			InetAddress address = InetAddress.getLocalHost();
			IP = address.getHostAddress(); // 172.18.32.1
			HostName = address.getHostName(); // BISWAJIT2000
		} catch (UnknownHostException e) {

			e.printStackTrace();
		}
		// TIME ZONE
		try {
			Calendar cal = Calendar.getInstance();
			long milliDiff = cal.get(Calendar.ZONE_OFFSET); // milliDiff=offset=19800000
			// Got local offset, now loop through available timezone id(s).
			String[] zoneName = TimeZone.getAvailableIDs(); // get all the zone name using the array

			for (String id : zoneName) {
				TimeZone tz = TimeZone.getTimeZone(id); // Example =
														// sun.util.calendar.ZoneInfo[id="Africa/Addis_Ababa",offset=10800000,dstSavings=0,useDaylight=false,transitions=7,lastRule=null]
				if (tz.getRawOffset() == milliDiff) { // check the offset is matched
					// Found a match.
					ZoneName = id;
					break;
				}
			}
			IP_HOST_ZONE = IP + "," + HostName + "," + ZoneName;
			/// System.out.println("IP="+ip+"--------HOSTNAME= "+hostname+"--------ZoneName=
			/// "+name);

		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
