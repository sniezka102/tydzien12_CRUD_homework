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

public class Json03PutPatchRandomAlbumsTest {
    private final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private final String DIRECTORY = "albums";
    private static Faker fakerObj;
    private String fakeTitle;
    private int fakeUserId;
    private static JSONObject album;

    @BeforeAll
    public static void beforeAllTest() {
        fakerObj = new Faker();
        album = new JSONObject();
    }

    @BeforeEach
    public void beforeEachTest() {
        fakeUserId = fakerObj.number().randomDigit();
        fakeTitle = fakerObj.lorem().sentence();
        album.put("userId", fakeUserId);
        album.put("title", fakeTitle);
    }

    @Test
    public void jsonPutAlbums() {
        Response response = given()
                .contentType("application/Json")
                .body(album.toString())
                .when()
                .put(BASE_URL + "/" + DIRECTORY + "/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();
        JsonPath json = response.jsonPath();
        Assertions.assertEquals(fakeTitle, json.get("title"));
    }

    @Test
    public void jsonPatchAlbum() {
        Response response = given()
                .contentType("application/Json")
                .body(album.toString())
                .when()
                .patch(BASE_URL + "/" + DIRECTORY + "/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();
        JsonPath json = response.jsonPath();
        Assertions.assertEquals(fakeTitle, json.get("title"));
    }
}
