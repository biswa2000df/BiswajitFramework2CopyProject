package FRAMEWORK;

import java.io.FileWriter;
import java.io.IOException;

import com.aventstack.extentreports.Status;

public class HtmlTable {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		/*
		 * <table> <tr> <th>Company</th> <th>Contact</th> <th>Country</th> </tr> <tr>
		 * <td>Alfreds Futterkiste</td> <td>Maria Anders</td> <td>Germany</td> </tr>
		 * <tr> <td>Centro comercial Moctezuma</td> <td>Francisco Chang</td>
		 * <td>Mexico</td> </tr> </table>
		 * 
		 * 
		 * 
		 * test.log(Status.INFO,
		 * "<h6><br><font color=\"Red\"><b> Expected Result is - </b></font></h6>" +
		 * "true" +
		 * " <h6><br> <font color=\"Red\"><b>Actual Result is - </b></font><h6>" +
		 * "False");
		 */

//		System.out.println(
//				"<!DOCTYPE html><html><body><table border=\"1\"><tr><td>Row 1, Column 1</td><td>Row 1, Column 2</td><td>Row 1, Column 3</td> </tr></table></body></html>");
//
//		  <tr>
//		    <th>Header 1</th>
//		    <th>Header 2</th>
//		    <th>Header 3</th>
//		  </tr>

		String filename = "table.html";
		int numRows = 2;
		int numCols = 4;

		FileWriter writer = new FileWriter(filename);

		writer.write("<!DOCTYPE html>\n<html>\n<head>\n");

		writer.write("<style> table { border-collapse: collapse; width: 50%; margin: auto; margin-top: 20px; }");

		writer.write(" th, td { border: 1px solid black; padding: 8px; text-align: center; }");

		writer.write("th {  background-color: #f2f2f2; } </style>");

		writer.write("</head>\n<body>\n");

		writer.write("<table border=\"1\">\n");

		writer.write(
				"<tr> <th><font color=\"Green\">Project</font></th><th><font color=\"Blue\">Total TCs</font></th><th><font color=\"Green\">Passed TCs</font></th><th><font color=\"Red\">Failed TCs</font></th><th>Report</th></tr>");

		String s="C:\\Users\\biswa\\eclipse-workspace\\BiswajitFramework\\RESULT\\2023\\August\\18\\Report\\17_53_50_students.html";
		
		writer.write(
				"<td>Row 2</td><td>Row 2</td><td>Row 2</td><td>Row 2</td><td><a href="+s+">View Report</a></td>");

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
		System.out.println("HTML table has been generated in " + filename);

	}

}
