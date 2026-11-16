package com.example.gradleproject1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Ejercicio5 extends Application {

    @Override
    public void start(Stage primaryStage) {
        // 1. Controles básicos requeridos por el enunciado
        Label lblPrecio = new Label("Precio (€):");
        TextField txtPrecio = new TextField();

        Label lblDinero = new Label("Dinero entregado (€):");
        TextField txtDinero = new TextField();

        Button btnCalcular = new Button("Calcular Cambio");
        Label lblResultado = new Label("Resultado:");

        // 2. Lógica al pulsar el botón
        btnCalcular.setOnAction(e -> {
            try {
                // Trabajamos con enteros ya que la moneda mínima pedida es 1 euro
                int precio = Integer.parseInt(txtPrecio.getText().trim());
                int dinero = Integer.parseInt(txtDinero.getText().trim());

                if (precio < 0 || dinero < 0) {
                    lblResultado.setText("Error: Los importes no pueden ser negativos.");
                    return;
                }

                if (dinero < precio) {
                    int falta = precio - dinero;
                    lblResultado.setText("Falta dinero. Faltan " + falta + " €.");
                } else if (dinero == precio) {
                    lblResultado.setText("Importe exacto. No hay cambio.");
                } else {
                    int cambio = dinero - precio;
                    int resto = cambio;

                    // Array ordenado de mayor a menor para devolver el mínimo de billetes/monedas
                    int[] denominaciones = {500, 100, 50, 20, 10, 2, 1};
                    StringBuilder desglose = new StringBuilder();
                    desglose.append("Cambio total: ").append(cambio).append(" €\n\nDesglose:\n");

                    for (int valor : denominaciones) {
                        int cantidad = resto / valor;
                        if (cantidad > 0) {
                            String tipo = (valor >= 10) ? "billete(s)" : "moneda(s)";
                            desglose.append("- ").append(cantidad).append(" ").append(tipo)
                                    .append(" de ").append(valor).append(" €\n");
                            resto = resto % valor; // Nos quedamos con el resto para la siguiente denominación
                        }
                    }

                    lblResultado.setText(desglose.toString());
                }

            } catch (NumberFormatException ex) {
                lblResultado.setText("Error: Introduce cantidades enteras válidas.");
            }
        });

        // 3. Layout VBox
        VBox root = new VBox(10, lblPrecio, txtPrecio, lblDinero, txtDinero, btnCalcular, lblResultado);
        root.setPadding(new Insets(15));

        // 4. Escena y ventana (un poco más alta para que quepa todo el desglose)
        Scene scene = new Scene(root, 320, 360);
        primaryStage.setTitle("Ejercicio 5 - Cambio");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}