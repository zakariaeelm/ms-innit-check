package com.carrefour.innit.service;

import com.carrefour.innit.domain.Product;
import com.carrefour.innit.domain.Score;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Slf4j
@Service
public class CheckInnitService {

    public static List<Product> productList;
    private ApiService apiService;
    private XlsxReaderService xlsxReaderService;
    private XlsxWriterService xlsxWriterService;

    @PostConstruct
    synchronized public void init() throws InterruptedException {
        try {
            productList = xlsxReaderService.readXlsFileToList();

            productList.forEach( product -> {
                Product p = apiService.UnigetProductwithParams(product.getGtin(),"","");
                product.setScore(p.getScore());
                product.setBrand(p.getBrand());
                product.setName(p.getName());
            });

            wait(3000);
            processProductsVeggie();
            wait(3000);
            processProductsVegan();
            wait(3000);
            processProductsPork();
            wait(3000);
            processProductsSport();
            wait(3000);
            processProductsMed();
            wait(3000);
            processProductsWeight();

            processAvoidables();

            xlsxWriterService.writeXlsFileToList(productList);
        }
        catch (IOException e) {
            log.error("::: ERROR ::: ", e );
        }
        log.info("::: CHECK INIT DONE :::" + productList.get(6).toString());
    }

    public Product productWithDietFilter(String ean, String diet){
        return apiService.UnigetProductwithParams(ean, diet, "");
    }
    public Product productWithAvoidableFilter(String ean, String avoid){
        return apiService.UnigetProductwithParams(ean, "", avoid);
    }
    public void processProductsVeggie(){
        productList.forEach( product -> {
            product.setScoreVeggie(apiService.UnigetProductwithParams(product.getGtin(),"=0","").getScore());
        });
    }
    public void processProductsVegan(){
        productList.forEach( product -> {
            product.setScoreVegan(apiService.UnigetProductwithParams(product.getGtin(),"=1","").getScore());
        });
    }
    public void processProductsPork(){
        productList.forEach( product -> {
            product.setScorePork(apiService.UnigetProductwithParams(product.getGtin(),"","=89").getScore());
        });
    }
    public void processProductsWeight(){
        productList.forEach( product -> {
            product.setScoreWeight(apiService.UnigetProductwithParams(product.getGtin(),"=3","").getScore());
        });
    }
    public void processProductsSport(){
        productList.forEach( product -> {
            product.setScoreSport(apiService.UnigetProductwithParams(product.getGtin(),"=7","").getScore());
        });
    }
    public void processProductsMed(){
        productList.forEach( product -> {
            product.setScoreMed(apiService.UnigetProductwithParams(product.getGtin(),"=4","").getScore());
        });
    }

    synchronized public void processAvoidables(){
        productList.forEach( product -> {
            try {
                wait(3000);

                Map<String, Score> avoidableScores = new HashMap<String, Score>();
                avoidableScores.put("43", apiService.UnigetProductwithParams(product.getGtin(),"","=43").getScore());
                avoidableScores.put("42", apiService.UnigetProductwithParams(product.getGtin(),"","=42").getScore());
                avoidableScores.put("46", apiService.UnigetProductwithParams(product.getGtin(),"","=46").getScore());
                avoidableScores.put("76", apiService.UnigetProductwithParams(product.getGtin(),"","=76").getScore());
                avoidableScores.put("39", apiService.UnigetProductwithParams(product.getGtin(),"","=39").getScore());
                avoidableScores.put("36", apiService.UnigetProductwithParams(product.getGtin(),"","=36").getScore());
                avoidableScores.put("44", apiService.UnigetProductwithParams(product.getGtin(),"","=44").getScore());
                avoidableScores.put("78", apiService.UnigetProductwithParams(product.getGtin(),"","=78").getScore());
                avoidableScores.put("37", apiService.UnigetProductwithParams(product.getGtin(),"","=37").getScore());
                avoidableScores.put("47", apiService.UnigetProductwithParams(product.getGtin(),"","=47").getScore());
                avoidableScores.put("45", apiService.UnigetProductwithParams(product.getGtin(),"","=45").getScore());
                avoidableScores.put("77", apiService.UnigetProductwithParams(product.getGtin(),"","=77").getScore());
                avoidableScores.put("41", apiService.UnigetProductwithParams(product.getGtin(),"","=41").getScore());
                avoidableScores.put("38", apiService.UnigetProductwithParams(product.getGtin(),"","=38").getScore());
                avoidableScores.put("90", apiService.UnigetProductwithParams(product.getGtin(),"","=90").getScore());

                product.setAvoidableScores(avoidableScores);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}


// Lait/43
// Lactose/42
// Oeuf/46
// Moutarde/76
// Gluten/39
// Arachides/36
// Sulfites/44	Lupin/78	Fruit à coque/37	sésame/47	soja/45	céleri/77	poissons/41	crustacés/38	mollusques/90














