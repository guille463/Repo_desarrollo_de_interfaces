package com.example.gradleproject1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Ejercicio4 extends Application {

    @Override
    public void start(Stage primaryStage) {
        // 1. Controles básicos
        Label lblCantidad = new Label("Cantidad en Euros (€):");
        TextField txtCantidad = new TextField();

        Label lblMoneda = new Label("Selecciona moneda:");

        // Creamos los RadioButtons
        RadioButton rbDolares = new RadioButton("Dólares (USD)");
        RadioButton rbLibras = new RadioButton("Libras (GBP)");
        RadioButton rbYenes = new RadioButton("Yenes (JPY)");

        // Agrupamos los RadioButtons para que sean excluyentes entre sí
        ToggleGroup grupoMonedas = new ToggleGroup();
        rbDolares.setToggleGroup(grupoMonedas);
        rbLibras.setToggleGroup(grupoMonedas);
        rbYenes.setToggleGroup(grupoMonedas);

        // Dejamos uno marcado por defecto
        rbDolares.setSelected(true);

        Button btnConvertir = new Button("Convertir");
        Label lblConversion = new Label("Conversión: -");

        // 2. Lógica al pulsar el botón
        btnConvertir.setOnAction(e -> {
            try {
                double euros = Double.parseDouble(txtCantidad.getText().replace(",", "."));
                double resultado = 0;
                String simbolo = "";

                // Comprobamos cuál está seleccionado con .isSelected()
                if (rbDolares.isSelected()) {
                    resultado = euros * 1.08;
                    simbolo = "$";
                } else if (rbLibras.isSelected()) {
                    resultado = euros * 0.85;
                    simbolo = "£";
                } else if (rbYenes.isSelected()) {
                    resultado = euros * 163.50;
                    simbolo = "¥";
                }

                lblConversion.setText(String.format("Conversión: %.2f %s", resultado, simbolo));

            } catch (NumberFormatException ex) {
                lblConversion.setText("Error: Introduce una cantidad numérica válida.");
            }
        });

        // 3. Layout VBox (añadimos los RadioButton directamente, el ToggleGroup no es visual)
        VBox root = new VBox(10, lblCantidad, txtCantidad, lblMoneda, rbDolares, rbLibras, rbYenes, btnConvertir, lblConversion);
        root.setPadding(new Insets(15));

        // 4. Escena y ventana
        Scene scene = new Scene(root, 300, 310);
        primaryStage.setTitle("Ejercicio 4 - RadioButtons");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}