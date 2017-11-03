package data;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Trong on 30/10/2017.
 */
public class InputFile {
    private String patch = "";

    public InputFile() {}

    public InputFile(String patch) {
        this.patch = patch;
    }

    public  String getPatch() {
        return this.patch;
    }

    public void setPatch(String patch) {
        this.patch = patch;
    }

    public HashMap<String, Word> loadMap(){
        HashMap<String, Word> map = new HashMap<>();

        try{
            FileInputStream inputStream = new FileInputStream(new File(patch));
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheetAt( 0);
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                /*while (cellIterator.hasNext()){

                }*/
                    String eng="" ;
                    String vie="" ;
                //doc ô 1
                        if (cellIterator.hasNext()){
                        Cell keyEnglish = cellIterator.next();
                        eng = keyEnglish.getStringCellValue();
                        //eng = eng.trim();

                    }
                // đọc ô 2
                    if (cellIterator.hasNext()){
                        Cell keyVietNam = cellIterator.next();
                        vie = keyVietNam.getStringCellValue();
                        vie = vie.trim();
                    }
                    Word newWord = new Word(eng, vie);
                //System.out.println("."+newWord.getEnglish()+".");
                map.put(newWord.getEnglish(), newWord);

            }
        }catch (IOException e){
            e.printStackTrace();
        }

        return map;
    }
}
