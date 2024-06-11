package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.client.Client;

public class ClientController {
    
    private static List<Client> clients = new ArrayList<>();
    
    public ClientController() {
    }

    public void addClient() {
        Scanner sc = new Scanner(System.in);

        String name = null;
        String cpf = null;

        do {
            System.out.println("Digite o nome do cliente:");
            name = sc.nextLine();
        } while(name.length() < 3);

        do {
            System.out.println("Digite o CPF do cliente:");
            cpf = sc.nextLine();
        } while(cpf.isEmpty());
        
        clients.add(new Client(name, cpf));
        System.out.println("Cliente cadastrado com sucesso!");
        System.out.println("Pressione ENTER para continuar...");
        sc.nextLine();
    }

    public Client getClientByCpf(String cpf) {
        return clients.stream().filter(c -> c.getCpf().equals(cpf)).findFirst().orElse(null);
    }

    public void listClients() {
        Scanner sc = new Scanner(System.in);
        if(clients.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado!");
        } else {
            clients.forEach(c -> c.printInfo());
        }
        System.out.println("Pressione ENTER para continuar...");
        sc.nextLine();
    }
}
