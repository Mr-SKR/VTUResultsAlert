
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;

public class Alert {

	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		
		
		while(true)
		{
			WebDriver driver = new ChromeDriver();
			driver.get("http://results.vtu.ac.in/vitaviresultnoncbcs/index.php");
			//FOR CBCS Scheme results, Replace the above line with below line
			//driver.get("http://results.vtu.ac.in/vitaviresultcbcs/index.php");
		
			WebElement element = driver.findElement(By.xpath(".//*[@id='raj']/div[1]/div/input"));
			
			//Replace my USN with yours
			element.sendKeys("1cr14is101");
		
			element = driver.findElement(By.id("submit"));
			element.click();
		
			try 
			{ 
				driver.switchTo().alert().dismiss();
			} 
			catch (NoAlertPresentException Ex) 
			{ 
				driver.manage().window().fullscreen();
				File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				try {
					FileUtils.copyFile(src, new File(".\\Screenshots\\Myresult.png"));
				}
			 
				catch (IOException e)
				{
					System.out.println(e.getMessage());
			 
				}
			
				String songName = "alarm.mp3";
				String pathToMp3 = System.getProperty("user.dir") +"/"+ songName;
				BasicPlayer player = new BasicPlayer();
				try {
					player.open(new URL("file:///" + pathToMp3));
					player.play();
				} catch (BasicPlayerException | MalformedURLException e) {
					e.printStackTrace();
				}
			}
			driver.quit();
			try {
				//Check for results every 30 seconds
				//30000 = 30s
				//60000 = 1m
				//Do not get this number too low which might result in your IP being blocked by the page
				Thread.sleep(30000);
			} catch(Exception e)
			{
				System.out.println("Thread error:" + e);
			}
			
		}		
	}

}
