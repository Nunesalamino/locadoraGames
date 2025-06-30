package com.example.locadoraGames;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

public class ClienteCadastroForm extends Application {

    private static ApplicationContext springContext;
    private ClienteBO clienteBO;

    public ClienteCadastroForm(ConfigurableApplicationContext springContext) {
    }

    @Override
    public void init() {
        springContext = SpringContextHolder.getApplicationContext();
        clienteBO = springContext.getBean(ClienteBO.class);
    }

    @Override
    public void start(Stage primaryStage) {
        init();

        primaryStage.setTitle("Cadastro de Cliente");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);

        TextField nomeField = new TextField();
        TextField cpfField = new TextField();
        TextField telefoneField = new TextField();
        TextField cepField = new TextField();
        TextField numeroField = new TextField();

        TextField ruaField = new TextField();
        TextField bairroField = new TextField();
        TextField cidadeField = new TextField();
        TextField estadoField = new TextField();

        // Deixe os campos de endereço como somente leitura
        ruaField.setEditable(false);
        bairroField.setEditable(false);
        cidadeField.setEditable(false);
        estadoField.setEditable(false);

        Button salvarBtn = new Button("Salvar Cliente");

        grid.add(new Label("Nome:"), 0, 0);
        grid.add(nomeField, 1, 0);
        grid.add(new Label("CPF:"), 0, 1);
        grid.add(cpfField, 1, 1);
        grid.add(new Label("Telefone:"), 0, 2);
        grid.add(telefoneField, 1, 2);
        grid.add(new Label("CEP:"), 0, 3);
        grid.add(cepField, 1, 3);
        grid.add(new Label("Número:"), 0, 4);
        grid.add(numeroField, 1, 4);
        grid.add(new Label("Rua:"), 0, 5);
        grid.add(ruaField, 1, 5);
        grid.add(new Label("Bairro:"), 0, 6);
        grid.add(bairroField, 1, 6);
        grid.add(new Label("Cidade:"), 0, 7);
        grid.add(cidadeField, 1, 7);
        grid.add(new Label("Estado:"), 0, 8);
        grid.add(estadoField, 1, 8);
        grid.add(salvarBtn, 1, 9);

        // Listener para buscar o endereço ao digitar o CEP
        cepField.setOnKeyReleased(event -> {
            String cep = cepField.getText().replaceAll("[^0-9]", "");
            if (cep.length() == 8) {
                try {
                    CepService cepService = springContext.getBean(CepService.class);
                    Endereco endereco = cepService.buscarEnderecoPorCep(cep);
                    if (endereco != null && endereco.getLogradouro() != null) {
                        ruaField.setText(endereco.getLogradouro());
                        bairroField.setText(endereco.getBairro());
                        cidadeField.setText(endereco.getLocalidade());
                        estadoField.setText(endereco.getUf());
                    } else {
                        ruaField.clear();
                        bairroField.clear();
                        cidadeField.clear();
                        estadoField.clear();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        salvarBtn.setOnAction(e -> {
            try {
                Cliente cliente = new Cliente();
                cliente.setNome(nomeField.getText());
                cliente.setCpf(cpfField.getText());
                cliente.setTelefone(telefoneField.getText());
                cliente.setCep(cepField.getText());
                cliente.setNumero(numeroField.getText());
                cliente.setRua(ruaField.getText());
                cliente.setBairro(bairroField.getText());
                cliente.setCidade(cidadeField.getText());
                cliente.setEstado(estadoField.getText());

                clienteBO.salvarCliente(cliente);

                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Cliente salvo com sucesso!");
                alert.showAndWait();

                // Limpar campos
                nomeField.clear();
                cpfField.clear();
                telefoneField.clear();
                cepField.clear();
                numeroField.clear();
                ruaField.clear();
                bairroField.clear();
                cidadeField.clear();
                estadoField.clear();

            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Erro ao salvar cliente: " + ex.getMessage());
                alert.showAndWait();
            }
        });

        Scene scene = new Scene(grid, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
