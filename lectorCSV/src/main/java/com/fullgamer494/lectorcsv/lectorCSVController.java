package com.fullgamer494.lectorcsv;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class lectorCSVController implements Initializable {

    @FXML
    private VBox dataDisplayContainer;

    @FXML
    private Button openFileButton;

    @FXML
    private Button clearButton;

    @FXML
    private Button firstButton;

    @FXML
    private Button prevButton;

    @FXML
    private Button nextButton;

    @FXML
    private Button lastButton;

    @FXML
    private Label rowCounterLabel;

    private List<String[]> csvData;
    private String[] headers;
    private int currentRowIndex = 0;
    private List<Label> dataLabels;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        csvData = new ArrayList<>();
        dataLabels = new ArrayList<>();
        setupInitialState();
        updateButtonStates();
        updateRowCounter();
    }

    private void setupInitialState() {
        dataDisplayContainer.getChildren().clear();
    }

    @FXML
    private void handleOpenFile() {
        Stage stage = (Stage) openFileButton.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar archivo CSV");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Archivos CSV", "*.csv")
        );

        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            loadCSVFile(selectedFile);
        }
    }

    private void loadCSVFile(File file) {
        try {
            csvData.clear();

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {
                String[] rowData = parseCSVLine(line);

                if (isFirstLine) {
                    headers = rowData.clone();
                    isFirstLine = false;
                }

                csvData.add(rowData);
            }
            reader.close();

            if (!csvData.isEmpty()) {
                currentRowIndex = 0;
                displayCurrentRow();
            }

            updateButtonStates();
            updateRowCounter();

        } catch (IOException e) {
            showErrorDialog("Error al cargar archivo", e.getMessage());
        }
    }

    private String[] parseCSVLine(String line) {
        List<String> fields = new ArrayList<>();
        StringBuilder currentField = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                fields.add(currentField.toString().trim());
                currentField = new StringBuilder();
            } else {
                currentField.append(c);
            }
        }

        fields.add(currentField.toString().trim());
        return fields.toArray(new String[0]);
    }

    private void displayCurrentRow() {
        dataDisplayContainer.getChildren().clear();
        dataLabels.clear();

        if (!csvData.isEmpty() && currentRowIndex >= 0 && currentRowIndex < csvData.size()) {
            String[] currentRow = csvData.get(currentRowIndex);

            // Crear labels separados para nombres y valores
            for (int i = 0; i < headers.length && i < currentRow.length; i++) {
                Label nameLabel = new Label(headers[i]);
                nameLabel.getStyleClass().add("field-name-label");

                Label valueLabel = new Label(currentRow[i]);
                valueLabel.getStyleClass().add("field-value-label");

                dataLabels.add(nameLabel);
                dataLabels.add(valueLabel);

                dataDisplayContainer.getChildren().addAll(nameLabel, valueLabel);
            }
        }
    }

    @FXML
    private void handleClear() {
        csvData.clear();
        dataDisplayContainer.getChildren().clear();
        dataLabels.clear();
        currentRowIndex = 0;
        headers = null;
        updateButtonStates();
        updateRowCounter();
    }

    @FXML
    private void handleFirst() {
        if (!csvData.isEmpty()) {
            currentRowIndex = 0;
            displayCurrentRow();
            updateButtonStates();
            updateRowCounter();
        }
    }

    @FXML
    private void handlePrevious() {
        if (currentRowIndex > 0) {
            currentRowIndex--;
            displayCurrentRow();
            updateButtonStates();
            updateRowCounter();
        }
    }

    @FXML
    private void handleNext() {
        if (currentRowIndex < csvData.size() - 1) {
            currentRowIndex++;
            displayCurrentRow();
            updateButtonStates();
            updateRowCounter();
        }
    }

    @FXML
    private void handleLast() {
        if (!csvData.isEmpty()) {
            currentRowIndex = csvData.size() - 1;
            displayCurrentRow();
            updateButtonStates();
            updateRowCounter();
        }
    }

    private void updateButtonStates() {
        boolean hasData = !csvData.isEmpty();
        boolean isFirst = currentRowIndex <= 0;
        boolean isLast = currentRowIndex >= csvData.size() - 1;

        firstButton.setDisable(!hasData || isFirst);
        prevButton.setDisable(!hasData || isFirst);
        nextButton.setDisable(!hasData || isLast);
        lastButton.setDisable(!hasData || isLast);
    }

    private void updateRowCounter() {
        if (csvData.isEmpty()) {
            rowCounterLabel.setText("Fila: 0 de 0");
        } else {
            rowCounterLabel.setText("Fila: " + (currentRowIndex + 1) + " de " + csvData.size());
        }
    }

    private void showErrorDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}