package testautomationu;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testautomationu.pages.InventoryPage;
import testautomationu.pages.LoginPage;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class InventoryTest {

    private ExtentReports extent;
    private ExtentTest test;

    @BeforeMethod
    public void setUp() {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("test-output/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @Test
    public void AñadirProducto() {

        System.setProperty("webdriver.chrome.driver","/Users/darwoft/Documents/AutomationProjects/driver/chromedriver-mac-x64/chromedriver");
        WebDriver driver = new ChromeDriver();
        try {
            System.out.println("Hello and welcome!");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            driver.get("https://www.saucedemo.com/");
            LoginPage loginPage = new LoginPage(driver);
            loginPage.loginStandard("standard_user", "secret_sauce");
            InventoryPage inventoryPage = new InventoryPage(driver);
            inventoryPage.addItem();
            Assert.assertEquals(Integer.parseInt(inventoryPage.getCartItemNumber()), 1);
            System.out.println("Se añadio un item al carrito");
        }catch (Exception e) {
                File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                try {
                    FileHandler.copy(screenshotFile, new File("ruta/del/archivo/fallo.png"));
                    System.out.println("Captura de pantalla guardada en caso de fallo.");
                } catch (IOException ioException) {
                    System.out.println("No se pudo guardar la captura de pantalla: " + ioException.getMessage());
                }
            }
        finally {
                driver.quit();
            }
    }

    @Test
    public void EliminarUnProducto() {
        System.out.println("Hello and welcome!");
        System.setProperty("webdriver.chrome.driver", "/Users/darwoft/Documents/AutomationProjects/driver/chromedriver-mac-x64/chromedriver");
        WebDriver driver = new ChromeDriver();
       try{
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.saucedemo.com/");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginStandard("standard_user", "secret_sauce");
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addItem();
        System.out.println("Se añadio un item al carrito");
        Assert.assertEquals(Integer.parseInt(inventoryPage.getCartItemNumber()), 1);
        inventoryPage.removeItem();
        inventoryPage.getCartItemNumber();
        } catch (Exception e) {
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                FileHandler.copy(screenshotFile, new File("/Users/darwoft/Documents/AutomationProjects/Selenium_Interview_Practice/src/screensot/fallo.png"));
                System.out.println("Captura de pantalla guardada en caso de fallo.");
            } catch (IOException ioException) {
                System.out.println("No se pudo guardar la captura de pantalla: " + ioException.getMessage());
            }
        }
        finally {
            driver.quit();
        }
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Test exitoso");
        } else if (result.getStatus() == ITestResult.FAILURE) {
            test.fail("Test fallido");
        }
        extent.flush();
    }
}
