package registration;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileReader;
import java.util.Formatter;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Lab04 (Registration)");

        // Setting up grid pane \\
        GridPane uiGrid = new GridPane();
        uiGrid.setAlignment(Pos.CENTER);
        uiGrid.setHgap(10);
        uiGrid.setVgap(10);
        uiGrid.setPadding(new Insets(25, 25, 25, 25));

        // create Login ui controls \\
        //labels
        Text welcome = new Text("Welcome");
        welcome.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        Label username = new Label("Username: ");
        Label password = new Label("Password: ");
        Label fullName = new Label("Full name: ");
        Label email = new Label("Email: ");
        Label phoneNumber = new Label("Phone number: ");
        Label DateOfBirth = new Label("Date of birth: ");
        Text textOnClick = new Text();

        //inputs
        TextField usernameInput = new TextField();
        TextField nameInput = new TextField();
        TextField emailInput = new TextField();
        TextField numberInput = new TextField();

        PasswordField passwordField = new PasswordField();
        DatePicker datePicker = new DatePicker();
        Button inputConfirm = new Button("Register");
        inputConfirm.setAlignment(Pos.CENTER);

        // connecting events \\
        inputConfirm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                textOnClick.setFill(Color.rgb(0, 200, 0));
                textOnClick.setText("Registered");

                System.out.println("Username: " + usernameInput.getCharacters());
                System.out.println("Password: " + passwordField.getCharacters());
                System.out.println("Full name: " + nameInput.getCharacters());
                System.out.println("Email: " + emailInput.getCharacters());
                System.out.println("Phone number: " + numberInput.getCharacters());
                System.out.println("Date: " + datePicker.getValue());
            }
        });

        // adding ui controls
        uiGrid.add(welcome, 0, 0, 2, 1);
        uiGrid.add(username, 0, 1);
        uiGrid.add(usernameInput, 1, 1);
        uiGrid.add(password, 0, 2);
        uiGrid.add(passwordField, 1, 2);
        uiGrid.add(fullName, 0, 3);
        uiGrid.add(nameInput, 1, 3);
        uiGrid.add(email, 0, 4);
        uiGrid.add(emailInput, 1, 4);
        uiGrid.add(phoneNumber, 0, 5);
        uiGrid.add(numberInput, 1, 5);
        uiGrid.add(DateOfBirth, 0, 6);
        uiGrid.add(datePicker, 1, 6);
        uiGrid.add(inputConfirm, 0, 7);
        uiGrid.add(textOnClick, 0, 8);

        Scene scene = new Scene(uiGrid, 500, 350);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
