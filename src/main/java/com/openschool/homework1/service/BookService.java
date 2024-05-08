package com.openschool.homework1.service;

import com.openschool.homework1.annotation.TrackAsyncTime;
import com.openschool.homework1.annotation.TrackTime;
import com.openschool.homework1.entity.Book;
import com.openschool.homework1.entity.BookException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final Map<String, Book> books = new HashMap<>();
    @TrackTime
    public void addBook(Book book){
        books.put(book.getName(), book);
    }
    @TrackTime
    public void addBooks(List<Book> newBooks) {
        if (newBooks.size() == 1){
            throw new BookException("Используйте метод addBook(Book book)");
        }

        books.putAll(newBooks.stream().collect(Collectors.toMap(Book::getName, Function.identity())));
    }
    @TrackTime
    public Book getBookByName(String name){
        return books.get(name);
    }
    @TrackTime
    public List<Book> getBooksByCategory(String category){
        return books.values().stream().filter(book -> book.getCategory().equals(category)).collect(Collectors.toList());
    }

    @TrackAsyncTime
    public void updateBook(String name, Book updatedBook){
        if (!books.containsKey(name)) {
            throw new BookException("Книга с названием " + name + " не найдена.");
        }

        books.put(name, updatedBook);
    }

    @TrackAsyncTime
    public void removeBook(String name){
        if (!books.containsKey(name)) {
            throw new BookException("Книга с названием " + name + " не найдена.");
        }

        books.remove(name);
    }

    @TrackAsyncTime
    public List<Book> getAllBooks(){
        return new ArrayList<>(books.values());
    }
}
