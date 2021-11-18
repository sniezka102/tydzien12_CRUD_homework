import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class Json04DeleteAlbumsTest {
    private final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private final String DIRECTORY = "albums";

    @Test
    public void jasonDeleteAlbum() {
        given()
                .when()
                .delete(BASE_URL + "/" + DIRECTORY + "/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();
    }
}
