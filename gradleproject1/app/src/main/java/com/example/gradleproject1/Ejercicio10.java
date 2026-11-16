package com.example.gradleproject1;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Ejercicio10 extends Application {

    @Override
    public void start(Stage primaryStage) {
        //  barra de menú 
        MenuBar menuBar = new MenuBar();

        // Submenu: Fichero (Nuevo, Abrir, Guardar, Separador, Salir
        Menu menuFichero = new Menu("Fichero");
        MenuItem itemNuevo = new MenuItem("Nuevo");
        MenuItem itemAbrir = new MenuItem("Abrir");
        MenuItem itemGuardar = new MenuItem("Guardar");
        SeparatorMenuItem sepFichero = new SeparatorMenuItem();
        MenuItem itemSalir = new MenuItem("Salir");

        // "Salir" cierre la aplicación
        itemSalir.setOnAction(e -> Platform.exit());

        menuFichero.getItems().addAll(itemNuevo, itemAbrir, itemGuardar, sepFichero, itemSalir);

        // Clientes (Listar, Separador, Informe Clientes)
        Menu menuClientes = new Menu("Clientes");
        MenuItem itemListarClientes = new MenuItem("Listar");
        SeparatorMenuItem sepClientes = new SeparatorMenuItem();
        MenuItem itemInformeClientes = new MenuItem("Informe Clientes");

        menuClientes.getItems().addAll(itemListarClientes, sepClientes, itemInformeClientes);

        // Submenu: Productos (Listar, Separador, Informe Productos)
        Menu menuProductos = new Menu("Productos");
        MenuItem itemListarProductos = new MenuItem("Listar");
        SeparatorMenuItem sepProductos = new SeparatorMenuItem();
        MenuItem itemInformeProductos = new MenuItem("Informe Productos");

        menuProductos.getItems().addAll(itemListarProductos, sepProductos, itemInformeProductos);

        // Añadimos los tres mens a la barra superior
        menuBar.getMenus().addAll(menuFichero, menuClientes, menuProductos);

        // Layout VBox (sin padding para que la barra quede pegada arriba y a los lados)
        VBox root = new VBox(menuBar);

        // Escena y ventana 
        Scene scene = new Scene(root, 350, 280);
        primaryStage.setTitle("Menú");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}