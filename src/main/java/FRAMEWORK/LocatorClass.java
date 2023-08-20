package FRAMEWORK;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.codoid.products.exception.FilloException;

public class LocatorClass extends ConnectDataSheet {

	public LocatorClass() throws FilloException {
		super();
		// TODO Auto-generated constructor stub
	}

	// In this method xpathpick()
	// first check the PropertyName and PropertyValue it is null or not
	// if it is null then go to the else part and call to the datasheet() for the
	// sheet2 value.
	// if it is not null then check the PropertyName and PropertyValue
	// to get the webelement and webelements and store into the webelement variable
	// if the webelement is no present i have use in the the try catch block if it
	// is failed or
	// it goes to the catch block to call the datasheet method().

	public void xpathpick() throws FilloException, InterruptedException, IOException {

		webElement = null;
		webElements = null;

		locatorClass = new LocatorClass();
		actClass = new ActionClass();

		By by;

		if ((PropertyName != null && !PropertyName.isEmpty() && PropertyValue != null && !PropertyValue.isEmpty())) {

			if (PropertyName.equalsIgnoreCase("xpath")) {
				by = By.xpath(PropertyValue);
			} else if (PropertyName.equalsIgnoreCase("id")) {
				by = By.id(PropertyValue);
			} else if (PropertyName.equalsIgnoreCase("name")) {
				by = By.name(PropertyValue);
			} else if (PropertyName.equalsIgnoreCase("className")) {
				by = By.className(PropertyValue);
			} else if (PropertyName.equalsIgnoreCase("css")) {
				by = By.cssSelector(PropertyValue);
			} else if (PropertyName.equalsIgnoreCase("tagName")) {
				by = By.tagName(PropertyValue);
			} else if (PropertyName.equalsIgnoreCase("linkText")) {
				by = By.linkText(PropertyValue);
			} else if (PropertyName.equalsIgnoreCase("partialLinkText")) {
				by = By.partialLinkText(PropertyValue);
			} else {
				// Handle unsupported PropertyName type
				throw new IllegalArgumentException("Unsupported PropertyName type: " + PropertyName);
			}
			try {
				webElement = driver.findElement(by);
				webElements = driver.findElements(by);
				ConnectDataSheet.DataFieldRead();
			} catch (Exception e) {

//				System.err.println(e.getMessage());
				e.printStackTrace();
				ConnectDataSheet.DataFieldRead();
				fail++;
			}
		}
		else {
			ConnectDataSheet.DataFieldRead();
		}

	}
}
