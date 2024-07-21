import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class RestAssuredTests {

    @Test
    public void testGetEmployees() {
        RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1";

        // 1. Servise istek atılır
        Response response = given()
                .when()
                .get("/employees")
                .then()
                .statusCode(200) // 2. HTTP status 200 döndüğü kontrol edilir
                .extract().response();

        // 3. 24 adet kaydın geldiği kontrol edilir
        response.then().body("data.size()", is(24));

        // 4. employee_salary değeri 313500 olan kaydın employee_name değerinin "Haley Kennedy" olduğu kontrol edilir
        response.then().body("data.find { it.employee_salary == 313500 }.employee_name", equalTo("Haley Kennedy"));
    }
}

