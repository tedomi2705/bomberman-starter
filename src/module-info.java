module bomberman {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.desktop;
    requires transitive javafx.graphics;
    requires transitive org.apache.logging.log4j;

    exports com.tedomi2705.bomberman;
    exports com.tedomi2705.bomberman.entities.abstracts;
}