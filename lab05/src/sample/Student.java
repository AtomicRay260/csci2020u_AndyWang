package sample;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Student {
    private final SimpleStringProperty studentId = new SimpleStringProperty();
    private final SimpleFloatProperty assignment = new SimpleFloatProperty();
    private final SimpleFloatProperty midterm = new SimpleFloatProperty();
    private final SimpleFloatProperty finalExam = new SimpleFloatProperty();
    private final SimpleFloatProperty finalGrade = new SimpleFloatProperty();
    private final SimpleStringProperty gradeLetter = new SimpleStringProperty();

    public Student(){
        this("N/A", 0.0f, 0.0f, 0.0f);
    }

    public Student(String studentId, float assignment, float midterm, float finalExam) {
        setStudentId(studentId);
        setAssignment(assignment);
        setMidterm(midterm);
        setFinalExam(finalExam);

        float finalGrade = (assignment*0.2f) + (midterm*0.2f) + (finalExam*0.5f);
        setFinalGrade(finalGrade);
        if (finalGrade < 50.0f){
            setGradeLetter("F");
        }else if(finalGrade < 60.0f){
            setGradeLetter("D");
        }else if(finalGrade < 70.0f){
            setGradeLetter("C");
        }else if(finalGrade < 60.0f){
            setGradeLetter("B");
        }else{
            setGradeLetter("A");
        }
    }

    //getters
    public String getStudentId() {return studentId.get();}
    public float getAssignment() {return assignment.get();}
    public float getMidterm() {return midterm.get();}
    public float getFinalExam() {return finalExam.get();}
    public float getFinalGrade() {return finalGrade.get();}
    public String getGradeLetter() {return gradeLetter.get();}

    //setters
    public void setStudentId(String value) {studentId.set(value);}
    public void setAssignment(float value) {assignment.set(value);}
    public void setMidterm(float value) {midterm.set(value);}
    public void setFinalExam(float value) {finalExam.set(value);}
    public void setFinalGrade(float value) {finalGrade.set(value);}
    public void setGradeLetter(String value) {gradeLetter.set(value);}

    //vale returners
    public StringProperty studentIdProperty() {return studentId;}
    public FloatProperty assignmentProperty() {return assignment;}
    public FloatProperty midtermProperty() {return midterm;}
    public FloatProperty finalExamProperty() {return finalExam;}
    public FloatProperty finalGradeProperty() {return finalGrade;}
    public StringProperty gradeLetterProperty() {return gradeLetter;}
}
