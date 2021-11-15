import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class Json04DeleteAlbumsTest {
    private final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private final String directory = "albums";


    @Test
    public void jasonDeleteAlbum(){
        Response response = given()
                .when()
                .delete(BASE_URL + "/" + directory + "/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

    }


}
