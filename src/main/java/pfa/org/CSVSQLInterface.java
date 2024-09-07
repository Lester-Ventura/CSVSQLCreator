package pfa.org;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

class CSVSQLInterface{
    private File FileCSVPath;
    private LinkedList<LinkedList<String>> rawCSV;
    private LinkedList<String> headers;
    private HashSet<String> selectedColumns;
    private String tableName;
    CSVSQLInterface(String CSVFilePath){
        this.FileCSVPath = new File(CSVFilePath);
        try
        {
        //Get the headers for indexing later.
        CSVParser parser = new CSVParserBuilder().withSeparator(',').withIgnoreQuotations(false).build();
        CSVReader reader = new CSVReaderBuilder(new FileReader(FileCSVPath)).withCSVParser(parser).build();
        headers = new LinkedList<>(Arrays.asList(reader.readNext()));
        // System.out.println("Size of Header: "+headers.size());
        rawCSV = new LinkedList<>();
        // Puts the entire data in a map.
        System.out.println(headers.toString());
        String[] line;
        // int index = 1 ; This is for testing
        LinkedList<String> putIntoMap = new LinkedList<>();
            while((line = reader.readNext())!=null)
            {
                for(String text:line )
                {
                // System.out.println("Line "+index+":"+ text);
                // index++;
                    if(putIntoMap.size()<headers.size()-1)
                    putIntoMap.add(text);
                    else
                    {
                    putIntoMap.add(text);
                    rawCSV.add(putIntoMap);
                    putIntoMap.clear();
                    }
                }   
            }
          System.out.println("CSV Reading Successful.");
        }
        catch(IOException | CsvValidationException e)
        {System.out.println("Failure to Initialize!");}  
    }
    public void selectedTable(String table){
        this.tableName = table;
    }

    public void selectNewColumns(String[] columns){
        selectedColumns = new HashSet<>();
        for(String checkColumns: columns){
            for(String header: this.headers){
                if(header.equals(checkColumns)){
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
        final CSVParser parser = new CSVParserBuilder().withSeparator(',').withIgnoreQuotations(false).build();
        final CSVReader reader = new CSVReaderBuilder(new FileReader(FileCSVPath)).withSkipLines(1).withCSVParser(parser).build();
        String[] nextRecord; 
            while ((nextRecord = reader.readNext()) != null) { 
                for (String cell : nextRecord) { 
                    System.out.print(cell + "\t"); 
                } 
            } 
        }
        catch(Exception e){
            System.out.println("Something went wrong!");
            e.printStackTrace();
        }
    }
    //Creates Inserts based on parameters,
    public void createInsertSQL(String FileDestination){
        if(selectedColumns==null||tableName==null){
            System.out.println("No Selected Columns/Table!");
            return;
        }
        Iterator selector = selectedColumns.iterator();
        String columns = "";
        while(selector.hasNext()){
            String a = (String)selector.next();
            if(!selector.hasNext())
            columns += a;
            else
            columns += a+",";
        }
        System.out.println("INSERT INTO "+tableName+"("+columns+") VALUES("+")");
        /*try{
            FileWriter fileToWrite = new FileWriter(new File(FileDestination+".sql"));
            for(int x = 0; x<rawCSV.size();x++){
            rawCSV.get(0);

            fileToWrite.write("INSERT INTO "+tableName+" ("+") VALUES("+")");
            }
            fileToWrite.close();
        }
        catch(IOException e){
            System.out.println("IOException Occurred!");
            e.printStackTrace();
        }*/
    }
    public void creationTemplate(){

    }
}
