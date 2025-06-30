package com.example.locadoraGames;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


public class LocadoraGamesApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(LocadoraGamesApplication.class, args);

//		JogoBO jogoBO = context.getBean(JogoBO.class);
//		Jogo jogo = new Jogo();
//		jogoBO.salvarJogo(jogo);

		ClienteBO clienteBO = context.getBean(ClienteBO.class);
		Cliente cliente;
		cliente = new Cliente();
		cliente = clienteBO.lerCliente();
		clienteBO.salvarCliente(cliente);
	}
}
