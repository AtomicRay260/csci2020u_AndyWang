package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FileReader{
    // Variables
    private File file;
    private ArrayList<String[]> data = new ArrayList<String[]>();

    // Constructors
    public FileReader(String fileName){
        this(new File(fileName));
    }
    public FileReader(File file){
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

    // Getters
    public File getFile() {return file;}
    public ArrayList<String[]> getData() {return data;}
    public Boolean hasData() {return data.size()>0;}
}