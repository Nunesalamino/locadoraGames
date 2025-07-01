package com.example.locadoraGames;

import com.example.locadoraGames.model.Cliente;
import com.example.locadoraGames.service.ClienteService;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;


public class LocadoraGamesApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(LocadoraGamesApplication.class, args);

//		JogoBO jogoBO = context.getBean(JogoBO.class);
//		Jogo jogo = new Jogo();
//		jogoBO.salvarJogo(jogo);

		ClienteService clienteService = context.getBean(ClienteService.class);
		Cliente cliente;
		cliente = new Cliente();
		cliente = clienteService.lerCliente();
		clienteService.salvarCliente(cliente);
	}
}
