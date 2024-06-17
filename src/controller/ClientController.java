package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.client.Client;

public class ClientController {
    
    private static List<Client> clients = new ArrayList<>(); // Lista de clientes
    
    // Construtor
    public ClientController() { } 

    // metodo para adicionar cliente
    public void addClient() {
        Scanner sc = new Scanner(System.in);

        String name = null;
        String cpf = null;
        Client cCpf = null;

        do { // enquanto o nome for menor que 3 caracteres
            System.out.println("Digite o nome do cliente:");
            name = sc.nextLine();

            if(name.length() < 3) System.out.println("Nome deve conter no mínimo 3 caracteres!"); 
        } while(name.length() < 3);

        do { // enquanto o cpf for menor que 11 caracteres ou já existir
            System.out.println("Digite o CPF do cliente:");
            cpf = sc.nextLine();

            cCpf = getClientByCpf(cpf);
            if (cCpf != null) System.out.println("CPF já cadastrado!");
            if (cpf.length() != 11) System.out.println("CPF deve conter 11 dígitos!");
        } while (cCpf != null || cpf.length() != 11);
        
        clients.add(new Client(name, cpf)); // adiciona o cliente na lista
        System.out.println("Cliente cadastrado com sucesso!");
        System.out.println("Dados do cliente:");
        clients.get(clients.size() - 1).printInfo();
        System.out.println("Pressione ENTER para continuar...");
        sc.nextLine();
    }

    // metodo para buscar cliente por cpf
    public Client getClientByCpf(String cpf) { return clients.stream().filter(c -> c.getCpf().equals(cpf)).findFirst().orElse(null); }

    // metodo para listar clientes
    public void listClients() {
        Scanner sc = new Scanner(System.in);
        if(clients.isEmpty()) System.out.println("Nenhum cliente cadastrado!"); // se a lista estiver vazia
        else clients.forEach(c -> c.printInfo()); // imprime os dados de cada cliente

        System.out.println("Pressione ENTER para continuar...");
        sc.nextLine();
    }
}
