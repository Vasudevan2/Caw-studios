package table01;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class sampleTable {

	public static void main(String[] args){

//Storing input data in an array
		String arr[] = { "Bob", "20", "male", "George", "42", "male", "sara", "42", "female", "conor", "40", "male",
				"jennifer", "42", "female" };

//Converting the input data as a json data
		String jsonData = "[{\"name\":\"" + arr[0] + "\",\"age\":" + arr[1] + ",\"gender\":\"" + arr[2]
				+ "\"},{\"name\":\"" + arr[3] + "\",\"age\":" + arr[4] + ",\"gender\":\"" + arr[5] + "\"},{\"name\":\""
				+ arr[6] + "\",\"age\":" + arr[7] + ",\"gender\":\"" + arr[8] + "\"},{\"name\":\"" + arr[9]
				+ "\",\"age\":" + arr[10] + ",\"gender\":\"" + arr[11] + "\"},{\"name\":\"" + arr[12] + "\",\"age\":"
				+ arr[13] + ",\"gender\":\"" + arr[14] + "\"}]";

		// Create a new instance of ChromeDriver
		WebDriver driver = new ChromeDriver();

		// Navigate to the given URL
		driver.get("https://testpages.herokuapp.com/styled/tag/dynamic-table.html");
		
		//Maximize the window 
		driver.manage().window().maximize();
		//Add Timeout for findElement and findElements
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		// Click on the "Table Data" button
		WebElement tableDataButton = driver.findElement(By.xpath("//*[contains(text(),'Table Data')]"));
		tableDataButton.click();

		// Find the input text box and insert the given data
		WebElement inputTextBox = driver.findElement(By.id("jsondata"));
		inputTextBox.clear();
		inputTextBox.sendKeys(jsonData);

		// Click on the "Refresh Table" button
		WebElement refreshTableButton = driver.findElement(By.id("refreshtable"));
		refreshTableButton.click();
		//Fetching the newly populated data from the table
		List<WebElement> populatedData = driver.findElements(By.xpath("//*[@id='dynamictable']/tr/td"));
		//Initiated for loop for interacting with each element fetched
		for (int i = 0; i < populatedData.size(); i++) {
			//Iterating through each elements to get the element text
			String populatedDatatext = populatedData.get(i).getText();
			//Asserting the stored data with the populated data from the table
			Assert.assertEquals(populatedDatatext, arr[i]);
			//Printed for console reference
			System.out.println(populatedDatatext+" : " + arr[i]);
		}
		//Quitting the browser
		driver.quit();


}
}
