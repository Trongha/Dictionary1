package data;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class OldWord {
    private static Queue<Word> oldWordQueue = new LinkedList<Word>();
    private final String path = "src\\data\\dataFile\\xlsx\\oldWord\\oldWord.xlsx";

    public OldWord() {
    }

    public void input(){
        try{
            FileInputStream inputStream = new FileInputStream(new File(path));
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheetAt( 0);
            Iterator<Row> rowIterator = sheet.iterator();

            //Bor dongf ddaafu
            if (rowIterator.hasNext()){
                Row row = rowIterator.next();
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                String nameGroup = path.substring(path.lastIndexOf('\\')+1, path.lastIndexOf('.'));

                String eng="" ;
                String vie="" ;
                String pathImage = "";
                String level = "";
                //doc ô 1
                if (cellIterator.hasNext()){
                    Cell keyEnglish = cellIterator.next();
                    eng = keyEnglish.getStringCellValue();
                    eng = eng.trim();
                }
                // đọc ô 2
                if (cellIterator.hasNext()){
                    Cell keyVietNam = cellIterator.next();
                    vie = keyVietNam.getStringCellValue();
                    vie = vie.trim();
                }
//                đọc ô 3
                if (cellIterator.hasNext()){
                    Cell pathImageCell = cellIterator.next();
                    level = pathImageCell.getStringCellValue().toString();
                }
                //Đọc ô 4
                if (cellIterator.hasNext()){
                    Cell pathImageCell = cellIterator.next();
                    pathImage = pathImageCell.getStringCellValue().toString();
                }

                if (!vie.equals("") && !eng.equals("")){
                    Word newWord = new Word(eng, vie, pathImage, nameGroup);
                    newWord.setLevel(level);
                    oldWordQueue.add(newWord);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println(String.format("%-70s", path) + " Load Complete!");
    }

    public void ghiTieuDe(XSSFSheet sheet){
        Row row = sheet.createRow(0);
        int cellNum = 0;
        row.createCell(cellNum++).setCellValue("English");
        row.createCell(cellNum++).setCellValue("Tieng Viet");
        row.createCell(cellNum++).setCellValue("Level");
        row.createCell(cellNum++).setCellValue("PathImage");
    }

    public void output(){
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();

        ghiTieuDe(sheet);

        int rowNum = 1;
        //Ghi tieu de:

        while (!oldWordQueue.isEmpty()){
            Word word = oldWordQueue.poll();
            Row row = sheet.createRow(rowNum++);
            int cellNum = 0;

            row.createCell(cellNum++).setCellValue(word.getEnglish());
            row.createCell(cellNum++).setCellValue(word.getVietNam());
            row.createCell(cellNum++).setCellValue(word.getLevel().toString());
            row.createCell(cellNum++).setCellValue(word.getPathImage());
        }
        try{
            System.out.println(this.path);
            FileOutputStream out = new FileOutputStream(new File(this.path));
            workbook.write(out);
            out.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addOldWord(Word word){
        System.out.println("add word to oldWords " + word.getEnglish());
        if (!oldWordQueue.contains(word)){
            oldWordQueue.add(word);
            System.out.println("add Complete");
        }
    }

    public HashMap<String, Word> getOldestWords(int n){
        System.out.println("deQueue");
        HashMap<String, Word> mapOldWord = new HashMap<String, Word>();
        if (!oldWordQueue.isEmpty()){
            if (n > oldWordQueue.size()){
                n = oldWordQueue.size();
            }
            for (int i=0 ; i<n ; i++){
                Word word = oldWordQueue.poll();
                mapOldWord.put(word.getEnglish(), word);
            }
        }
        return mapOldWord;
    }

    public Group getGroupOldestWord(int n){
        Group group = new Group();
        group.setName("Oldest Words");
        group.setListWords(this.getOldestWords(n));
        return group;
    }

    @Override
    public String toString() {
        String s = "";
        while (!oldWordQueue.isEmpty()){
            Word word = oldWordQueue.poll();
            s += String.format("%s", word.toString());
        }
        return s;
    }
}
