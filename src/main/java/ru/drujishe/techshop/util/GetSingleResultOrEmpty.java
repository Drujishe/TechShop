package ru.drujishe.techshop.util;

import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GetSingleResultOrEmpty<T> {
    public static <T> Optional<T> getResult(TypedQuery<T> query) {
        try {
            return Optional.of((T) query.getSingleResult());
        } catch (Exception exc) {
            return Optional.empty();
        }
    }
}
