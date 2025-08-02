module com.fullgamer494.figuras {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens com.fullgamer494.figuras to javafx.fxml;
    exports com.fullgamer494.figuras;
    exports com.fullgamer494.figuras.model;
    opens com.fullgamer494.figuras.model to javafx.fxml;
}