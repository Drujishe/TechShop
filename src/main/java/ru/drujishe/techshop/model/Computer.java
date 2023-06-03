package ru.drujishe.techshop.model;

import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.drujishe.techshop.model.abs.AbstractItem;
import ru.drujishe.techshop.model.abs.ComputerType;

@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonTypeName("computer")
public class Computer extends AbstractItem {
    @Enumerated(EnumType.STRING)
    ComputerType computerType;

    public Computer(int serial, String company, int price, int count, ComputerType computerType) {
        super(serial, company, price, count);
        this.computerType = computerType;
    }
}
