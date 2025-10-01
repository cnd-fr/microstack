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
                .when().get("/hello")
                .then()
                .statusCode(200)
                .body(is("hello"));

      given()
        .when().post("/hello")
        .then()
        .statusCode(200)
        .body(is("hello"));

      given()
        .when().put("/hello")
        .then()
        .statusCode(405);
    }

  @Test
  public void testHelloNameEndpoint() {
    given()
      .when().get("/hello/path/pathtoto")
      .then()
      .statusCode(200)
      .body(is("hello pathtoto"));
  }

  @Test
  public void testHelloNameReEndpoint() {
    given()
      .when().get("/hello/re/retoto")
      .then()
      .statusCode(200)
      .body(is("hello retoto"));
  }

  @Test
  public void testHelloNameReGrEndpoint() {
    given()
      .when().get("/hello/regr/regrtoto")
      .then()
      .statusCode(200)
      .body(is("hello regrtoto"));
  }
}
