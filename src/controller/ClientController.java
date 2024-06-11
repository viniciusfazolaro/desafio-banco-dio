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
        Client cCpf = null;

        do {
            System.out.println("Digite o nome do cliente:");
            name = sc.nextLine();

            if(name.length() < 3 && name.contains("")) System.out.println("Nome deve conter no mínimo 3 caracteres!");
        } while(name.length() < 3);

        do {
            System.out.println("Digite o CPF do cliente:");
            do {
                cpf = sc.nextLine();
                cCpf = getClientByCpf(cpf);

                if (cCpf != null) System.out.println("CPF já cadastrado!");
            
            } while (cCpf != null);
        } while(cpf.isEmpty());
        
        clients.add(new Client(name, cpf));
        System.out.println("Cliente cadastrado com sucesso!");
        System.out.println("Dados do cliente:");
        clients.get(clients.size() - 1).printInfo();
        System.out.println("Pressione ENTER para continuar...");
        sc.nextLine();
    }

    public Client getClientByCpf(String cpf) {
        return clients.stream().filter(c -> c.getCpf().equals(cpf)).findFirst().orElse(null);
    }

    public void listClients() {
        Scanner sc = new Scanner(System.in);
        if(clients.isEmpty()) System.out.println("Nenhum cliente cadastrado!");
        else clients.forEach(c -> c.printInfo());

        System.out.println("Pressione ENTER para continuar...");
        sc.nextLine();
    }
}
