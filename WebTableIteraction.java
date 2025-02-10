package week3.day4;


import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class WebTableIteraction {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ChromeDriver driver=new ChromeDriver(); 
		 //get the url
	     driver.get("https://erail.in/");
		
		//Maximize the browser
	     driver.manage().window().maximize();
	     
	     driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	    
	     //type from station
	     driver.findElement(By.id("txtStationFrom")).sendKeys("MAS");
	    //type to station
	     driver.findElement(By.id("txtStationTo")).sendKeys("MDU");
	     //click on sort on date checkbox
	     driver.findElement(By.id("chkSelectDateOnly")).click();
	     //get column count
	     List<WebElement> coumn_count = driver.findElements(By.xpath("//table[@class='DataTable TrainList TrainListHeader stickyTrainListHeader']//tbody//tr//th"));
	     
	     System.out.println("coumn_count: "+coumn_count.size());
	     //get row count
	     List<WebElement> row_count = driver.findElements(By.xpath("//table[@class='DataTable TrainList TrainListHeader stickyTrainListHeader']//tbody//tr"));
	    System.out.println("row_count: "+row_count.size());
	     
	     //to fetch train name
	     List<String> names=new ArrayList<String>();
	     System.out.println("train names:");
	     for(int i=2;i<row_count.size();i++)
	     {
	    	 WebElement name = driver.findElement(By.xpath("//table[@class='DataTable TrainList TrainListHeader stickyTrainListHeader']//tbody//tr["+i+"]//td[2]"));
	    	 System.out.println(name.getText());
	    	 String n=name.getText();
	    	 names.add(n);
	 
	     }
	     System.out.println("--------------------------");
	   System.out.println("train names without duplicates:");
	   
	   Set<String> train_names=new LinkedHashSet<>(names);
	   for(String t:train_names)
	   {
		System.out.println(t);
	   }
	   }
	   
	   
	   }


