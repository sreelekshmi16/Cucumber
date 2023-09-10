package com.unifiedframework.libraries;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;


public class BaseClass {
	private static WebDriver driver;
	private static WebDriverWait wait;
	public static Logger log;
	public String logPath="";
	public static String projectFolder = System.getProperty("user.dir");
	public static ExtentSparkReporter esReporter;
	public static ExtentReports eReports;
	public static ExtentTest eTest;
	private static Date date;
	private static SimpleDateFormat df;
	private static String currentDateTime;
	private static String baseURL= "",log4jConfPath="", projectName="", environment="", excelPath= "", browserName= "";
	public String[] actualArray, expectedArray;
	public String expectedString, actualString;
	public Boolean expectedBool, actualBool;
	public Boolean[] actualArrayBool,expectedArrayBool;
	public ArrayList<String> actualArraylist = new ArrayList<String>();
	public ArrayList<String> expectedArraylist = new ArrayList<String>();
	public ArrayList<Boolean> actualBoolArray = new ArrayList<Boolean>();
	public ArrayList<Boolean> expectedBoolArray = new ArrayList<Boolean>();
	
	public WebDriver openChrome(String URL) {
		try {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			//options.addArguments("--headless");
			driver = new ChromeDriver(options);
			driver.manage().window().maximize();
			driver.get(URL);
			log.info("Url loaded");
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		return driver;
	}

	public WebDriver openFirefox(WebDriver driver, String URL) {
		try {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.get(URL);
			log.info("Url loaded");
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		return driver;
	}

	public WebDriver openEdge(WebDriver driver, String URL) {
		try {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			driver.manage().window().maximize();
			driver.get(URL);
			log.info("Url loaded");
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		return driver;
	}
	
	public void closeBrowser() {
		try {
			driver.quit();
		}catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
	}
	
	public WebDriver driver() {
		return driver;
	}
	
	public String baseURL() {
		return baseURL;
	}
	
	public String projectName() {
		return projectName;
	}
	
	public String environment() {
		return environment;
	}
	
	public String excelPath() {
		return excelPath;
	}
	
	public static void readPorpertyFile() {
		try {
			Properties prop = new Properties();
			File myObj = new File(projectFolder+"/src/main/resources/defaultValues.properties");
			if(myObj.exists()) {
				InputStream input = new FileInputStream(projectFolder+"/src/main/resources/defaultValues.properties");
				prop.load(input);
				baseURL = prop.getProperty("baseURL");
				log4jConfPath = projectFolder + prop.getProperty("log4jConfPath");	
				projectName = prop.getProperty("projectName");
				environment = prop.getProperty("environment");
				excelPath = prop.getProperty("excelPath");
			}
		}catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
	}
	
	public static void initializeLog(String automationType) {
		try {
			log = Logger.getLogger(BaseClass.class.getName());
			System.setProperty("logPath", projectFolder+"/Logs/LogFile_"+automationType+"_"+getCurrentDateTime());
			PropertyConfigurator.configure(log4jConfPath); 
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void extentReport() {
		try {
			log.info("Report status initiated");
			String path = projectFolder+"/Reports/Demo_"+getCurrentDateTime()+".html";
			esReporter = new ExtentSparkReporter(path);
			esReporter.config().setDocumentTitle("Automation Report");
			esReporter.config().setReportName(projectName + " Report");
			esReporter.config().setTheme(Theme.DARK);
			eReports = new ExtentReports();
			eReports.attachReporter(esReporter);
			eReports.setSystemInfo("Project Name", projectName);
			eReports.setSystemInfo("Platform", System.getProperty("os.name"));
			eReports.setSystemInfo("Environment", environment);
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
	}
	
	public static String getCurrentDateTime() {    
		try {
			df= new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
			date = new Date();
			System.setProperty("currentDateTime", df.format(new Date()));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		currentDateTime = df.format(date);
		return currentDateTime;
	}
	
	public void sendKeyElements(WebElement element, String text) {
		try {
			element.click();
			element.clear();
			element.sendKeys(text);
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e);
		}
	}
	
	public void waitForElementToLoad(WebElement element, long seconds) {
		try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
			wait.until(ExpectedConditions.visibilityOf(element));
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}

	}
}
