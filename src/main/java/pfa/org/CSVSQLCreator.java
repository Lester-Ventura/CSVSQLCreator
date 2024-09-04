package pfa.org;
public class CSVSQLCreator {
    public static void main( String[] args ) {
        System.out.println("This is working!");
        // Replace the placeholder with your MongoDB deployment's connection string
        CSVSQLInterface CSVReadFile = new CSVSQLInterface("C:/Users/czarv/Downloads/SAMPLE METADATA SHEET - REFERENCE.csv");
        CSVReadFile.readCSV();
        }
    }
