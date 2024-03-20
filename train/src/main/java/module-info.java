module com.ckmidi {
    // jfx
    requires transitive javafx.controls;
    requires transitive javafx.graphics;
    requires javafx.fxml;

    // pi4j
    requires transitive com.pi4j;

    // other
    opens com.traindiorama to javafx.fxml;
    exports com.traindiorama;
}
