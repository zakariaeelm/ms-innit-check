package com.carrefour.innit.service;

import com.carrefour.innit.domain.Product;
import com.carrefour.innit.service.utilities.DirectoryUtility;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


@Service
@Slf4j
public class XlsxWriterService {

    public void writeXlsFileToList(List<Product> productList) throws IOException {
        //Blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook();

        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("Employee Data");

        int rownum = 2;
        for (Product product : productList)
        {
            //create a row of excelsheet
            Row row = sheet.createRow(rownum++);

            //get object array of prerticuler key
            //Object[] objArr = data.get(key);

            int cellnum = 0;

            Cell cell = row.createCell(0);
            cell.setCellValue((String) product.getGtin());
            cell = row.createCell(1);
            cell.setCellValue((String) product.getName());
            cell = row.createCell(2);
            cell.setCellValue((String) product.getBrand());
            cell = row.createCell(3);
            if(product.getScore() != null) cell.setCellValue(String.valueOf(product.getScore().getDefaultScore()));

            // Diets
            cell = row.createCell(4);
            if(product.getScoreVeggie() != null) cell.setCellValue(String.valueOf(product.getScoreVeggie().getFit()));
            cell = row.createCell(5);
            if(product.getScoreVegan() != null) cell.setCellValue(String.valueOf(product.getScoreVegan().getFit()));
            cell = row.createCell(6);
            if(product.getScorePork() != null) cell.setCellValue(String.valueOf(product.getScorePork().getFit()));
            cell = row.createCell(7);
            if(product.getScoreWeight() != null) cell.setCellValue(String.valueOf(product.getScoreWeight().getFit()));
            cell = row.createCell(8);
            if(product.getScoreSport() != null) cell.setCellValue(String.valueOf(product.getScoreSport().getFit()));
            cell = row.createCell(9);
            if(product.getScoreMed() != null) cell.setCellValue(String.valueOf(product.getScoreMed().getFit()));

            // Avoidables
            cell = row.createCell(10);
            if(product.getAvoidableScores().get("43") != null) cell.setCellValue(String.valueOf(product.getAvoidableScores().get("43").getFit()));
            cell = row.createCell(11);
            if(product.getAvoidableScores().get("42") != null) cell.setCellValue(String.valueOf(product.getAvoidableScores().get("42").getFit()));
            cell = row.createCell(12);
            if(product.getAvoidableScores().get("46") != null) cell.setCellValue(String.valueOf(product.getAvoidableScores().get("46").getFit()));
            cell = row.createCell(13);
            if(product.getAvoidableScores().get("76") != null) cell.setCellValue(String.valueOf(product.getAvoidableScores().get("76").getFit()));
            cell = row.createCell(14);
            if(product.getAvoidableScores().get("39") != null) cell.setCellValue(String.valueOf(product.getAvoidableScores().get("39").getFit()));
            cell = row.createCell(15);
            if(product.getAvoidableScores().get("36") != null) cell.setCellValue(String.valueOf(product.getAvoidableScores().get("36").getFit()));
            cell = row.createCell(16);
            if(product.getAvoidableScores().get("44") != null) cell.setCellValue(String.valueOf(product.getAvoidableScores().get("44").getFit()));
            cell = row.createCell(17);
            if(product.getAvoidableScores().get("78") != null) cell.setCellValue(String.valueOf(product.getAvoidableScores().get("78").getFit()));
            cell = row.createCell(18);
            if(product.getAvoidableScores().get("37") != null) cell.setCellValue(String.valueOf(product.getAvoidableScores().get("37").getFit()));
            cell = row.createCell(19);
            if(product.getAvoidableScores().get("47") != null) cell.setCellValue(String.valueOf(product.getAvoidableScores().get("47").getFit()));
            cell = row.createCell(20);
            if(product.getAvoidableScores().get("45") != null) cell.setCellValue(String.valueOf(product.getAvoidableScores().get("45").getFit()));
            cell = row.createCell(21);
            if(product.getAvoidableScores().get("77") != null) cell.setCellValue(String.valueOf(product.getAvoidableScores().get("77").getFit()));
            cell = row.createCell(22);
            if(product.getAvoidableScores().get("41") != null) cell.setCellValue(String.valueOf(product.getAvoidableScores().get("41").getFit()));
            cell = row.createCell(23);
            if(product.getAvoidableScores().get("38") != null) cell.setCellValue(String.valueOf(product.getAvoidableScores().get("38").getFit()));
            cell = row.createCell(24);
            if(product.getAvoidableScores().get("90") != null) cell.setCellValue(String.valueOf(product.getAvoidableScores().get("90").getFit()));

        }
        try
        {
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream( DirectoryUtility.getFile("sanity-check-result.xlsx"));
            workbook.write(out);
            out.close();
            System.out.println(":: XLSX FILE WRITTEN SUCCESSFULLY ON DISK ::");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}