package org.sgtngmvnallure.listeners;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.sgtngmvnallure.core.BaseDriver;
import org.slf4j.MDC;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.util.concurrent.TimeUnit;

/**
 * @author kedarthatte
 */
public class TestListener extends BaseDriver implements ITestListener {

    private String testResult;

    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {

        return message;
    }

    @Attachment(value = "{0}", type = "text/html")
    public static String attachHtml(String html) {
        return html;
    }

    private String getTestResult() {
        return testResult;
    }

    private void setTestResult(String testResult) {
        this.testResult = testResult;
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    private byte[] saveScreenshotPNG(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Override
    public void onTestStart(ITestResult result) {
        MDC.put("testMethodName", result.getName());
        MDC.put("deviceName", "Ahmet");
        MDC.put("platformVersion", "10.2");
        MDC.put("appVersion", "3.4.1");
        System.out.println("Test "+getTestCaseName()+" Execution Started");
        Reporter.log("Test "+getTestCaseName()+" Execution Started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        setTestResult("PASSED");
        setTestFailedMsg("-");
        saveTextLog(getTestCaseName()+" - "+result.getMethod()+" - "+getTestResult());
        System.out.println(getTestCaseName()+" - "+result.getMethod()+" - "+getTestResult());
        Reporter.log(getTestCaseName()+" - "+result.getMethod()+" - "+getTestResult());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        setTestResult("FAILED");
        setTestFailedMsg(result.getThrowable().getMessage());
        Object testClass = result.getInstance();
        WebDriver driver = ((BaseDriver) testClass).getDriver();
        //Allure ScreenShotRobot and SaveTestLog
        if (driver != null) {
            saveScreenshotPNG(driver);
        }
        //Save a log on allure.
        saveTextLog(getTestCaseName()+" - "+result.getMethod()+" - "+getTestResult());
        //Take base64Screenshot screenshot for extent reports
        assert driver != null;
        String screenshot =
                "data:image/png;base64," + ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        System.out.println(getTestCaseName()+" - "+result.getMethod()+" - "+getTestResult());
        Reporter.log(getTestCaseName()+" - "+result.getMethod()+" - "+getTestResult());

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        setTestResult("SKIPPED");
        saveTextLog("Test "+getTestCaseName()+" "+getTestResult());
        System.out.println(getTestCaseName()+" - "+result.getMethod()+" - "+getTestResult());
        Reporter.log(getTestCaseName()+" - "+result.getMethod()+" - "+getTestResult());

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        result.getMethod().getConstructorOrMethod().getName();

    }

    @Override
    public void onStart(ITestContext context) {
        setTestCaseName(context.getName());
        setTestStartTime(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
        //MDC.put("ip", ip.getIP());
        MDC.put("testCaseName", getTestCaseName());

    }

    @Override
    public void onFinish(ITestContext context) {
        setTestFinishTime(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
        setTestDurationTime(getTestFinishTime() - getTestStartTime());
        MDC.put("testFailedMsg", getTestFailedMsg());
        MDC.put("testDuration", String.valueOf(getTestDurationTime()));

    }


}

