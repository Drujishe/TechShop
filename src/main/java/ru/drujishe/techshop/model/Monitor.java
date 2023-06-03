package ru.drujishe.techshop.model;

import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.drujishe.techshop.model.abs.AbstractItem;

@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonTypeName("monitor")
public class Monitor extends AbstractItem {
    float screenSize;

    public Monitor(int serial, String company, int price, int count, float screenSize) {
        super(serial, company, price, count);
        this.screenSize = screenSize;
    }
}
