package fr.codeanddata.microstack.web.it;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class MicrostackWebResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
                .when().get("/microstack-web")
                .then()
                .statusCode(200)
                .body(is("Hello microstack-web"));
    }
}
