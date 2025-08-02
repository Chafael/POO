package com.chafael11.figuras;

import com.chafael11.figuras.model.Circulo;
import com.chafael11.figuras.model.Cuadrado;
import com.chafael11.figuras.model.Figura;
import com.chafael11.figuras.model.Triangulo;
import com.chafael11.figuras.model.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.net.URL;
import java.util.ResourceBundle;

public class figurasController implements Initializable {

    @FXML
    private ComboBox<String> comboFiguras;

    @FXML
    private VBox panelInputs;

    @FXML
    private TextArea resultadoArea;

    @FXML
    private Button btnCalcular;

    @FXML
    private Button btnLimpiar;

    private TextField[] campos;
    private Label[] etiquetas;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Configurar ComboBox
        comboFiguras.getItems().addAll("Triángulo", "Cuadrado", "Círculo");
        comboFiguras.setValue("Triángulo");

        // Configurar evento del ComboBox
        comboFiguras.setOnAction(e -> actualizarCampos());

        // Configurar eventos de botones
        btnCalcular.setOnAction(e -> calcular());
        btnLimpiar.setOnAction(e -> limpiar());

        // Inicializar campos
        actualizarCampos();
    }

    private void actualizarCampos() {
        panelInputs.getChildren().clear();

        String figuraSeleccionada = comboFiguras.getValue();

        switch (figuraSeleccionada) {
            case "Triángulo":
                campos = new TextField[3];
                etiquetas = new Label[3];
                String[] nombresTriangulo = {"Lado 1:", "Lado 2:", "Lado 3:"};

                for (int i = 0; i < 3; i++) {
                    HBox campoBox = new HBox(10);
                    campoBox.setAlignment(javafx.geometry.Pos.CENTER);

                    etiquetas[i] = new Label(nombresTriangulo[i]);
                    etiquetas[i].setFont(Font.font("Arial", FontWeight.BOLD, 12));
                    etiquetas[i].setPrefWidth(80);

                    campos[i] = new TextField();
                    campos[i].setPromptText("Ingrese valor");
                    campos[i].setPrefWidth(200);

                    campoBox.getChildren().addAll(etiquetas[i], campos[i]);
                    panelInputs.getChildren().add(campoBox);
                }
                break;

            case "Cuadrado":
                campos = new TextField[1];
                etiquetas = new Label[1];

                HBox campoBoxCuadrado = new HBox(10);
                campoBoxCuadrado.setAlignment(javafx.geometry.Pos.CENTER);

                etiquetas[0] = new Label("Lado:");
                etiquetas[0].setFont(Font.font("Arial", FontWeight.BOLD, 12));
                etiquetas[0].setPrefWidth(80);

                campos[0] = new TextField();
                campos[0].setPromptText("Ingrese la longitud del lado");
                campos[0].setPrefWidth(200);

                campoBoxCuadrado.getChildren().addAll(etiquetas[0], campos[0]);
                panelInputs.getChildren().add(campoBoxCuadrado);
                break;

            case "Círculo":
                campos = new TextField[1];
                etiquetas = new Label[1];

                HBox campoBoxCirculo = new HBox(10);
                campoBoxCirculo.setAlignment(javafx.geometry.Pos.CENTER);

                etiquetas[0] = new Label("Radio:");
                etiquetas[0].setFont(Font.font("Arial", FontWeight.BOLD, 12));
                etiquetas[0].setPrefWidth(80);

                campos[0] = new TextField();
                campos[0].setPromptText("Ingrese el radio");
                campos[0].setPrefWidth(200);

                campoBoxCirculo.getChildren().addAll(etiquetas[0], campos[0]);
                panelInputs.getChildren().add(campoBoxCirculo);
                break;
        }
    }

    private void calcular() {
        try {
            String figuraSeleccionada = comboFiguras.getValue();
            Figura figura = null;

            switch (figuraSeleccionada) {
                case "Triángulo":
                    double lado1 = Double.parseDouble(campos[0].getText());
                    double lado2 = Double.parseDouble(campos[1].getText());
                    double lado3 = Double.parseDouble(campos[2].getText());

                    // Validar que los lados formen un triángulo válido
                    if (lado1 + lado2 > lado3 && lado1 + lado3 > lado2 && lado2 + lado3 > lado1) {
                        figura = new Triangulo(lado1, lado2, lado3);
                    } else {
                        resultadoArea.setText("Error: Los lados ingresados no forman un triángulo válido.\n" +
                                "La suma de dos lados debe ser mayor que el tercer lado.");
                        return;
                    }
                    break;

                case "Cuadrado":
                    double lado = Double.parseDouble(campos[0].getText());
                    if (lado > 0) {
                        figura = new Cuadrado(lado);
                    } else {
                        resultadoArea.setText("Error: El lado debe ser un número positivo.");
                        return;
                    }
                    break;

                case "Círculo":
                    double radio = Double.parseDouble(campos[0].getText());
                    if (radio > 0) {
                        figura = new Circulo(radio);
                    } else {
                        resultadoArea.setText("Error: El radio debe ser un número positivo.");
                        return;
                    }
                    break;
            }

            if (figura != null) {
                StringBuilder resultado = new StringBuilder();
                resultado.append("=== RESULTADOS ===\n");
                resultado.append("Figura: ").append(figura.getNombre()).append("\n\n");

                // Mostrar datos de entrada
                resultado.append("Datos de entrada:\n");
                if (figura instanceof Triangulo) {
                    Triangulo t = (Triangulo) figura;
                    resultado.append("• Lado 1: ").append(t.getLado1()).append("\n");
                    resultado.append("• Lado 2: ").append(t.getLado2()).append("\n");
                    resultado.append("• Lado 3: ").append(t.getLado3()).append("\n");
                } else if (figura instanceof Cuadrado) {
                    Cuadrado c = (Cuadrado) figura;
                    resultado.append("• Lado: ").append(c.getLado()).append("\n");
                } else if (figura instanceof Circulo) {
                    Circulo c = (Circulo) figura;
                    resultado.append("• Radio: ").append(c.getRadio()).append("\n");
                }

                resultado.append("\nResultados:\n");
                resultado.append("• Área: ").append(String.format("%.2f", figura.area())).append("\n");
                resultado.append("• Perímetro: ").append(String.format("%.2f", figura.perimetro())).append("\n");

                resultadoArea.setText(resultado.toString());
            }

        } catch (NumberFormatException e) {
            resultadoArea.setText("Error: Por favor ingrese valores numéricos válidos.");
        } catch (Exception e) {
            resultadoArea.setText("Error: " + e.getMessage());
        }
    }

    private void limpiar() {
        for (TextField campo : campos) {
            campo.clear();
        }
        resultadoArea.clear();
    }
}