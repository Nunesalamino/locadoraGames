package com.example.locadoraGames;

import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class ClienteBO {
    private  final ClienteRepository clienteRepository;

    public ClienteBO(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public void salvarCliente(Cliente cliente){
        clienteRepository.save(cliente);
        System.out.println("Cliente cadastrado!");
    }

    public Cliente lerCliente() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o nome do cliente: ");
        String nome = scanner.nextLine();

        System.out.println("Digite o telefone: ");
        String telefone = scanner.nextLine();

        System.out.println("Digite o CPF: ");
        String cpf = scanner.nextLine();

        System.out.println("Digite o CEP: ");
        String cep = scanner.nextLine();

        System.out.println("Digite o número do imóvel: ");
        String numero = scanner.nextLine();

        CepService cepService = new CepService();

        Endereco endereco = cepService.buscarEnderecoPorCep(cep);

        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setTelefone(telefone);
        cliente.setCpf(cpf);
        cliente.setRua(endereco.getLogradouro());
        cliente.setNumero(numero);
        cliente.setBairro(endereco.getBairro());
        cliente.setCidade(endereco.getLocalidade());
        cliente.setEstado(endereco.getUf());
        cliente.setCep(cep);

        return cliente;
    }
}
