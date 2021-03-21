package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CSVFileReader {
    // Variables
    private File file;
    private ArrayList<String[]> data = new ArrayList<String[]>();

    // Constructors
    public CSVFileReader(String fileName){
        this(new File(fileName));
    }
    public CSVFileReader(File file){
        this.file = file;
        try {
            createArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Methods for generating array from file
    private void createArray() throws FileNotFoundException {
        if (file != null){
            Scanner scanner = new Scanner(this.file);
            //scanner.useDelimiter(",");
            while (scanner.hasNext()) {
                String currentRow = scanner.nextLine();
                String[] row = currentRow.split(",",-1);
                if (row.length>0){
                    data.add(row);
                }
            }
            scanner.close();
        }else{
            System.out.println("Null array input");
        }
    }

    // Method for comparing file data
    private boolean compareData(ArrayList<String[]> objData){
        if (objData.size() != data.size()){return false;}
        for (int i=0; i<data.size();i++){
            String[] obj1List = data.get(i);
            String[] obj2List = objData.get(i);
            for (int j=0; i<obj1List.length; i++){
                if (obj1List[j].equals(obj2List[j])){
                    return false;
                }
            }
        }
        return true;
    }
    @Override
    public boolean equals(Object object){
        if (object == this){
            return true;
        }else if(!(object instanceof CSVFileReader)){
            return false;
        }

        CSVFileReader castedObject = (CSVFileReader) object;
        ArrayList<String[]> objectData = castedObject.getData();
        return compareData(objectData);
    }
    public boolean equals(ArrayList<String[]> objData){
        return compareData(objData);
    }

    // Getters
    public File getFile() {return file;}
    public ArrayList<String[]> getData() {return data;}
    public Boolean hasData() {return data.size()>0;}
}