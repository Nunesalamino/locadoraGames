package com.example.locadoraGames.service;

import com.example.locadoraGames.enums.Categoria;
import com.example.locadoraGames.model.Jogo;
import com.example.locadoraGames.repository.JogoRepository;
import com.example.locadoraGames.enums.Console;
import org.springframework.stereotype.Service;

import java.util.Scanner;
@Service
public class JogoService {
    private final JogoRepository jogoRepository;

    public JogoService(JogoRepository jogoRepository) {
        this.jogoRepository = jogoRepository;
    }

    public void salvarJogo(Jogo jogo){
        //jogo = lerJogo();
        jogoRepository.save(jogo);
        System.out.println("Jogo cadastrado!");
    }

    private Jogo lerJogo() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o nome do jogo: ");
        String nome = scanner.nextLine();

        System.out.println("Digite o preço do jogo: ");
        Double preco = scanner.nextDouble();

        Categoria categoria = null;
        scanner = new Scanner(System.in);

        while (categoria == null) {
            System.out.println("Digite a categoria (ex: ACAO, AVENTURA, RPG): ");
            String categoriaInput = scanner.nextLine().toUpperCase();
            try {
                categoria = Categoria.valueOf(categoriaInput);
            } catch (IllegalArgumentException e) {
                System.out.println("Categoria inválida. Tente novamente.");
            }
        }

        Console console = null;
        while (console == null) {
            System.out.println("Digite o console (ex: PS5, PS4, XBOX, SWITCH): ");
            String consoleInput = scanner.nextLine().toUpperCase();
            try {
                console = Console.valueOf(consoleInput);
            } catch (IllegalArgumentException e) {
                System.out.println("Console inválido. Tente novamente.");
            }
        }

        Jogo jogo = new Jogo();
        jogo.setNome(nome);
        jogo.setCategoria(categoria);
        jogo.setConsole(console);
        jogo.setPreco(preco);

        return jogo;
    }
}
