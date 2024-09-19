package pfa.org;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

class CSVManager {
    private LinkedList<String> headers;
    File srcFile;
    
    CSVManager(String srcFile){
        this.srcFile = new File(srcFile);
        CSVReader reader = initializeReader();
        try{
        headers = new LinkedList<>(Arrays.asList(reader.readNext()));
        }
        catch(IOException|CsvValidationException e){
            System.out.println("Headers failed to initialize.");
        }
        System.out.println("CSV Initialization Successful!");
    }

    public LinkedList<String> CSVFindAllInColumn(String word){
        LinkedList<String> returnValues = new LinkedList<>();
        CSVReader reader = initializeReader(1); 
        try{
        String[] addString;
        while((addString = reader.readNext())!=null){
                returnValues.add(addString[headers.indexOf(word)].trim());
        }
        return returnValues;
        }
        catch(IOException|CsvValidationException e){
            System.out.println("Find Columns failed to initialize.");
        }
        return null;
    }
    public boolean verifyColumns(String columns){
        for(String head: headers){
            if(head.equals(columns)){
                return true;
            }
        }
        return false;
    }
    public LinkedHashSet<String> CSVFindUniqueInColumn(String word){
        LinkedHashSet<String> returnValues = new LinkedHashSet<>();
        CSVReader reader = initializeReader(1);
        try{
            String[] addString;
            while((addString = reader.readNext())!=null){
                    if(addString.equals(""))
                        continue;
                    String[] parsedString = parseString(addString[headers.indexOf(word)]);
                    for(String pString : parsedString)
                    returnValues.add(pString);
            }
            return returnValues;
            }
            catch(IOException|CsvValidationException e){
                System.out.println("Find Columns failed to initialize.");
            }
            return null;
        }
    private CSVReader initializeReader(int start){
        try{
         CSVParser parser = new CSVParserBuilder().withSeparator(',').withIgnoreQuotations(false).build();
         CSVReader reader = new CSVReaderBuilder(new FileReader(this.srcFile)).withSkipLines(start).withCSVParser(parser).build();
         return reader;
        }catch(FileNotFoundException e){
            System.out.println("File Not Found!");
        }
        return null;
    }
    private String[] parseString(String parseString){  
        parseString = parseString.replace("\n",",");
        parseString = parseString.toUpperCase();
        String[] parsedString = parseString.split(",");
        for(int i =0; i<parsedString.length;i++){
             parsedString[i] = parsedString[i].trim();
            }
         return parsedString;
    }
}
