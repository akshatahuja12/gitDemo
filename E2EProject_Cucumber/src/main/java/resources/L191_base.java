package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

@SuppressWarnings("unused")
public class L191_base {
	
	public WebDriver driver; // Initialize driver globally so that its scope is valid throughout.
	//you can give static to run tcs in sequential mode -> static creates only 1 memory allocation for all tcs and each tc overwrites the previous object.
	//For parallel execution don't give static or create a local driver var to copy original var and use the local to run test.
	public Properties prop; //Initialize properties globally so that all classes inheriting base class can ue it just like driver.
	
	public WebDriver initializeDriver() throws IOException {
		prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\browserData.properties");
		
		prop.load(fis);		
		
		
		//Instead of giving this, use System.getProperty - so that you can directly pass browser value through maven using "mvn test -DpropertyName=value"
		String browserName = prop.getProperty("browser");
		
		//String browserName = System.getProperty("browser");
		//By doing this you can then further give browser directly from Jenkins
		
		if(browserName.contains("chrome")){ 
			//code to execute tests in chrome
			
			ChromeOptions options = new ChromeOptions();//gives many options/ behaviors to be injected into chromeDriver using "options" object.
			
			if(browserName.contains("headless")) { //only run in headless mode when given in mvn command or passed in jenkins
				options.addArguments("headless"); //runs browser in headless mode - browser won't open but tcs will run in background using chrome binaries.
			}
			
			System.setProperty("webdriver.chrome.driver","C:\\Program Files\\chromedriver_win32\\chromedriver.exe"); //make this path dynamic - put chromedriver in resources and use user.dir property.
			driver = new ChromeDriver(options); //pass options to chromeDriver	
			
		}
		
		else if(browserName.equals("firefox")) {//== wont work as its a value of a property. use equals() instead.
			//code to execute tests in firefox
			System.setProperty("webdriver.gecko.driver","C:\\Program Files\\geckodriver-v0.30.0-win64\\geckodriver.exe");
			driver = new FirefoxDriver();
			
		}
		
		else if(browserName.equals("edge")) {
			System.setProperty("webdriver.edge.driver","C:\\Program Files\\edgedriver_win64\\msedgedriver.exe");
			driver = new EdgeDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); //wait mechanism is valid throughout where driver will be used
		return driver; // return driver object to whoever calls this method
	}
	
	public String getScreenShotPath(String testCaseName, WebDriver driver) throws IOException {
		
		TakesScreenshot ts  = (TakesScreenshot) driver;	
		File sourceFile = ts.getScreenshotAs(OutputType.FILE); // The source file is a virtual file created by Selenium and we need to copy it to an actual file in our project director using FileUils.
		String destinationFile =  System.getProperty("user.dir")+"\\Reports\\"+testCaseName+".png";
		FileUtils.copyFile(sourceFile, new File(destinationFile)); // Add apache commons io dependency in pom.xl for FileUtils to work.
		//error will be captured as a screenshot based on method name.
		
		return destinationFile; //return path so that it can be used by extent reports object and screenshot be shown in the report.
		
	}
	
}
