package ru.drujishe.techshop.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.drujishe.techshop.dao.abs.ItemDao;
import ru.drujishe.techshop.exception.AddingItemException;
import ru.drujishe.techshop.model.abs.AbstractItem;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@SuppressWarnings("rawtypes")
@RestController
@RequestMapping("api")
public class MainRest {

    private final Map<String, ItemDao> map;

    public MainRest(Map<String, ItemDao> map) {
        this.map = map;
    }

    @PostMapping("save")
    public ResponseEntity saveItem(@RequestBody AbstractItem item) {
        var repository = getRepositoryByItemClassName(item.getClass().getSimpleName().toLowerCase());

        AbstractItem foundedItem = repository.findBySerialId(item.getSerial());
        if (foundedItem != null) throw new AddingItemException("Item is Exist.");
        return ResponseEntity.ok(repository.save(item));

        /* Так как в тз не было сказано, что делать с объектом, серийный номер которого встречается в таблице,
         * то я просто бросаю исключение.
         * Но можно реализовать алгоритм, когда будет добавляться к найденной сущности количество товара (суммироваться)
         * а затем будет выполнен update в бд.
         *  */
    }

    @GetMapping("find")
    public ResponseEntity findItemsByType(@RequestParam String type) {
        var repository = getRepositoryByItemClassName(type);
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("findAll")
    public List<AbstractItem> findBySerial(@RequestParam int serialId) {
        return map.values().stream()
                .map(x -> x.findBySerialId(serialId))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @PostMapping("update")
    public ResponseEntity updateItem(@RequestBody AbstractItem item) {
        var repository = getRepositoryByItemClassName(item.getClass().getSimpleName().toLowerCase());
        return ResponseEntity.ok(repository.update(item));
    }

    @PostMapping("delete")
    public ResponseEntity deleteItem(@RequestBody AbstractItem item) {
        var repository = getRepositoryByItemClassName(item.getClass().getSimpleName().toLowerCase());
        repository.delete(item);
        return ResponseEntity.status(200).build();
    }

    private ItemDao getRepositoryByItemClassName(String className) {
        var repository = map.get(className + "Dao");
        if (repository == null) throw new AddingItemException("Repository not found.");
        return repository;
    }
}
