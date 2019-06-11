package org.sgtngmvnallure.admin;

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
public class AdminFlowTest extends LoginTest {
    @Test(dependsOnMethods = "loginTest")
    public void adminUserLogsIn() {
        //driver.findElements(By.xpath(".//*[text()=' automation ']")).get(1).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("admin-portal"));
        saveTextLog("Admin User Flow Started");

    }
}
