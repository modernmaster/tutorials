package uk.co.jamesmcguigan.models;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
@Getter
public class BookViewModel {
    private Map<String, Boolean> books;
}
