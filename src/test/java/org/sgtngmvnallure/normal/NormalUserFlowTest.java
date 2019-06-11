package org.sgtngmvnallure.normal;

import org.openqa.selenium.By;
import org.sgtngmvnallure.LoginTest;
import org.sgtngmvnallure.listeners.TestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.sgtngmvnallure.listeners.TestListener.saveTextLog;

/**
 * @author kedarthatte
 */
@Listeners(TestListener.class)
public class NormalUserFlowTest extends LoginTest {

    @Test (dependsOnMethods = "loginTest")
    public void normalUserLogsIn(){
        driver.findElements(By.xpath(".//*[text()=' automation ']")).get(1).click();
        Assert.assertTrue(driver.findElements(By.xpath(".//a[text()='  Logout']")).get(1).isDisplayed());
        saveTextLog("User is on his Home Page");
    }
}
