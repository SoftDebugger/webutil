package cn.buglife.selenium;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Harry.Zhang
 * @since 0.1.0
 */
public class LoginTest {

    private WebDriver webDriver;

    @BeforeClass
    public void setUpBeforeClass() throws Exception {
        System.setProperty("webdriver.chrome.driver", "D:\\tool\\chromedriver.exe");
    }

    @Before
    public void setUp() throws Exception {
        this.webDriver = new ChromeDriver();
        this.webDriver.get("http://zhangjun817.duapp.com");
    }

    @Test
    public void testLogin() throws Exception {
        WebDriverWait wait = new WebDriverWait(webDriver, 5);

        webDriver.findElement(By.linkText("Sign in")).click();
        webDriver.findElement(By.name("username")).sendKeys("harry.zhang@buglife.cn");
        webDriver.findElement(By.name("password")).sendKeys("buglife.cn");
        webDriver.findElement(By.id("login")).click();
        webDriver.findElement(By.className("glyphicon-log-out")).click();
    }

}
