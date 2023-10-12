module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
    exports com.example.demo.Render;
    opens com.example.demo.Render to javafx.fxml;
    exports com.example.demo.Trabajador;
    opens com.example.demo.Trabajador to javafx.fxml;
    exports com.example.demo.Calculadora;
    opens com.example.demo.Calculadora to javafx.fxml;
}