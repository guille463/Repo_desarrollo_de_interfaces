package com.example.gradleproject1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Ejercicio6 extends Application {

    // Tabla oficial de letras del DNI según el resto de dividir entre 23
    private static final String LETRAS_DNI = "TRWAGMYFPDXBNJZSQVHLCKE";

    @Override
    public void start(Stage primaryStage) {
        // 1. Controles básicos requeridos por el enunciado
        Label lblDni = new Label("Introduce el DNI:");
        TextField txtDni = new TextField();
        txtDni.setPromptText("Ej: 12345678Z o 12345678");

        CheckBox chkAutoLetra = new CheckBox("Autocalcular letra");
        Button btnValidar = new Button("Validar DNI");
        Label lblResultado = new Label("Resultado: -");

        // 2. Lógica al pulsar el botón
        btnValidar.setOnAction(e -> {
            String entrada = txtDni.getText().trim().toUpperCase();

            // Modo A: El CheckBox está activo -> Autocalcular letra a partir de los 8 números
            if (chkAutoLetra.isSelected()) {
                // Si el usuario escribió también la letra, nos quedamos solo con los 8 primeros dígitos
                String soloNumeros = (entrada.length() == 9) ? entrada.substring(0, 8) : entrada;

                if (soloNumeros.matches("\\d{8}")) {
                    int numero = Integer.parseInt(soloNumeros);
                    char letraCalculada = LETRAS_DNI.charAt(numero % 23);
                    String dniCompleto = soloNumeros + letraCalculada;

                    txtDni.setText(dniCompleto); // Rellenamos el TextField con el DNI completo
                    lblResultado.setText("Letra autocalculada: " + dniCompleto);
                } else {
                    lblResultado.setText("Error: Introduce 8 dígitos para calcular la letra.");
                }

            // Modo B: El CheckBox NO está activo -> Validar DNI completo (8 números + 1 letra)
            } else {
                if (entrada.matches("\\d{8}[A-Z]")) {
                    int numero = Integer.parseInt(entrada.substring(0, 8));
                    char letraIntroducida = entrada.charAt(8);
                    char letraCorrecta = LETRAS_DNI.charAt(numero % 23);

                    if (letraIntroducida == letraCorrecta) {
                        lblResultado.setText("DNI VÁLIDO.");
                    } else {
                        lblResultado.setText("DNI INVÁLIDO. La letra correcta sería: " + letraCorrecta);
                    }
                } else {
                    lblResultado.setText("Formato incorrecto. Debe tener 8 números y 1 letra.");
                }
            }
        });

        // 3. Layout VBox
        VBox root = new VBox(10, lblDni, txtDni, chkAutoLetra, btnValidar, lblResultado);
        root.setPadding(new Insets(15));

        // 4. Escena y ventana
        Scene scene = new Scene(root, 340, 230);
        primaryStage.setTitle("Ejercicio 6 - Validador DNI");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}