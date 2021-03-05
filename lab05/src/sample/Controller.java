package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class Controller {
    @FXML
    TableView tableView;
    @FXML
    Button button;

    @FXML
    HBox inputBox;
    @FXML
    GridPane mainGridPane;

    @FXML
    TextField studentIdField;
    @FXML
    TextField assignmentField;
    @FXML
    TextField midtermField;
    @FXML
    TextField finalExamField;
    @FXML
    Label errorDisplay;

    public Student[] getAllMarks(){
        //ObservableList<Student> marks = FXCollections.observableArrayList();
        /*marks.add(new Student("100100100", 75.0f, 68.0f,54.25f));
        marks.add(new Student("100100101",70.0f,69.25f, 51.5f));
        marks.add(new Student("100100102",100.0f, 97.0f, 92.5f));
        marks.add(new Student("100100103", 90.0f, 88.5f, 68.75f));
        marks.add(new Student("100100104", 72.25f, 74.75f,58.25f));
        marks.add(new Student("100100105", 85.0f, 56.0f,62.5f));
        marks.add(new Student("100100106",70.0f,66.5f, 61.75f));
        marks.add(new Student("100100107",55.0f, 47.0f, 50.5f));
        marks.add(new Student("100100108", 40.0f, 32.5f, 27.75f));
        marks.add(new Student("100100109", 82.5f, 77.0f,74.25f));*/
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

    public void generateItems(){
        Student[] marks = getAllMarks();
        for (int i=0; i<marks.length; i++){
            addStudent(marks[i]);
        }
        mainGridPane.getChildren().remove(button);
    }

    public void attemptAddStudent(){
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
}
