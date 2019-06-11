package org.sgtngmvnallure.core;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.sgtngmvnallure.config.ConfigDataLoad;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.sgtngmvnallure.listeners.TestListener.saveTextLog;

/**
 * @author kedarthatte
 */
public class BaseDriver extends ConfigDataLoad {

    public RemoteWebDriver driver;
    public String URL ,Node;
    public static ThreadLocal<RemoteWebDriver> dr = new ThreadLocal<RemoteWebDriver>();
    public String parentwindowhandle;

    public static RemoteWebDriver getDriver() {
        return dr.get();
    }

    public void setWebDriver(RemoteWebDriver driver) {
        dr.set(driver);
    }

    @BeforeClass
    @Parameters({"browser","usertype"})
    public RemoteWebDriver launchApplication(String browser,String usertype) throws MalformedURLException {
        System.out.println(config.getProperty("usersite"));
        System.out.println(config.getProperty("adminsite"));

        Node="http://localhost:4444/wd/hub/";
        //Node = "http://ondemand.eu-central-1.saucelabs.com:80/wd/hub";
        //DesiredCapabilities cap=null;

        if(browser.equalsIgnoreCase("chrome")){
            ChromeOptions options = new ChromeOptions();
            options.setCapability("browser","chrome");
            options.setCapability("acceptSslCerts", "true");
            options.addArguments("chrome.switches", "--disable-extensions --disable-extensions-file-access-check --disable-extensions-http-throttling --disable-infobars --enable-automation --start-maximized");
            //options.addArguments("--headless");
            driver=new RemoteWebDriver(new URL(Node),options);

        }else if(browser.equalsIgnoreCase("firefox")){
            FirefoxOptions options= new FirefoxOptions();
            options.setCapability("browser","firefox");
            //options.setHeadless(true);
            options.setAcceptInsecureCerts(true);
            driver=new RemoteWebDriver(new URL(Node),options);

        }else if(browser.equalsIgnoreCase("opera")){
            OperaOptions options = new OperaOptions();
            //options.setCapability("browser","opera");
            options.setCapability("browserName","operablink");
            //options.setCapability("platform","MAC");
            driver=new RemoteWebDriver(new URL(Node),options);

        }else if (browser.equalsIgnoreCase("safari")){
            SafariOptions options=new SafariOptions();
            options.setCapability("browser","safari");
            //options.setCapability("technologyPreview","true");
            options.setCapability("platform","MAC");
            driver=new RemoteWebDriver(new URL(Node),options);

        }else if(browser.equalsIgnoreCase("msedge")){
            EdgeOptions options = new EdgeOptions();
            options.setCapability("browser","msedge");
            options.setCapability("platformName","WIN10");
            driver=new RemoteWebDriver(new URL(Node),options);

        }else if(browser.equalsIgnoreCase("ie")){
            InternetExplorerOptions options = new InternetExplorerOptions();
            options.setCapability("browser","ie");
            options.setCapability("platform","WINDOWS");
            driver=new RemoteWebDriver(new URL(Node),options);
        }
        setWebDriver(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(getUrlFromUserType(usertype));
        driver.manage().window().maximize();
        saveTextLog(driver.getCurrentUrl());
        parentwindowhandle=driver.getWindowHandle();
        return driver;
    }
    @AfterClass
    public void tearDown(){
        //driver.close();
        driver.quit();
    }

    public void windowswitchback(){
        driver.switchTo().window(parentwindowhandle);
    }
    public void windowSwitch(){
        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }

    }
    public void haltscript(int milliseconds){
        try{
            Thread.sleep(milliseconds);
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public String getUrlFromUserType(String utype) {
        if (utype.equalsIgnoreCase("normal")) {
            URL = (config.getProperty("usersite"));

        } else if (utype.equalsIgnoreCase("admin")) {
            URL = (config.getProperty("adminsite"));

        }
        System.out.println(URL);
        return URL;
    }




}
