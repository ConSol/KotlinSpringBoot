package de.consol.hackaburg;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.dsl.testng.TestNGCitrusTestRunner;
import com.consol.citrus.http.client.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Test;

public class GreetingIT extends TestNGCitrusTestRunner {

    @Autowired
    private HttpClient httpClient;

    @Test
    @CitrusTest
    public void testGlobalGreeter() {
        http(action -> {
            action.client(httpClient)
                    .send()
                    .get("/greet");
        });

        http(action -> {
            action.client(httpClient)
                    .receive()
                    .response(HttpStatus.OK)
                    .payload("Hello world!");
        });
    }

    @Test
    @CitrusTest
    public void testPersonalGreeter() {

        variable("testPerson", "Simon");

        http(action -> {
            action.client(httpClient)
                    .send()
                    .get("/greet")
                    .queryParam("name", "${testPerson}");
        });

        http(action -> {
            action.client(httpClient)
                    .receive()
                    .response(HttpStatus.OK)
                    .payload("Hello ${testPerson}!");
        });
    }
}
