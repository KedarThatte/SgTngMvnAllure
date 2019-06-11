package org.sgtngmvnallure;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.sgtngmvnallure.core.BaseDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.sgtngmvnallure.listeners.TestListener.saveTextLog;

/**
 * @author kedarthatte
 */
public class LoginTest extends BaseDriver {

    @Test
    @Step
    public void loginTest(){
        String curl=driver.getCurrentUrl();

        if (curl.equalsIgnoreCase(config.getProperty("usersite"))){
            driver.findElements(By.xpath(".//*[text()=' My Account ']")).get(1).click();
            driver.findElements(By.xpath(".//a[text()=' Login']")).get(1).click();
            driver.findElement(By.xpath(".//input[@placeholder='Email']")).click();
            driver.findElement(By.xpath(".//input[@placeholder='Email']")).sendKeys(config.getProperty("username"));
            driver.findElement(By.xpath(".//input[@placeholder='Email']")).click();
            driver.findElement(By.xpath(".//input[@placeholder='Password']")).sendKeys(config.getProperty("userpassword"));
            driver.findElement(By.xpath(".//button[text()='Login']")).isDisplayed();
            driver.findElement(By.xpath(".//button[text()='Login']")).click();
            Assert.assertTrue(driver.findElement(By.xpath(".//h3[contains(text(),'automation tester')]")).isDisplayed());
            saveTextLog("Normal User logged in successfully");
        }else if (curl.equalsIgnoreCase(config.getProperty("adminsite"))){
            saveTextLog("Will open Admin Site");

        }
    }
}
