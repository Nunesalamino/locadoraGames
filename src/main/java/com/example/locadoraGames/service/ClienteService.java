package com.example.locadoraGames.service;

import com.example.locadoraGames.model.Cliente;
import com.example.locadoraGames.repository.ClienteRepository;
import com.example.locadoraGames.model.Endereco;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class ClienteService {
    private  final ClienteRepository clienteRepository;


    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public void salvarCliente(Cliente cliente){
        clienteRepository.save(cliente);
        System.out.println("Cliente cadastrado!");
    }

    public Cliente lerCliente() {
        String nome = solicitarEntrada("Digite o nome do cliente: ");
        String telefone = solicitarEntrada("Digite o telefone: ");
        String cpf = solicitarEntrada("Digite o CPF: ");
        String cep = solicitarEntrada("Digite o CEP: ");
        String numero = solicitarEntrada("Digite o número do imóvel: ");

        CepService cepService = new CepService();
        Endereco endereco = cepService.buscarEnderecoPorCep(cep);

        return construirCliente(nome, telefone, cpf, cep, numero, endereco);
    }

    private String solicitarEntrada(String mensagem) {
        System.out.print(mensagem);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private Cliente construirCliente(String nome, String telefone, String cpf, String cep, String numero, Endereco endereco) {
        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setTelefone(telefone);
        cliente.setCpf(cpf);
        cliente.setCep(cep);
        cliente.setNumero(numero);
        cliente.setRua(endereco.getLogradouro());
        cliente.setBairro(endereco.getBairro());
        cliente.setCidade(endereco.getLocalidade());
        cliente.setEstado(endereco.getUf());
        return cliente;
    }
}
