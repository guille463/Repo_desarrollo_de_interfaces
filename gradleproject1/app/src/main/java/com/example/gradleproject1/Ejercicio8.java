package com.example.gradleproject1;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Ejercicio8 extends Application {

    @Override
    public void start(Stage primaryStage) {
      
        ObservableList<String> listaAnios = FXCollections.observableArrayList();
        ListView<String> listView = new ListView<>(listaAnios);

        TextField txtAnio = new TextField();
        txtAnio.setPromptText("Introduce un año");
        Button btnComprobar = new Button("Comprobar AÑO");

        btnComprobar.setOnAction(e -> {
            try {
                int anio = Integer.parseInt(txtAnio.getText().trim());

                if (anio <= 0) {
                    mostrarError("Introduce un año positivo.");
                    return;
                }

                boolean esBisiesto = (anio % 4 == 0 && anio % 100 != 0) || (anio % 400 == 0);

                String resultado = anio + (esBisiesto ? " BISIESTO" : " NO BISIESTO");
                listaAnios.add(resultado);
                
                txtAnio.clear();
                txtAnio.requestFocus();

            } catch (NumberFormatException ex) {
                mostrarError("Dato incorrecto. Por favor, introduce un año numérico válido.");
            }
        });

        HBox panelInferior = new HBox(10, txtAnio, btnComprobar);
        VBox root = new VBox(10, listView, panelInferior);
        root.setPadding(new Insets(15));

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Ejemplo de ObservableList en JavaFX - AÑO BISIESTO");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}