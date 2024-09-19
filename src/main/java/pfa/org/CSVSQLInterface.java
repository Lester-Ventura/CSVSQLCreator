package pfa.org;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.ListIterator;

class CSVSQLInterface{
    private ArrayList<String[]> selectedColumns;
    private String tableName;
    private final CSVManager csv;
    CSVSQLInterface(String CSVFilePath){
        csv = new CSVManager(CSVFilePath);
    }
    public void selectedTable(String table){
        this.tableName = table;
    }

    public void selectNewColumns(String[] columns, String[] nameColumns){
        selectedColumns = new ArrayList<>();
        if(columns.length != nameColumns.length){
            System.out.println("Column Selection Failed: Unequal Lengths!");
            return;
        }
        for(int i=0; i<columns.length;i++){
               if(csv.verifyColumns(columns[i])){
                    selectedColumns.add(new String[] {columns[i],nameColumns[i]});
                    System.out.println("Selection Successful: "+ columns[i] +" as "+ nameColumns[i] );
                }
        }
        System.out.println("Column Selection Ended.");
    }
    //Creates Inserts based on parameters,
    public void createAllInsertSQL(String FileDestination){
        if(checkTableOrColumnEmpty()){
            return;
        }
        ListIterator<String[]> selector = selectedColumns.listIterator();
        LinkedList<LinkedList<String>> csvInsertList = new LinkedList<>();
        File fileDest = new File(FileDestination);
        try{
        FileWriter fileToWrite = new FileWriter(fileDest,true);
        while(selector.hasNext()){
            csvInsertList.add(csv.CSVFindAllInColumn(selector.next()[0]));
        }
        for(int i=0;i<csvInsertList.get(0).size();i++){
            String columns = "";
            String values = "";
            for(int b = 0;b<csvInsertList.size();b++){
                if(csvInsertList.get(b).get(i).equals("")){
                    continue;
                }
                if(columns.equals("")){
                    columns = selectedColumns.get(b)[1];
                }
                else{
                    columns += ","+selectedColumns.get(b)[1];
                }
                if(values.equals("")){
                    values += "\""+csvInsertList.get(b).get(i)+"\"";
                }
                else{
                    values += ", \""+csvInsertList.get(b).get(i)+"\"";
                }
            }
            if(columns.equals(""))
            continue;
        fileToWrite.write("INSERT INTO "+tableName+"("+columns+") VALUES("+values+");\n");
            }
        fileToWrite.close();
        }    
        catch(Exception e){
            System.out.println("File failed to write!");
            }
        
    }
    public void createUniqueInsertSQL(String FileDestination){
        if(checkTableOrColumnEmpty()){
            return;
        }
        if(selectedColumns.size()>1){
            System.out.println("Size of Colmuns too large for unique select!");
        }
        ListIterator<String[]> selector = selectedColumns.listIterator();
        LinkedList<LinkedHashSet<String>> csvInsertList = new LinkedList<>();
        while(selector.hasNext()){
            csvInsertList.add(csv.CSVFindUniqueInColumn(selector.next()[0]));
        }
        
        String columns = selectedColumns.get(0)[1];
        int i = 0;
        File fileDest = new File(FileDestination);
        try{
        FileWriter fileToWrite = new FileWriter(fileDest,true);
        for(Iterator<String> hashIterator = csvInsertList.get(i).iterator();hashIterator.hasNext();){
                String values = "\""+hashIterator.next()+"\"";
                fileToWrite.write("INSERT INTO "+tableName+"("+columns+") VALUES("+values+");\n");
            }
        fileToWrite.close();
        }
        catch(Exception e){
            System.out.println("File failed to write!");
        }
        }
        private boolean checkTableOrColumnEmpty(){
            if(selectedColumns == null)
                System.out.println("Error: No Selected Columns!");
            if(tableName == null)
                System.out.println("Error: Empty Table Name!");
            return !(selectedColumns == null || tableName == null);
        }
      
    }

