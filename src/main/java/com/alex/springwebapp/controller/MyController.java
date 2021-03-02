package com.alex.springwebapp.controller;

import java.util.ArrayList;
import java.util.List;


import com.alex.springwebapp.model.Book;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class MyController {
    List<Book> bookList =new ArrayList<>();
    int id = 1;
    @GetMapping(value="/books")
    public String getMethodName(Model model) {
        model.addAttribute("books", bookList);
        return "index";
    }
    @GetMapping(value = "/getBookByIndex/{id}")
    public String getBooksByIndx(@PathVariable("id") int id,Model model){
        model.addAttribute("book", bookList.get(id));
        return "book";
    }
    @PostMapping(value = "/addBook")
    public String addBook(Book book,Model model){
        book.setId(id++);
        bookList.add(book);
        model.addAttribute("books", bookList);
        return "insertBook";
    }
    @GetMapping(value = "/addBookView")
    public String insetrView(Model model){
        Book book = new Book();
        model.addAttribute("book", book);
        model.addAttribute("books", bookList);
        return "insertBook";
    }

    @PostMapping(value = "/edit/book/{id}")
    public String updateBookByIndex(@PathVariable("id") int id,Book b,Model model){
        bookList.get(id).setName(b.getName());
        return "redirect:/books";
    }
    @DeleteMapping(value = "/books/delete/{id}")
    public String getBookByIndex(@PathVariable("id") int id){
        bookList.remove(id);
        return "redirect:/books";
    }
    
}
