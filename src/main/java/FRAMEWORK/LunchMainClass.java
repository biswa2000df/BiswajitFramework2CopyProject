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
