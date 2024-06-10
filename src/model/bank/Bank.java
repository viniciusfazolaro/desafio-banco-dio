package model.bank;
import lombok.Getter;

public class Bank {

    @Getter private String name;

    public Bank(String name) {
        this.name = name;
    }

}
