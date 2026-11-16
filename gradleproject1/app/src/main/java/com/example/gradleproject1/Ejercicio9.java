package com.example.gradleproject1;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Ejercicio9 extends Application {

    // Clase modelo para almacenar los elementos tipo "Año"[cite: 2]
    public static class Anio {
        private int anio;
        private String bisiesto;

        public Anio(int anio, String bisiesto) {
            this.anio = anio;
            this.bisiesto = bisiesto;
        }

        // Los getters son obligatorios para que PropertyValueFactory encuentre los datos
        public int getAnio() {
            return anio;
        }

        public String getBisiesto() {
            return bisiesto;
        }
    }

    @Override
    public void start(Stage primaryStage) {
        // 1. Creamos el ObservableList de objetos Anio y el TableView[cite: 2]
        ObservableList<Anio> listaAnios = FXCollections.observableArrayList();
        TableView<Anio> tableView = new TableView<>(listaAnios);

        // 2. Configuramos las dos columnas: AÑO y BISIESTO[cite: 2]
        TableColumn<Anio, Integer> colAnio = new TableColumn<>("AÑO");
        // "anio" debe coincidir exactamente con el nombre del atributo / getAnio()
        colAnio.setCellValueFactory(new PropertyValueFactory<>("anio"));
        colAnio.setPrefWidth(100);

        TableColumn<Anio, String> colBisiesto = new TableColumn<>("BISIESTO");
        // "bisiesto" debe coincidir exactamente con el nombre del atributo / getBisiesto()
        colBisiesto.setCellValueFactory(new PropertyValueFactory<>("bisiesto"));
        colBisiesto.setPrefWidth(150);

        // Añadimos las columnas a la tabla
        tableView.getColumns().addAll(colAnio, colBisiesto);

        // 3. Controles de entrada
        TextField txtAnio = new TextField();
        txtAnio.setPromptText("Introduce un año");
        Button btnComprobar = new Button("Comprobar AÑO");

        // 4. Lógica al pulsar el botón
        btnComprobar.setOnAction(e -> {
            try {
                int valorAnio = Integer.parseInt(txtAnio.getText().trim());

                if (valorAnio <= 0) {
                    mostrarError("Introduce un año positivo.");
                    return;
                }

                boolean esBisiesto = (valorAnio % 4 == 0 && valorAnio % 100 != 0) || (valorAnio % 400 == 0);
                String estado = esBisiesto ? "BISIESTO" : "NO BISIESTO";

                // Añadimos un nuevo objeto Anio a la lista observable[cite: 2]
                listaAnios.add(new Anio(valorAnio, estado));

                txtAnio.clear();
                txtAnio.requestFocus();

            } catch (NumberFormatException ex) {
                mostrarError("Dato incorrecto. Por favor, introduce un año numérico válido.");
            }
        });

        // 5. Layout (igual que la captura del PDF)[cite: 2]
        HBox panelInferior = new HBox(10, txtAnio, btnComprobar);
        VBox root = new VBox(10, tableView, panelInferior);
        root.setPadding(new Insets(15));

        // 6. Escena y ventana
        Scene scene = new Scene(root, 420, 320);
        primaryStage.setTitle("Ejemplo de ObservableList en JavaFX - TableView - AÑO BISIESTO");
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