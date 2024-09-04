package pfa.org;

import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashSet;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

class CSVSQLInterface{
    File FileCSVPath;
    HashSet<String> headers;
    HashSet<String> selectedColumns = new HashSet<>();
    CSVSQLInterface(String CSVFilePath){
        this.FileCSVPath = new File(CSVFilePath);
        try{
        CSVParser parser = new CSVParserBuilder().withSeparator(',').withIgnoreQuotations(true).build();
        CSVReader reader = new CSVReaderBuilder(new FileReader(FileCSVPath)).withCSVParser(parser).build();
        headers = new HashSet<>(Arrays.asList(reader.readNext()));
        }
        catch(Exception e)
        {System.out.println("Headers Failed to Add!");
        }
       
    }


    public void selectNewColumns(String[] columns){
        selectedColumns.clear();
        for(String checkColumns: columns){
            for(String header: this.headers){
                if(header.contains(checkColumns)){
                    selectedColumns.add(checkColumns);
                    System.out.println("Selection Successful: "+checkColumns);
                    break;
                }
             }
        }
        System.out.println("Column Selection Ended.");
    }
    public void readCSV(){
        try{
        final CSVParser parser = new CSVParserBuilder().withSeparator(',').withIgnoreQuotations(true).build();
        final CSVReader reader = new CSVReaderBuilder(new FileReader(FileCSVPath)).withSkipLines(1).withCSVParser(parser).build();
        String[] nextRecord; 
  
            // we are going to read data line by line 
            while ((nextRecord = reader.readNext()) != null) { 
                for (String cell : nextRecord) { 
                    System.out.print(cell + "\t"); 
                } 
                System.out.println(); 
            } 
        }
        catch(Exception e){
            System.out.println("Something went wrong!");
            e.printStackTrace();
        }
    }
    //Creates Inserts based on parameters,
    public void createInsertSQL(){

    }
}
