package com.openschool.homework1;

import com.openschool.homework1.entity.Book;
import com.openschool.homework1.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class Application {
    private final BookService bookService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onReady() {
        bookService.addBooks(List.of(new Book("Чистый код", "Программирование", "Интересная"),
            new Book("Книга", "Роман", "Впечатляющая")));
        bookService.addBook(new Book("Алхимик", "Роман", "Хорошая"));
        bookService.getAllBooks();
        bookService.updateBook("Книга", new Book("Книга", "Поэма", "Впечатляющая"));
        bookService.removeBook("Книга");
        bookService.getBookByName("Алхимик");

//        bookService.addBooks(List.of(new Book("Детские сказки", "Сказка", "Хорошая")));
//        bookService.removeBook("Название");
    }
}
