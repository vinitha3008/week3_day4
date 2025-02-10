package week3.day4;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SnapDeal {

	public static void main(String[] args) throws InterruptedException, IOException  {
		// TODO Auto-generated method stub
		ChromeDriver driver=new ChromeDriver(); 
		//Loading the URL -get
		driver.get("https://www.snapdeal.com/");
		
		//Maximize the browser
	     driver.manage().window().maximize();
	     
	     driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	    
	     WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
	     Actions action=new Actions(driver);
	     //focus on mens fashion
	     WebElement MensElem = driver.findElement(By.xpath("//span[@class='catText']"));
	     action.moveToElement(MensElem).perform();
	     //find sports shoes and click on it
	     MensElem.findElement(By.xpath("//span[text()='Sports Shoes']")).click();
	     //get the count of sports shoe
	     WebElement shoes_count = driver.findElement(By.xpath("//span[@class='category-name category-count']"));
	     System.out.println("Shoes count: "+shoes_count.getText());
	     //click on training shoe
	     driver.findElement(By.xpath("//div[text()='Training Shoes']")).click();
	     
	     List<WebElement> count = driver.findElements(By.xpath("//div[@class='product-tuple-description ']"));
	     System.out.println(count.size());
	     
	     //click on drop down of short by
	     driver.findElement(By.xpath("//div[@class='sort-selected']")).click();
	     //click on low to high element from that drop down
	     driver.findElement(By.xpath("//li[@class='search-li']")).click();
	     Thread.sleep(2000);
	     //get list of sorted shoe products in list
	     List<WebElement> sorted = driver.findElements(By.xpath("//span[@class='lfloat product-price']"));
	    List<WebElement> s=new ArrayList<WebElement>();
	    s.addAll(sorted);
	   //check shoe list are in shorted order or not
	     for(int i=0;i<sorted.size();i++)
	     {
	    	 if(s.get(i).getText().equals(sorted.get(i).getText()))
	    	 {
	    		 continue;
	    	 }
	    	 else
	    	 {
	    		 System.out.println("not filtered");
	    	 }
	     }
	     System.out.println("elements are sorted");
	     //enter min price in text box
	     Actions a=new Actions(driver);
	     WebElement min_price = driver.findElement(By.xpath("(//input[@class='input-filter'])[1]"));
	     a.moveToElement(min_price).perform();
	     min_price.clear();
	     a.click(min_price).sendKeys("500").perform();
	   
	     //find that max price text box
	     WebElement Max_price = driver.findElement(By.name("toVal"));
	     a.moveToElement(Max_price).perform();
	     Max_price.clear();
	     a.click().sendKeys("700").perform();
	     //click on go button
	     driver.findElement(By.xpath("//div[@class='price-go-arrow btn btn-line btn-theme-secondary']")).click();
	     //choosen by color
	     WebElement color = driver.findElement(By.xpath("//label[@for='Color_s-White%20%26%20Blue']"));
	     a.scrollToElement(color).moveToElement(color).click().perform();
	     wait.until(ExpectedConditions.elementToBeClickable(color));
	     color.click();
	     Thread.sleep(2000);
	   //mouse hover on that shoe image
	     WebElement mouseon_img = driver.findElement(By.xpath("//img[@class='product-image wooble']"));
	     a.moveToElement(mouseon_img).perform();
	     //click on quick view button
	     WebElement Q_view = driver.findElement(By.xpath("//div[@class='clearfix row-disc']//div"));
	     a.moveToElement(Q_view).click().perform();
	     //get the price and discount of the shoe from new window
	     System.out.println("shoe cost is: "+driver.findElement(By.xpath("//div[@class='product-price pdp-e-i-PAY-l clearfix']//span")).getText());
	     System.out.println("discount: "+driver.findElement(By.xpath("//div[@class='product-price pdp-e-i-PAY-l clearfix']//span[2]")).getText());
	     //focus on shoe image and get a screenshot
	     WebElement shoe_pic = driver.findElement(By.xpath("//div[@class='col-xs-11 quickViewModal ']"));
	     File source = shoe_pic.getScreenshotAs(OutputType.FILE);
	     File destination=new File("./ScrnShot/shoe.png");
	     FileUtils.copyFile(source, destination);
	    // close current window using button
	     driver.findElement(By.xpath("//div[@class='close close1 marR10']")).click();
	     driver.close();
	     
	}

}




