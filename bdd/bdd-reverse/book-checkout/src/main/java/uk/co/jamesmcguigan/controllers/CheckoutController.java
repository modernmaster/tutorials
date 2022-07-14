package uk.co.jamesmcguigan.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uk.co.jamesmcguigan.checkout.Book;
import uk.co.jamesmcguigan.checkout.BookTransformer;
import uk.co.jamesmcguigan.checkout.Bookstore;
import uk.co.jamesmcguigan.models.BookViewModel;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CheckoutController {

    private final Bookstore bookstore;
    private final BookTransformer bookTransformer;

    @CrossOrigin
    @PostMapping("/bookstore/checkout")
    public double post(@RequestBody BookViewModel bookViewModel) {
        List<Book> bookList = bookTransformer.transform(bookViewModel);
        return bookstore.checkout(bookList);
    }

}
