package org.fneto.copadeequipes.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class EquipeClient {

    @Value("${api.equipe}")
    private String equipeApi;
    private WebClient client;

    public WebClient getClient() {
        if (client == null) {
            client = WebClient.create(equipeApi);
        }
        return client;
    }
}
