MyStepdefs:  Using H2 database locally, created a file registrationdetails csv file with columns registraionnumber,make,color fields.

used wiremock in sprintboot framework, its easy to run the tests on a virtual database when compared to physical, in this way we can reduce the time taken in 
running the tests.


package acceptancetest;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;

import javax.validation.Payload;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class MyStepdefs extends CucumberRoot {

    private String payload;
    private ResponseEntity<String> response; // output

    private HttpHeaders headers = new HttpHeaders();

    private String path;

    @Value("${tenantId}")
    private String tenantId;

    @Value("${userId}")
    private String userId;

    @Value("${source}")
    private String source;

    @Value("${accept}")
    private String accept;



    @Before
    public void setUp() {
        headers = new HttpHeaders();
        headers.set("tenantId",tenantId);
        headers.set("userId",userId);
        headers.set("source",source);
        headers.set("accept",accept);
    }

            

  @Given("iam on the dvla website")
    public void Iamonthevvlawebsite () {			
			
	   
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\devintest\\geckodriver-v0.19.0-win64\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("https://www.gov.uk/get-vehicle-information-from-dvla");
                driver.findElement(By.className("pub-c-title__text"));
        	driver.findElement(By.linkText("Start now")).click();
       		 WebDriverWait wait = new WebDriverWait(driver, 200);
		
	   
	}
                   }

@When (a valid "registration number" is enterered from a file") 
public void avalidregistrationnumber (registrationnumber) {

        driver.findElement(By.id("Vrm")).sendKeys(registrationNumber);
        driver.findElement(By.name("Continue")).submit();
        return wait;
   
}


@When (a invalid "registration number" is enterered from a file") 
public void avalidregistrationnumber (registrationnumber) {

        driver.findElement(By.id("Vrm")).sendKeys(registrationNumber);
        driver.findElement(By.name("Continue")).submit();
        return wait;
   
}


@Then(a warning message "registration number does not exists")
public void warningmessgedisplayed(msg)
{
  
        WebElement newPgElement = driver.findElement(By.xpath("//div/p/strong"));
        wait.until(ExpectedConditions.visibilityOf(newPgElement));
        WebElement newPageElement =  driver.findElement(By.className("heading-large"));
        wait.until(ExpectedConditions.visibilityOf(newPageElement));
        assertEquals(newpageElement.text,msg);
}


@then(a the error message "enter a valid registration number"is displayed ")
public void warningmessage(msg)
{

         WebElement errorElement = driver.findElement(By.id("Vrm-error"));
         assertEquals(errorElement.text,msg);

}


private void addTestData() throws IOException {
        String fileName = "sql/custom-fields-inserts.sql";

        ClassLoader classLoader = getClass().getClassLoader();
      // some times the classLoader.getResource(fileName) is returning NULL
  //     File file = new File("C:\\Users\\src\\integration-test\\resources\\sql\\registrationdetails.sql");
       File file = new File(Objects.requireNonNull(classLoader.getResource(fileName)).getFile());
        Files.lines(file.toPath()).forEach(insertStatement -> {

            try (Statement statement = dataSource.getConnection()
                    .createStatement()) {
                statement.execute(insertStatement);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        });
    }


       public void cleanupTestData() throws Exception {
       String cleanupSql = "delete from custom_field_definitions";

        try (Statement statement = dataSource.getConnection()
                .createStatement()) {
            statement.execute(cleanupSql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


   

    @And("matchs with the values in the file")
    public void whenQueryTheDatabaseForCustom_field_definitionsTableTheFollowingValuesExist(DataTable customFields) {
        List<Map<String, String>> listOfMaps = customFields.asMaps();
        listOfMaps.forEach(map -> {

            String sql = String.format("Select * from custom_field_definitions where name = '%s' and label = '%s'", map.get("name"), map.get("label"));
            try (Statement statement = dataSource.getConnection()
                    .createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(sql)) {
                    if (resultSet.next()) {
                        map.keySet().forEach(field -> {
                            try {
                                assertFieldExistInDB(map, resultSet, field);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        });
                    } else {
                        fail("No rows where returned from database");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    private void assertFieldExistInDB(Map<String, String> map, ResultSet resultSet, String fieldName) throws SQLException {
        assertThat(fieldName + "value does not match", resultSet.getString(fieldName), is(map.get(fieldName)));
    }




    @Given("the path to get registraion detials is {string}")
    public void thePathToGetregistrationdetails(String path) {
        this.path = path;
    }


    @When("And the registrationservice is running")
    public void iMakeAGetRequestWithTheAboveInformation() {
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<Object> entityWithHeaders = new HttpEntity<>(headers);
        response = template.exchange(path, HttpMethod.GET, entityWithHeaders, String.class);
    }

    @Given("I clean and add test data")
    public void iCleanAndAddTestData() throws Exception {
        cleanupTestData();
        addTestData();
    }

    @And("I read test data")
    public void iReadTestData() throws SQLException {
            String sql = ("Select * from registratiiondetails where make = '%s' and color = '%s'");
            Statement statement = dataSource.getConnection()
                    .createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
               System.out.println(resultSet);


















