package week3.day4;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BigBasket {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		ChromeDriver driver=new ChromeDriver(); 
		//Loading the URL -get
		driver.get("https://www.bigbasket.com/");
		
		//Maximize the browser
	     driver.manage().window().maximize();
	     driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	     
	     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        
	     Actions a=new Actions(driver);
	     //select category button
	     driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	     WebElement sortby = driver.findElement(By.xpath("(//button[contains(@class,'h-full px-2.5')])[2]"));
	     a.moveToElement(sortby).click().perform();
	     Thread.sleep(2000);
	     //find food grains oil and masala
	     WebElement mousehover_ele = driver.findElement(By.xpath("//div[@class='CategoryMenu___StyledMenuItems-sc-d3svbp-4 fpskRu']//nav//ul//li[5]"));
	     //wait.until(ExpectedConditions.elementToBeClickable(mousehover_ele));
	     a.moveToElement(mousehover_ele).perform();
	     
	     Thread.sleep(2000);
	     //find rice and rice products
	     WebElement mousehover_ele2 = driver.findElement(By.xpath("//div[@class='CategoryMenu___StyledMenuItems-sc-d3svbp-4 fpskRu']//nav//ul[2]//li[7]"));
	     //wait.until(ExpectedConditions.elementToBeClickable(mousehover_ele2));
	     a.moveToElement(mousehover_ele2).perform();
	     Thread.sleep(2000);
	    //find and click on Boiled & Steam Rice
	     WebElement mousehover_ele3 = driver.findElement(By.xpath("//div[@class='CategoryMenu___StyledMenuItems-sc-d3svbp-4 fpskRu']//nav//ul[3]//li[2]"));
	    // wait.until(ExpectedConditions.elementToBeClickable(mousehover_ele3));
	     a.moveToElement(mousehover_ele3).click().perform();
	     //find bb royal brand 
	     WebElement filterCheckBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='i-BBRoyal']")));
	     a.moveToElement(filterCheckBox).click().perform();
	    Thread.sleep(2000);
	     //click on tamil ponni boiled rice
	     WebElement tamil_poinni = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='break-words h-10 w-full'])[5]//h3")));
	     a.moveToElement(tamil_poinni).click().perform();
	     //window handling
	     Set<String> windowHandles = driver.getWindowHandles();
	   //set to list
			List<String> allWindows=new ArrayList<String>(windowHandles);
			
			driver.switchTo().window(allWindows.get(1));
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[@class='justify-between cursor-pointer rounded-2xs overflow-hidden mb-2.5 hover:shadow-1 border flex bg-white min-h-16 border-silverSurfer-400']//div//div//div//span")).click();
			//get the price of 5kg pag
			String price = driver.findElement(By.xpath("//table//tr[2]//td")).getText();
			System.out.println(price);
			//click on add to cart button
			driver.findElement(By.xpath("//div[@class='flex flex-col lg:w-60 xl:w-82']//button")).click();
			Thread.sleep(2000);
			//verify the toast message
			System.out.println("message is: "+driver.findElement(By.xpath("//div[@class='Toast___StyledDiv-sc-1uergwq-0 kLrmgR']/p")).getText());
			
			//take a screenshot
			File source = driver.getScreenshotAs(OutputType.FILE);
			File destination=new File("./ScrnShot/bigbasket.png");
			FileUtils.copyFile(source, destination);
			driver.close();
			//switch to parent window to close
			driver.switchTo().window(allWindows.get(0));
			driver.close();
			
	}

}
