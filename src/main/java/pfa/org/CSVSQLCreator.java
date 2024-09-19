package pfa.org;
import java.io.File;
public class CSVSQLCreator {
    public static void main( String[] args ) {
 CSVSQLInterface CSVReadFile = new CSVSQLInterface("C:/Users/czarv/Downloads/SAMPLE METADATA SHEET - REFERENCE.csv");
        CSVReadFile.selectedTable("Genre");
        File file = new File("C:/Users/czarv/Downloads/FirstVersionSQL.sql");
        file.delete();
        System.out.println("Path:"+file.getAbsolutePath());
        CSVReadFile.selectNewColumns(new String[]{"GENRE"},new String[]{"Name"});
        CSVReadFile.createUniqueInsertSQL(file.getAbsolutePath());
        CSVReadFile.selectedTable("Language");
        CSVReadFile.selectNewColumns(new String[]{"LANGUAGE/DIALECT"},new String[]{"Name"});
        CSVReadFile.createUniqueInsertSQL(file.getAbsolutePath());
        CSVReadFile.selectedTable("Country");
        CSVReadFile.selectNewColumns(new String[]{"COUNTRY OF ORIGIN"},new String[]{"Name"});
        CSVReadFile.createUniqueInsertSQL(file.getAbsolutePath());
        CSVReadFile.selectedTable("Tag");
        CSVReadFile.selectNewColumns(new String[]{"GAUGE"},new String[]{"Name"});
        CSVReadFile.createUniqueInsertSQL(file.getAbsolutePath());
        CSVReadFile.selectedTable("Theme");
        CSVReadFile.selectNewColumns(new String[]{"TYPE "},new String[]{"Name"});
        CSVReadFile.createUniqueInsertSQL(file.getAbsolutePath());
        }
    }
