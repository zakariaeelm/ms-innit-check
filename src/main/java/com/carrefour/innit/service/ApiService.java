package com.carrefour.innit.service;

import com.carrefour.innit.domain.Product;
import com.carrefour.innit.domain.Token;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.net.ssl.SSLContext;

@Slf4j
@Service
public class ApiService {

    private CloseableHttpClient httpClient;

    private final String INNIT_BASE_URL;
    private final String INNIT_PROD_SCOPE;
    private final String INNIT_PROD_TOKEN;

    public ApiService(CloseableHttpClient httpClient,
                      @Value("${api.carrefour.prod.innit.baseurl}") String INNIT_BASE_URL,
                      @Value("${api.carrefour.prod.innit.scope}") String INNIT_PROD_SCOPE,
                      @Value("${api.carrefour.prod.innit.token}") String INNIT_PROD_TOKEN) {

        this.httpClient = httpClient;
        this.INNIT_BASE_URL = INNIT_BASE_URL;
        this.INNIT_PROD_SCOPE = INNIT_PROD_SCOPE;
        this.INNIT_PROD_TOKEN = INNIT_PROD_TOKEN;
    }
    public Product UnigetProductwithParams(String ean, String diet, String toAvoid){
        try {
            String uri = INNIT_BASE_URL +
                    ean + "?desirables=&undesirables=&avoidables" +
                    toAvoid + "&qualifications=69&diets" +
                    diet + "&demography=adultMale&country=FR&lang=fr";
            log.info(" :: URI :: " + uri );

            Unirest.setHttpClient(httpClient);
            HttpResponse<String> response = Unirest.get(uri)
                    .header("Authorization", INNIT_PROD_SCOPE + " " + INNIT_PROD_TOKEN)
                    .asString();

            if(response.getStatus() == 200) {
                ObjectMapper mapper = new ObjectMapper();
                Product product = mapper.readValue(response.getBody(), Product.class);

                return product;
            } else return new Product();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}