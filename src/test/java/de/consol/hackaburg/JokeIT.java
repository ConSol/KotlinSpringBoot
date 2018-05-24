package de.consol.hackaburg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.testng.annotations.Test;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.dsl.testng.TestNGCitrusTestRunner;
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.http.server.HttpServer;

public class JokeIT extends TestNGCitrusTestRunner {

    @Autowired
    private HttpClient client;

    @Autowired
    private HttpServer backend;

    @Test
    @CitrusTest
    public void getRandomJokeIT() {

        final ClassPathResource endpointResponse = new ClassPathResource("payloads/joke.json");

        variable("expected", "Product Owners never ask Chuck Norris for more features. They ask for mercy.");

        http(action -> action.client(client).send().get("/joke").accept(MediaType.APPLICATION_JSON_VALUE).fork(true));

        http(action -> action.server(backend).receive().get("/jokes/random"));

        http(action -> action.server(backend).respond(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).payload(endpointResponse));

        http(action -> action.client(client).receive().response(HttpStatus.OK).payload("${expected}"));
    }

    @Test
    @CitrusTest
    public void getFallbackJokeIT() {

        final ClassPathResource endpointResponse = new ClassPathResource("payloads/failure.json");

        variable("expected", "Germans don't laugh...");

        http(action -> action.client(client).send().get("/joke").accept(MediaType.APPLICATION_JSON_VALUE).fork(true));

        http(action -> action.server(backend).receive().get("/jokes/random"));

        http(action -> action.server(backend).respond(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON_VALUE).payload(endpointResponse));

        http(action -> action.client(client).receive().response(HttpStatus.OK).payload("${expected}"));
    }
}
