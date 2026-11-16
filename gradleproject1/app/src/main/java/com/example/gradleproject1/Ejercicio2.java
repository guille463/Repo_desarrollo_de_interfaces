package com.example.gradleproject1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Ejercicio2 extends Application {

    // Contador de intentos fallidos
    private int intentos = 0;

    @Override
    public void start(Stage primaryStage) {
        Label lblTitulo = new Label("Login Username and Password");
        
        // Imagen (puedes usar una URL o una ruta local de tu proyecto)
        ImageView imgUsuario = new ImageView(new Image("https://cdn-icons-png.flaticon.com/512/149/149071.png"));
        imgUsuario.setFitWidth(50);
        imgUsuario.setFitHeight(50);

        Label lblUser = new Label("Username");
        TextField txtUser = new TextField();

        Label lblPass = new Label("Password");
        PasswordField txtPass = new PasswordField();

        Button btnOk = new Button("OK");

        // Credenciales de prueba
        final String USER_CORRECTO = "weerachai";
        final String PASS_CORRECTO = "1234";

        btnOk.setOnAction(e -> {
            if (txtUser.getText().equals(USER_CORRECTO) && txtPass.getText().equals(PASS_CORRECTO)) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Login correcto", "¡Bienvenido " + USER_CORRECTO + "!");
                intentos = 0; // Reiniciamos contador
            } else {
                intentos++;
                if (intentos > 5) {
                    mostrarAlerta(Alert.AlertType.ERROR, "Bloqueado", "Has superado los 5 intentos permitidos.");
                    btnOk.setDisable(true); // Desabilitar boton
                } else {
                    mostrarAlerta(Alert.AlertType.WARNING, "Error", "Credenciales incorrectas. Intento " + intentos + " de 5.");
                }
            }
        });

        VBox root = new VBox(10, lblTitulo, imgUsuario, lblUser, txtUser, lblPass, txtPass, btnOk);
        root.setPadding(new Insets(15));

        Scene scene = new Scene(root, 300, 320);
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}