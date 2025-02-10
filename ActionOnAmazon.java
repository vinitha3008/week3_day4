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

public class ActionOnAmazon {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		ChromeDriver driver=new ChromeDriver(); 
		//Loading the URL -get
		driver.get("https://www.amazon.in/n");
		
		//Maximize the browser
	     driver.manage().window().maximize();
	     
	     driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	     
	     //enter data in search box
	     driver.findElement(By.id("twotabsearchtextbox")).sendKeys("oneplus 9 pro");
	     //click on search button
	     driver.findElement(By.id("nav-search-submit-button")).click();
	     //get the price of first product
	     WebElement pro_price = driver.findElement(By.xpath("//span[@class='a-price']"));
	     System.out.println(pro_price.getText());
	     
	    String price=pro_price.getText();
	     //click on first product using link text
	     driver.findElement(By.xpath("//a[@class='a-link-normal s-line-clamp-2 s-link-style a-text-normal']//h2//span")).click();
	     //transfering driver focus to child window
	     Set<String> windowHandles = driver.getWindowHandles();
	     List<String> windowList=new ArrayList<String>(windowHandles);
	     driver.switchTo().window(windowList.get(1));
	     
	     //get the rating
	     WebElement rating = driver.findElement(By.xpath("//a[@class='a-popover-trigger a-declarative']//span"));
	     System.out.println("Rating: "+rating.getText());
	     //find product image element
	     WebElement prod_img = driver.findElement(By.className("imgTagWrapper"));
	     //take a screen shot
	     File source = prod_img.getScreenshotAs(OutputType.FILE);
	     File destination=new File("./ScrnShot/product.png");
	     FileUtils.copyFile(source, destination);
	     
	     //click on add to cart button
	     WebElement addto_cart = driver.findElement(By.xpath("(//input[@id='add-to-cart-button']/following-sibling::span)[2]"));
	     Actions a=new Actions(driver);
	     a.scrollToElement(addto_cart).perform();
	     
	     
	     WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
	     wait.until(ExpectedConditions.elementToBeClickable(addto_cart));
	     a.moveToElement(addto_cart).click().perform();
	     Thread.sleep(2000);
	     
	     //get subtotal
	     WebElement amount = driver.findElement(By.xpath("//div[@class='a-column a-span11 a-text-left a-spacing-top-large']//span[2]//span"));
	     System.out.println("subtotal is : "+amount.getText());
	     String amnt=amount.getText();
	     String[] sp = amnt.split("\\.");
	     System.out.println(sp.length);
	     System.out.println(sp[0]);
	     
	     //check subtotal and prod_price
	     if(sp[0].equals(price))
	     {
	    	 System.out.println("total amount is correct");
	     }
	     else
	     {
	    	 System.out.println("amount is mismatched");
	     }
	}

}

