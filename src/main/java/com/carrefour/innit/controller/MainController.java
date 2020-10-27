package com.carrefour.innit.controller;

import com.carrefour.innit.domain.Product;
import com.carrefour.innit.domain.Token;
import com.carrefour.innit.service.ApiService;
import com.carrefour.innit.service.CheckInnitService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.IOException;

@AllArgsConstructor
@RestController
@RequestMapping("/product")
public class MainController {

    private ApiService apiService;
    private CheckInnitService checkInnitService;

    @GetMapping("/{ean}/diet/{diet}")
    public ResponseEntity<Product> productWithDietFilter(@PathVariable("ean") String ean, @PathVariable("diet") String diet) throws IOException {

        return new ResponseEntity<Product>(checkInnitService.productWithDietFilter( ean, diet), HttpStatus.OK);

    }
    @GetMapping("/{ean}/avoid/{avoid}")
    public ResponseEntity<Product> productWithAvoidableFilter(@PathVariable("ean") String ean, @PathVariable("avoid") String avoid) throws IOException {

        return new ResponseEntity<Product>(checkInnitService.productWithAvoidableFilter( ean, avoid), HttpStatus.OK);

    }
}
