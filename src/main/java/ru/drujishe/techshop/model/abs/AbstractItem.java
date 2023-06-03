package ru.drujishe.techshop.model.abs;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.drujishe.techshop.model.Computer;
import ru.drujishe.techshop.model.HDD;
import ru.drujishe.techshop.model.Monitor;
import ru.drujishe.techshop.model.Notebook;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Monitor.class, name = "monitor"),
        @JsonSubTypes.Type(value = Notebook.class, name = "notebook"),
        @JsonSubTypes.Type(value = HDD.class, name = "hdd"),
        @JsonSubTypes.Type(value = Computer.class, name = "computer")
})
public abstract class AbstractItem {
    @Id
    int serial;

    String company;

    int price;

    int count;
}
