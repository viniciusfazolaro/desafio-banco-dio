package model.client;
import lombok.Getter;

public class Client {
    @Getter private String name;
    @Getter private String cpf;
    
    // Construtor
    public Client(String name, String cpf) {
        this.name = name;
        this.cpf = cpf;
    }

    // método para imprimir dados do cliente
    public void printInfo() {
        System.out.println("*** Dados do Cliente ***");
        System.out.println(String.format("Nome: %s", name));
        System.out.println(String.format("CPF: %s", cpf));
        System.out.println("***********************");
    }
    
}
