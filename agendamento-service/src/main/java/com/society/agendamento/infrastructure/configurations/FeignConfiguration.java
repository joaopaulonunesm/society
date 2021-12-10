package com.society.agendamento.infrastructure.configurations;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.security.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

@Configuration
@RequiredArgsConstructor
public class FeignConfiguration {

    @Value("${society-security.oauth2.access-token-uri}")
    private String accessTokenUri;

    @Value("${society-security.oauth2.client-id}")
    private String clientId;

    @Value("${society-security.oauth2.client-secret}")
    private String clientSecret;

    @Bean
    public ClientCredentialsResourceDetails clientCredentialsResourceDetails() {
        ClientCredentialsResourceDetails clientCredentialsResourceDetails = new ClientCredentialsResourceDetails();
        clientCredentialsResourceDetails.setAccessTokenUri(accessTokenUri);
        clientCredentialsResourceDetails.setClientId(clientId);
        clientCredentialsResourceDetails.setClientSecret(clientSecret);
        return clientCredentialsResourceDetails;
    }

    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor(ClientCredentialsResourceDetails clientCredentialsResourceDetails) {
        return new OAuth2FeignRequestInterceptor(new DefaultOAuth2ClientContext(), clientCredentialsResourceDetails) {

            @Override
            public void apply(RequestTemplate template) {
                super.apply(template);

                OAuth2AccessToken oAuth2AccessToken = super.getToken();

                template.header(HttpHeaders.AUTHORIZATION, String.format("%s %s", oAuth2AccessToken.getTokenType(), oAuth2AccessToken.getValue()));
            }
        };
    }

}
