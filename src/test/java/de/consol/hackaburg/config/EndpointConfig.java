package de.consol.hackaburg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.consol.citrus.dsl.endpoint.CitrusEndpoints;
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.http.server.HttpServer;

@Configuration
public class EndpointConfig {

    @Bean
    public HttpClient hackaburgClient() {
        return CitrusEndpoints.http()
                .client()
                .requestUrl("http://localhost:8080")
                .build();
    }

    @Bean
    public HttpServer hackaburgServer() {
        return CitrusEndpoints.http()
                .server()
                .port(5000)
                .autoStart(true)
                .build();
    }
}
