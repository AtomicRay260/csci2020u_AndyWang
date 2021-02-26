module lab04  {
    requires javafx.fxml;
    requires javafx.controls;

    opens registration to javafx.fxml;
    exports registration;
}
//--module-path
//${PATH_TO_FX}
//--add-modules=javafx.controls,javafx.fxml