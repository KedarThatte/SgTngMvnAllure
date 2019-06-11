package org.sgtngmvnallure.config;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

/**
 * @author kedarthatte
 */
public class ConfigDataLoad {

    public static Properties config;


    protected WebDriverWait wait;
    private String testCaseName;
    private long testStartTime;
    private long testFinishTime;
    private long testDurationTime;
    private String testFailedMsg;

    protected long getTestStartTime() {
        return testStartTime;
    }

    protected void setTestStartTime(long testStartTime) {
        this.testStartTime = testStartTime;
    }

    protected long getTestFinishTime() {
        return testFinishTime;
    }

    protected void setTestFinishTime(long testFinishTime) {
        this.testFinishTime = testFinishTime;
    }

    protected long getTestDurationTime() {
        return testDurationTime;
    }

    protected void setTestDurationTime(long testDurationTime) {
        this.testDurationTime = testDurationTime;
    }

    protected String getTestCaseName()
    {
        return testCaseName;
    }

    protected void setTestCaseName(String testCaseName) {
        this.testCaseName = testCaseName;
    }

    public String getTestFailedMsg() {
        return testFailedMsg;
    }

    public void setTestFailedMsg(String testFailedMsg) {
        this.testFailedMsg = testFailedMsg;
    }

    /*public void setLocalBrowserDrivers(String browser){
        String path = (System.getProperty("user.dir")+ File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+"drivers");
        File file = new File(path+File.separator+browser+"driver");
        System.out.println(file.getAbsolutePath());
        System.setProperty("webdriver."+browser+".driver",file.getAbsolutePath());
    }*/

    @BeforeSuite
    public void loadConfig() throws IOException {
        config= new Properties();
        config.load(ConfigDataLoad.class.getClassLoader().getResourceAsStream("config.properties"));
        System.out.println("Config loaded!");
        /*setLocalBrowserDrivers("chrome");
        setLocalBrowserDrivers("gecko");*/
    }

}
