package fr.training.spring.library.infrastructure.http;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OpenLibraryRestConfig {

    private static final String rootUri = "https://openlibrary.org";
    @Bean
    public RestTemplate getRestClient(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.rootUri(rootUri).build();
    }

}
