package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class Testcase01 {
    WebDriver driver;
    @BeforeMethod
    public void setUp(){
        // setup edge driver
        driver = new EdgeDriver();
        // config edge driver
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        // URT navigation
        driver.navigate().to("https://the-internet.herokuapp.com/login");

    }
    @AfterMethod
    public void teardown(){
        driver.quit();
    }
    @Test
    public void testcase1(){
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.xpath("//*[@class='fa fa-2x fa-sign-in']")).click();
        System.out.println("the url of website is"+driver.getCurrentUrl());
        SoftAssert soft = new SoftAssert();

        soft.assertEquals(driver.getCurrentUrl(),"https://the-internet.herokuapp.com/secure");
        Assert.assertTrue(driver.getCurrentUrl().contains("secure"));

        Assert.assertTrue(driver.findElement(By.id("flash")).getText().contains("logged into"));



    }
}
