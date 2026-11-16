package com.example.gradleproject1;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Ejercicio1 extends Application {

    @Override
    public void start(Stage primaryStage) {
        // 1. Creamos los controles basicos
        Label lblPrecio = new Label("Precio (€)");
        TextField txtPrecio = new TextField();

        Label lblDescuento = new Label("Descuento (%)");
        TextField txtDescuento = new TextField();

        Button btnCalcular = new Button("Calcular");

        // 2. Programamos el evento al pulsar el botón
        btnCalcular.setOnAction(e -> {
            try {
                // Convertimos el texto a números
                double precio = Double.parseDouble(txtPrecio.getText().replace(",","."));
                double porcentaje = Double.parseDouble(txtDescuento.getText().replace(",","."));

                // Calculamos descuento y precio final
                double descuentoAplicado = precio * (porcentaje / 100.0);
                double precioFinal = precio - descuentoAplicado;

                // Creamos y mostramos la ventana emergente (Alert)
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Info");
                alert.setHeaderText("Descuento aplicado. Precio inicial " + precio);
                alert.setContentText("Descuento : " + descuentoAplicado + " Precio final: " + precioFinal);
                alert.showAndWait();

            } catch (NumberFormatException ex) {
                // Por si el usuario deja un campo vacío o mete letras
                Alert error = new Alert(AlertType.ERROR);
                error.setTitle("Error");
                error.setHeaderText("Datos no válidos");
                error.setContentText("Por favor, introduce valores numéricos correctos.");
                error.showAndWait();
            }
        });

        // 3. Metemos todo en un VBox (como indica el enunciado)
        VBox root = new VBox(10, lblPrecio, txtPrecio, lblDescuento, txtDescuento, btnCalcular); // 10px de separación entre elementos
        root.setPadding(new Insets(15));

        // 4. Configuramos la escena y la ventana
        Scene scene = new Scene(root, 250, 220);
        primaryStage.setTitle("Ejercicio 1");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}