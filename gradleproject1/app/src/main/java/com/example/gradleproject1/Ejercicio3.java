package com.example.gradleproject1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Ejercicio3 extends Application {

    @Override
    public void start(Stage primaryStage) {
        // 1. Controles básicos requeridos por el enunciado
        Label lblCantidad = new Label("Cantidad en Euros (€):");
        TextField txtCantidad = new TextField();

        Label lblMoneda = new Label("Selecciona moneda:");
        ComboBox<String> cmbMoneda = new ComboBox<>();
        cmbMoneda.getItems().addAll("Dólares (USD)", "Libras (GBP)", "Yenes (JPY)");
        cmbMoneda.setValue("Dólares (USD)"); // Valor seleccionado por defecto

        Button btnConvertir = new Button("Convertir");

        // Label donde se mostrará el resultado de la conversión
        Label lblConversion = new Label("Conversión: -");

        // 2. Lógica al pulsar el botón
        btnConvertir.setOnAction(e -> {
            try {
                double euros = Double.parseDouble(txtCantidad.getText().replace(",", "."));
                String monedaSeleccionada = cmbMoneda.getValue();
                double resultado = 0;
                String simbolo = "";

                // Tasas de cambio aproximadas
                switch (monedaSeleccionada) {
                    case "Dólares (USD)":
                        resultado = euros * 1.08;
                        simbolo = "$";
                        break;
                    case "Libras (GBP)":
                        resultado = euros * 0.85;
                        simbolo = "£";
                        break;
                    case "Yenes (JPY)":
                        resultado = euros * 163.50;
                        simbolo = "¥";
                        break;
                }

                // Mostramos el resultado formateado a 2 decimales en el Label
                lblConversion.setText(String.format("Conversión: %.2f %s", resultado, simbolo));

            } catch (NumberFormatException ex) {
                lblConversion.setText("Error: Introduce una cantidad numérica válida.");
            }
        });

        // 3. Layout VBox
        VBox root = new VBox(10, lblCantidad, txtCantidad, lblMoneda, cmbMoneda, btnConvertir, lblConversion);
        root.setPadding(new Insets(15));

        // 4. Escena y ventana
        Scene scene = new Scene(root, 300, 260);
        primaryStage.setTitle("Ejercicio 3 - Conversor");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}