import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class Json02PostAlbumsTest {
    private final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private final String DIRECTORY = "albums";
    private static Faker faker;
    private String fakeTitle;
    private int fakeId;

    @BeforeAll
    public static void methodBeforeAll() {
        faker = new Faker();
    }

    @BeforeEach
    public void methodBeforeEach() {
        fakeId = faker.number().randomDigit();
        fakeTitle = faker.lorem().sentence();
    }

    @Test
    public void jsonGetAlbumsPostRequest() {
        JSONObject newAlbum = new JSONObject();

        newAlbum.put("userId", fakeId);
        newAlbum.put("title", fakeTitle);

        Response response = given()
                .contentType("application/Json")
                .body(newAlbum.toString())
                .when()
                .post(BASE_URL + "/" + DIRECTORY + "/")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .response();
        JsonPath json = response.jsonPath();
        Assertions.assertEquals(fakeTitle, json.get("title"));
    }
}
