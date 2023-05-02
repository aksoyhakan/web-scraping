import java.io.*;
import java.time.Duration;
import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class Hakan {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		System.setProperty("javax.net.ssl.trustStore", "C:\\Program Files\\Java\\jre1.8.0_361\\lib\\security\\cacerts");
		String file_name="C:\\Users\\Meltem AKSOY\\Desktop\\task.txt";
		
		int maxPage=20;
		String writingLine;
		ChromeDriver driver = new ChromeDriver();
		ChromeDriver driver2 = new ChromeDriver();
     	WriteFile info=new WriteFile(file_name,true);
		for (int page=1;page<=maxPage;page++) {
			
			driver.get("https://www.ilan.gov.tr/ilan/kategori/9/ihale-duyurulari?txv=9&currentPage="+page);
		    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
	        List<WebElement> links = driver.findElements(new By.ByCssSelector("igt-ad-single-list.ng-tns-c138-3.ng-star-inserted ng-component a"));
	        int i=0;
	
		    for (WebElement link:links ) {
		    	i++;
		    	
	        	driver2.get(link.getAttribute("href"));
	        	driver2.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
	        	List<WebElement> title = driver2.findElements(new By.ByCssSelector("div.list-title.ng-tns-c144-3"));
	        	List<WebElement> data = driver2.findElements(new By.ByCssSelector("div.list-desc.ng-tns-c144-3"));
	        	List<WebElement> data2 = driver2.findElements(new By.ByCssSelector("div.mb-5.ng-tns-c144-3 div table tbody tr td"));
	        	List<WebElement> data3 = driver2.findElements(new By.ByCssSelector("div.mb-5.ng-tns-c144-3 table tbody tr td"));
	          	
	          	
	        	if (title.get(8).getText().equals("İhale Kayıt No")) {
	        		if(data2.size()==0&&data3.size()!=0) {
	        			writingLine=("S/N: "+((page-1)*12+i)+"  İhale Kayıt No: "+data.get(8).getText()+"  Niteliği Türü ve Miktarı: "+data3.get(21).getText()+"  İşin Yapılacağı Yer: "+data.get(2).getText()+"  İhale Türü: "+data.get(6).getText());
	        			System.out.println(data3.get(21).getText());
	        		}else if (data2.size()!=0&&data3.size()==0) {
	        			writingLine=("S/N: "+((page-1)*12+i)+"  İhale Kayıt No: "+data.get(8).getText()+"  Niteliği Türü ve Miktarı: "+data2.get(21).getText()+"  İşin Yapılacağı Yer: "+data.get(2).getText()+"  İhale Türü: "+data.get(6).getText());
	        			System.out.println(data2.get(21).getText());
	        		}else {
	        			writingLine=("S/N: "+((page-1)*12+i)+"  İhale Kayıt No: "+data.get(8).getText()+"  İhale Usulü: "+data.get(5).getText()+"  İşin Yapılacağı Yer: "+data.get(2).getText()+"  İhale Türü: "+data.get(6).getText());
	        		}
	        		
	        		
	        	}else {
	        		writingLine=("S/N: "+((page-1)*12+i)+"  İlan Numarası: "+data.get(1).getText()+"  İhale Usulü: "+data.get(5).getText()+"  İşin Yapılacağı Yer: "+data.get(2).getText()+"  İhale Türü: "+data.get(6).getText());
	        	}
	        	
	        	try {
					info.writeToFile(writingLine);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        
	        	
		}
		

		
		
		
		


	       
        
        
        
        
	    
		}      
		
		
	}

}
