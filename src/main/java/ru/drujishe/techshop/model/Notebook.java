package ru.drujishe.techshop.model;

import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.drujishe.techshop.exception.AddingItemException;
import ru.drujishe.techshop.model.abs.AbstractItem;

@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonTypeName("notebook")
public class Notebook extends AbstractItem {
    int screenSize;

    public Notebook(int serial, String company, int price, int count, int screenSize) {
        super(serial, company, price, count);
        this.screenSize = screenSize;
    }

    public void setScreenSize(int screenSize) {
        if (screenSize == 13 || screenSize == 14 || screenSize == 15 || screenSize == 17)
            this.screenSize = screenSize;
        else
            throw new AddingItemException("Unsupported NoteBook ScreenSize : " + screenSize);
    }
}
