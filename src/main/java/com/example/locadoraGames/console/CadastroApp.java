// Classe principal com menu de seleção entre cadastro de Jogo ou Cliente
package com.example.locadoraGames.console;

import com.example.locadoraGames.form.ClienteCadastroForm;
import com.example.locadoraGames.form.JogoCadastroForm;
import com.example.locadoraGames.config.SpringContextHolder;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class CadastroApp extends Application {

    private static ConfigurableApplicationContext springContext;

//    @Override
//    public void init() {
//        springContext = SpringApplication.run(CadastroApp.class);
//    }
@Override
public void init() {
    springContext = SpringApplication.run(CadastroApp.class);
    SpringContextHolder.setApplicationContext(springContext);
}


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sistema de Cadastro");

        Button cadastrarJogoBtn = new Button("Cadastrar Jogo");
        Button cadastrarClienteBtn = new Button("Cadastrar Cliente");

        cadastrarJogoBtn.setOnAction(e -> {
            JogoCadastroForm jogoForm = new JogoCadastroForm(springContext);
            jogoForm.start(new Stage());
        });

        cadastrarClienteBtn.setOnAction(e -> {
            ClienteCadastroForm clienteForm = new ClienteCadastroForm(springContext);
            clienteForm.start(new Stage());
        });

        VBox vbox = new VBox(15, cadastrarJogoBtn, cadastrarClienteBtn);
        vbox.setPadding(new Insets(20));

        Scene scene = new Scene(vbox, 300, 150);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() {
        springContext.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

// Tela de cadastro de Cliente
