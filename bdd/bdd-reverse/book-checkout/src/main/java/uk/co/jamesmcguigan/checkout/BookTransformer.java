package uk.co.jamesmcguigan.checkout;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;
import uk.co.jamesmcguigan.models.BookViewModel;

import java.util.List;

@Component
public class BookTransformer {

    private final String[] bookTitles = {"book1", "book2", "book3", "book4", "book5"};

    public List<Book> transform(BookViewModel bookViewModel) {
        List<Book> books = Lists.newArrayList();
        for (String bookTitle : bookTitles) {
            if (bookViewModel.getBooks().get(bookTitle)) {
                books.add(new Book(bookTitle));
            }
        }
        return books;
    }
}
