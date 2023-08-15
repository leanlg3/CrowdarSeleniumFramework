package testautomationu;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class MercadoLibreTest {

    private ExtentReports extent;
    private ExtentTest test;

    @BeforeMethod
    public void setUp() {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("test-output/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @Test
    public void testDepartmentsExistence() {
        Response response = RestAssured.get("https://www.mercadolibre.com.ar/menu/departments");

        // Verificar el código de respuesta
        int statusCode = response.getStatusCode();
        assertTrue(statusCode == 200, "El código de respuesta no es 200");

        // Verificar que la respuesta contenga departamentos
        String responseBody = response.getBody().asString();
        assertTrue(responseBody.contains("departments"), "La respuesta no contiene departamentos");
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
