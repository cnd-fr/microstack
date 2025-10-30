package fr.codeanddata.microstack.blocks.it;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.matchesPattern;

@QuarkusTest
public class MicrostackBlocksResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
                .when().get("/microstack-blocks")
                .then()
                .statusCode(200)
                .body(matchesPattern(Pattern.compile("^\\s*start\\s+hello\\s+end\\s*$", Pattern.MULTILINE | Pattern.DOTALL)));
    }
}
