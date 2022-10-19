module bomberman {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.desktop;
    requires transitive javafx.graphics;
    requires transitive org.apache.logging.log4j;

    opens com.tedomi2705.bomberman.controller to javafx.fxml;

    exports com.tedomi2705.bomberman;
    exports com.tedomi2705.bomberman.entities.abstracts;
    exports com.tedomi2705.bomberman.controller to javafx.fxml;
}
