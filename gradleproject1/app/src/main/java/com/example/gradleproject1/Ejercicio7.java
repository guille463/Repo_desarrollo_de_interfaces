package com.example.gradleproject1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Ejercicio7 extends Application {

    @Override
    public void start(Stage primaryStage) {
        // 1. Controles básicos requeridos por el enunciado
        Label lblNota = new Label("Introduce una nota (0 - 10):");
        TextField txtNota = new TextField();

        Button btnAnadir = new Button("Añadir");
        Button btnCalcular = new Button("Calcular");

        ListView<Double> listViewNotas = new ListView<>();
        Label lblResultados = new Label("Mayor: - | Menor: - | Media: -");

        // 2. Evento botón "Añadir"
        btnAnadir.setOnAction(e -> {
            try {
                double nota = Double.parseDouble(txtNota.getText().replace(",", ".").trim());

                if (nota >= 0 && nota <= 10) {
                    listViewNotas.getItems().add(nota);
                    txtNota.clear();
                    txtNota.requestFocus(); // Devuelve el cursor al campo de texto
                } else {
                    lblResultados.setText("Error: La nota debe estar entre 0 y 10.");
                }
            } catch (NumberFormatException ex) {
                lblResultados.setText("Error: Introduce un número válido.");
            }
        });

        // 3. Evento botón "Calcular"
        btnCalcular.setOnAction(e -> {
            if (listViewNotas.getItems().isEmpty()) {
                lblResultados.setText("No hay notas en la lista para calcular.");
                return;
            }

            double mayor = Double.MIN_VALUE;
            double menor = Double.MAX_VALUE;
            double suma = 0;

            // Recorremos directamente los elementos almacenados en el ListView
            for (double nota : listViewNotas.getItems()) {
                if (nota > mayor) {
                    mayor = nota;
                }
                if (nota < menor) {
                    menor = nota;
                }
                suma += nota;
            }

            double media = suma / listViewNotas.getItems().size();
            lblResultados.setText(String.format("Mayor: %.2f | Menor: %.2f | Media: %.2f", mayor, menor, media));
        });

        // 4. Layout VBox
        VBox root = new VBox(10, lblNota, txtNota, btnAnadir, listViewNotas, btnCalcular, lblResultados);
        root.setPadding(new Insets(15));

        // 5. Escena y ventana
        Scene scene = new Scene(root, 340, 380);
        primaryStage.setTitle("Ejercicio 7 - Notas ListView");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}