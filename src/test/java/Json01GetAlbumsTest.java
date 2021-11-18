import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class Json01GetAlbumsTest {
    private final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private final String DIRECTORY = "albums";

    @Test
    public void jsonGetAlbumsRegularAllRequest() {
        Response response = given()
                .when()
                .get(BASE_URL + "/" + DIRECTORY)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();
        JsonPath json = response.jsonPath();
        List<String> title = json.getList("title");
        Assertions.assertEquals(100, title.size());
    }

    @Test
    public void jsonGetAlbumsRegularOneRequest() {
        Response response = given()
                .when()
                .get(BASE_URL + "/" + DIRECTORY + "/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();
        JsonPath json = response.jsonPath();
        Assertions.assertEquals("quidem molestiae enim", json.get("title"));
    }

    @Test
    public void jsonGetAlbumsPATHVARIABLESRequest() {
        Response response = given()
                .pathParam("id", 33)
                .when()
                .get(BASE_URL + "/" + DIRECTORY + "/" + "{id}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();
        JsonPath json = response.jsonPath();
        Assertions.assertEquals("iste eos nostrum", json.get("title"));
    }

    @Test
    public void jasonGetAlbumsQueryRequest() {
        Response response = given()
                .queryParam("title", "iste eos nostrum")
                .when()
                .get(BASE_URL + "/" + DIRECTORY)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();
        JsonPath json = response.jsonPath();
        Assertions.assertEquals("iste eos nostrum", json.getList("title").get(0));
    }
}
