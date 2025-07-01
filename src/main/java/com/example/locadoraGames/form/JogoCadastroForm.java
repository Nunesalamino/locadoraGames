// Exemplo básico de tela com JavaFX para cadastrar jogo
// Salve este arquivo como JogoCadastroApp.java em um diretório apropriado

package com.example.locadoraGames.form;

import com.example.locadoraGames.enums.Categoria;
import com.example.locadoraGames.config.SpringContextHolder;
import com.example.locadoraGames.enums.Console;
import com.example.locadoraGames.model.Jogo;
import com.example.locadoraGames.service.JogoService;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

public class JogoCadastroForm extends Application {

    private static ConfigurableApplicationContext springContext;
    private JogoService jogoService;

    public JogoCadastroForm(ConfigurableApplicationContext springContext) {
    }

    @Override
    public void init() {
        ApplicationContext context = SpringContextHolder.getApplicationContext();
        jogoService = context.getBean(JogoService.class);
    }

    @Override
    public void start(Stage primaryStage) {
        init();
        primaryStage.setTitle("Cadastro de Jogo");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);

        TextField nomeField = new TextField();
        TextField precoField = new TextField();

        ComboBox<Categoria> categoriaBox = new ComboBox<>();
        categoriaBox.getItems().addAll(Categoria.values());

        ComboBox<Console> consoleBox = new ComboBox<>();
        consoleBox.getItems().addAll(Console.values());

        Button salvarBtn = new Button("Salvar Jogo");

        grid.add(new Label("Nome:"), 0, 0);
        grid.add(nomeField, 1, 0);
        grid.add(new Label("Preço:"), 0, 1);
        grid.add(precoField, 1, 1);
        grid.add(new Label("Categoria:"), 0, 2);
        grid.add(categoriaBox, 1, 2);
        grid.add(new Label("Console:"), 0, 3);
        grid.add(consoleBox, 1, 3);
        grid.add(salvarBtn, 1, 4);

        salvarBtn.setOnAction(e -> {
            try {
                Jogo jogo = new Jogo();
                jogo.setNome(nomeField.getText());
                jogo.setPreco(Double.parseDouble(precoField.getText()));
                jogo.setCategoria(categoriaBox.getValue());
                jogo.setConsole(consoleBox.getValue());

                jogoService.salvarJogo(jogo);

                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Jogo salvo com sucesso!");
                alert.showAndWait();

                nomeField.clear();
                precoField.clear();
                categoriaBox.getSelectionModel().clearSelection();
                consoleBox.getSelectionModel().clearSelection();

            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Erro ao salvar jogo: " + ex.getMessage());
                alert.showAndWait();
            }
        });

        Scene scene = new Scene(grid, 400, 250);
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
