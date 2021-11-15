import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import java.util.List;
import static io.restassured.RestAssured.given;

public class Json05getUsersFindEmailplTest {

    private final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private final String directory = "users";


    @Test
    public void readUsersAndFindUserWithPlEmailFor(){

        Response response = given()
                .when()
                .get(BASE_URL + "/" + directory)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        List<String> email = json.getList("email");

  //      System.out.println(response.asString());
        System.out.println(email.size());

        for (String x : email) {
            if (x.endsWith("biz")) {
                System.out.println(x);

            }
        }

    }


}
