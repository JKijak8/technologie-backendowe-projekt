package com.jkpbmz.technologiebackendoweprojekt.config;

import org.jspecify.annotations.NullMarked;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Configuration
public class ApiClientConfig {
    @Bean
    public RestClient weatherClient(
            @Value("${external.weather-api.url}") String url,
            @Value("${external.weather-api.key}") String key
    ) {
        return RestClient.builder()
                .baseUrl(url)
                .requestInterceptor(((request, body, execution) -> {
                    URI modifiedUri = UriComponentsBuilder.fromUri(request.getURI())
                            .queryParam("appid", key)
                            .build()
                            .toUri();

                    HttpRequest modifiedRequest = new HttpRequestWrapper(request) {
                        @Override
                        @NullMarked
                        public URI getURI() {
                            return modifiedUri;
                        }
                    };

                    return execution.execute(modifiedRequest, body);
                }))
                .defaultHeader("Accept", "application/json")
                .build();
    }
}
