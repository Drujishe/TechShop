package ru.drujishe.techshop.model;

import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;
import ru.drujishe.techshop.model.abs.AbstractItem;

@Entity
@NoArgsConstructor
@JsonTypeName("hdd")
public class HDD extends AbstractItem {
    int memorySize;

    public HDD(int serial, String company, int price, int count, int memorySize) {
        super(serial, company, price, count);
        this.memorySize = memorySize;
    }
}
