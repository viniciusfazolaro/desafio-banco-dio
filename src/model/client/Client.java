package model.client;
import lombok.Getter;

public class Client {
    @Getter private String name;
    @Getter private String cpf;
    
    public Client(String name, String cpf) {
        this.name = name;
        this.cpf = cpf;
    }
    
}
