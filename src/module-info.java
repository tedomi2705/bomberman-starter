module bomberman {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.desktop;
    requires transitive javafx.graphics;

    exports com.tedomi2705.bomberman;
    exports com.tedomi2705.bomberman.entities.abstracts;
}