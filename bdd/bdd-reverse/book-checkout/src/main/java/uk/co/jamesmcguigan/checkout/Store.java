package uk.co.jamesmcguigan.checkout;

import java.util.List;

public interface Store {
    double checkout(List<Book> bookList);
}
