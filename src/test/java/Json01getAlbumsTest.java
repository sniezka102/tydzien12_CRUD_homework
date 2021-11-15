import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class Json01getAlbumsTest {
    private final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private final String directory = "albums";

    @Test
    public void jsongetalbumsREGULARAllRequest(){
        Response response = given()
                .when()
                .get(BASE_URL + "/" + directory)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        List<String> title = json.getList("title");

        System.out.println(response.asString());

        System.out.println("\nNumber of tiles in albums " + title.size() + "\n");
        Assertions.assertEquals(100, title.size());
        System.out.println("Tiles: ");
        title.stream()
                .forEach(System.out::println);
    }

    @Test
    public void jsongetalbumsREGULAROneRequest(){
        Response response = given()
                .when()
                .get(BASE_URL + "/" + directory+"/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        System.out.println(response.asString());
        Assertions.assertEquals("quidem molestiae enim",json.get("title"));
    }


    @Test
    public void jsongetalbumsPATHVARIABLESRequest(){

        Response response = given()
                .pathParam("id", 33)
                .when()
                .get(BASE_URL + "/" + directory + "/" + "{id}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();
        JsonPath json = response.jsonPath();
        Assertions.assertEquals("iste eos nostrum", json.get("title"));

    }

    @Test
    public void jasongetalbumsQUERRYRequest(){

        Response response = given()
                .queryParam("title","iste eos nostrum")
                .when()
                .get(BASE_URL + "/" + directory)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        Assertions.assertEquals("iste eos nostrum", json.getList("title").get(0));

    }
}
