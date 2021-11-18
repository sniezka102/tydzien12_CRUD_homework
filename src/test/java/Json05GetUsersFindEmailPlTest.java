import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class Json05GetUsersFindEmailPlTest {
    private final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private final String DIRECTORY = "users";

    @Test
    public void readUsersAndFindUserWithPlEmailFor() {
        Response response = given()
                .when()
                .get(BASE_URL + "/" + DIRECTORY)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();
        JsonPath json = response.jsonPath();
        List<String> emails = json.getList("email");

        List<String> emailsPl = emails.stream()
                .filter(em -> em.endsWith(".pl"))
                .collect(Collectors.toList());
        Assertions.assertEquals(0, emailsPl.size());
    }
}
