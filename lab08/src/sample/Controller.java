package sample;

import com.sun.glass.ui.CommonDialogs;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.cert.Extension;
import java.util.ArrayList;
import java.util.HashMap;

public class Controller {
    @FXML TableView tableView;

    @FXML HBox inputBox;
    @FXML GridPane mainGridPane;

    @FXML TextField studentIdField;
    @FXML TextField assignmentField;
    @FXML TextField midtermField;
    @FXML TextField finalExamField;

    private CSVFileReader currentFileReader = null;
    private File targetFile = null;
    public Student[] getAllMarks(){
        Student[] marks = new Student[10];
        marks[0] = new Student("100100100", 75.0f, 68.0f,54.25f);
        marks[1] = new Student("100100101",70.0f,69.25f, 51.5f);
        marks[2] = new Student("100100102",100.0f, 97.0f, 92.5f);
        marks[3] = new Student("100100103", 90.0f, 88.5f, 68.75f);
        marks[4] = new Student("100100104", 72.25f, 74.75f,58.25f);
        marks[5] = new Student("100100105", 85.0f, 56.0f,62.5f);
        marks[6] = new Student("100100106",70.0f,66.5f, 61.75f);
        marks[7] = new Student("100100107",55.0f, 47.0f, 50.5f);
        marks[8] = new Student("100100108", 40.0f, 32.5f, 27.75f);
        marks[9] = new Student("100100109", 82.5f, 77.0f,74.25f);
        return marks;
    }

    public void addStudent(Student student){
        ObservableList<Student> data = tableView.getItems();
        data.add(student);
    }

    /*public void initialize(){
        Student[] marks = getAllMarks();
        for (int i=0; i<marks.length; i++){
            addStudent(marks[i]);
        }
    }*/

    public void addStudentFromUi(){
        float assignment, midterm, finalExam;
        try{
            Integer.parseInt(studentIdField.getText());
        }catch (Exception ClassCastException){
            System.out.println(studentIdField.getCharacters());
            studentIdField.setText("Invalid Student Id");
            return;
        }
        try{
            assignment = Float.parseFloat(assignmentField.getText());
        }catch (Exception ClassCastException){
            assignmentField.setText("Invalid Mark");
            return;
        }
        try{
            midterm = Float.parseFloat(midtermField.getText());
        }catch (Exception ClassCastException){
            midtermField.setText("Invalid Mark");
            return;
        }
        try{
            finalExam = Float.parseFloat(finalExamField.getText());
        }catch (Exception ClassCastException){
            finalExamField.setText("Invalid Mark");
            return;
        }

        addStudent(new Student(studentIdField.getText(), assignment, midterm, finalExam));
        studentIdField.setText("");
        assignmentField.setText("");
        midtermField.setText("");
        finalExamField.setText("");
    }

    public ArrayList<String[]> getOpenData(){
        ObservableList<Student> data = tableView.getItems();
        ArrayList<String[]> returnData = new ArrayList<>();
        for (Student student : data){
            String[] dataList = {
                    student.getStudentId(),
                    String.valueOf(student.getAssignment()),
                    String.valueOf(student.getMidterm()),
                    String.valueOf(student.getFinalExam())
            };
            returnData.add(dataList);
        }
        return returnData;
    }
    public Boolean isDifferenceInFile(){
        CSVFileReader reader = new CSVFileReader(targetFile);
        return reader.equals(getOpenData());
    }
    public void generateNewTable(){
        Stage mainStage = (Stage) tableView.getScene().getWindow();
        if (tableView.getItems().size() > 0){
            tableView.getItems().removeAll();
        }
        currentFileReader = null;
        targetFile = null;
        mainStage.setTitle("NewFile.csv");
    }
    public void load(){
        Stage mainStage = (Stage) tableView.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
        File selectedFile = fileChooser.showOpenDialog(mainStage);
        if (!selectedFile.exists()){return;}

        CSVFileReader reader = new CSVFileReader(selectedFile);
        ArrayList<String[]> data = reader.getData();
        for (String[] row : data){
            addStudent(new Student(row));
        }
        mainStage.setTitle(reader.getFile().getName());
        currentFileReader = reader;
        targetFile = selectedFile;
    }

    private void saveDataIntoFile(String fileDirectory){
        try {
            FileWriter fileWriter = new FileWriter(fileDirectory);
            for (String[] dataRow : getOpenData()){
                StringBuilder row = new StringBuilder(dataRow[0]);
                for (int i=1; i<dataRow.length;i++){
                    row.append(",").append(dataRow[i]);
                }
                row.append("\n");
                fileWriter.append(row);
            }
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void save(){
        if (targetFile==null){
            saveAs();
        }else{
            saveDataIntoFile(targetFile.getAbsolutePath());
            BorderPane borderPane = new BorderPane();
            borderPane.setCenter(new Label("Successfully saved file as "+targetFile.getName()));
            Stage secondaryStage = new Stage();
            secondaryStage.setScene(new Scene(borderPane, 200, 50));
            secondaryStage.show();
        }
    }
    public void saveAs(){
        Stage primaryStage = (Stage) tableView.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
        File fileDirectory = fileChooser.showSaveDialog(primaryStage);
        if (!fileDirectory.exists()){return;}

        saveDataIntoFile(fileDirectory.getAbsolutePath());
        primaryStage.setTitle(fileDirectory.getName());

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(new Label("Successfully saved file as "+primaryStage.getTitle()));
        Stage secondaryStage = new Stage();
        secondaryStage.setScene(new Scene(borderPane, 200, 50));
        secondaryStage.show();

        targetFile = fileDirectory;
    }
    public void attemptExit(){
        Stage mainStage = (Stage) tableView.getScene().getWindow();
        mainStage.close();
    }
}