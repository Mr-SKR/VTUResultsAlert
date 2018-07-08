package main.java;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class Alert {

	public Alert(String usn, Boolean cbcs, int semester) {
		
		File temp = null;
		
		try {
            String filename = "/main/resources/chromedriver.exe";
            InputStream fi = Alpha.class.getResourceAsStream(filename);
            temp = File.createTempFile("temp_exe", "");
            OutputStream fo = new FileOutputStream(temp);
            byte[] b = new byte[1024];
            int count = 0;
            while ((count = fi.read(b)) != -1) {
                fo.write(b, 0, count);
            }
            fi.close();
            fo.close();

            //System.out.println(temp.canExecute());
            Runtime.getRuntime().exec(temp.getPath());

        } catch (Exception e) {
            e.printStackTrace();
        }
		
		//System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", temp.getPath());
		
		while(true)
		{
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--headless");
			WebDriver driver = new ChromeDriver(chromeOptions);
			if(cbcs == true)
				driver.get("http://results.vtu.ac.in/vitaviresultcbcs18/index.php");
			else
				driver.get("http://results.vtu.ac.in/vitaviresultnoncbcs18/index.php");
			
		
			WebElement element = driver.findElement(By.xpath(".//*[@id='raj']/div[1]/div/input"));
			
			element.sendKeys(usn);
		
			element = driver.findElement(By.id("submit"));
			element.click();
			Boolean validSemester = true;	
			try 
			{ 
				driver.switchTo().alert().dismiss();
			} 
			catch (NoAlertPresentException Ex) 
			{ 
				try {
					String sem = driver.findElement(By.xpath(".//*[contains(text(), 'Semester')]")).getText();
					sem = sem.substring(11);
					if(Integer.parseInt(sem) != semester)
					{
						validSemester = false;
					}
				} catch (Exception e) {
					
				}
				if(validSemester)
				{
					driver.manage().window().fullscreen();
					File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
					try {
						String demo = System.getProperty("user.home");
						FileUtils.copyFile(src, new File(demo+"\\Desktop\\MyVtuResult.png"));
					}
				 
					catch (IOException e)
					{
						System.out.println(e.getMessage());
				 
					}
				
					
					try{
					    AudioInputStream audioInputStream =
					        AudioSystem.getAudioInputStream(
					            this.getClass().getResource("/main/resources/alarm.wav"));
					    Clip clip = AudioSystem.getClip();
					    clip.open(audioInputStream);
					    clip.start();
					}
					catch(Exception ex)
					{
						System.out.println(ex);
					}
				}
				
			}
			driver.quit();
			try {
				//Check for results every 30 seconds
				//30000 = 30s
				//60000 = 1m
				//Do not set this number too low which might result in your IP being blocked by the page
				Thread.sleep(30000);
			} catch(Exception e)
			{
				System.out.println("Thread error:" + e);
			}
			
		}		
	}
	

}
