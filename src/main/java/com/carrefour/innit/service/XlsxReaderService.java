package com.carrefour.innit.service;

import com.carrefour.innit.domain.Product;
import com.carrefour.innit.service.utilities.DirectoryUtility;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Slf4j
public class  XlsxReaderService {

    private final String product_count;

    public XlsxReaderService(@Value("${api.carrefour.prod.innit.count}")String product_count) {
        this.product_count = product_count;
    }

    public List<Product> readXlsFileToList() throws IOException {

        File excelFile = DirectoryUtility.getFile("sanity-check.xlsx");
        FileInputStream fis = new FileInputStream(excelFile);
        // we create an XSSF Workbook object for our XLSX Excel File
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        // we get first sheet
        XSSFSheet sheet = workbook.getSheetAt(0);
        // we iterate on rows
        Iterator<Row> rowIt = sheet.iterator();
        rowIt.next();
        rowIt.next();
        List<Product> productList = new ArrayList<>();
        Product product;

        int count = Integer.parseInt(product_count);

        while(rowIt.hasNext() && count > 0){
            count--;
            Row row = rowIt.next();
            product = new Product();
                if(row.getCell(0) != null ) {
                    product.setGtin( new BigDecimal(row.getCell(0).toString()).toPlainString() );
                }
            productList.add(product);
        }
        log.info(":: XLSX FILE READ SUCCESSFULLY ::");
        workbook.close();
        fis.close();

        return productList;
    }
}