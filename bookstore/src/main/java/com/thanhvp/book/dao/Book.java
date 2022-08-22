/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thanhvp.book.dao;

import java.io.Serializable;

/**
 *
 * @author phuoc
 */
// có bao nhiêu  loại interface
//1.Makerr interface: loại interface mà k y/c class implements no phải có code 
//interface k có hàm abstrac - bao nhiêu cho JVM biết cần phải làm gì !!
//2/ Functional Interface : loại interface ma chỉ có duy nhất 1 hàm abstract k nhiều hơn
//xai interface nay thi dung Anonymous-> chuyensang LamBDA lun 
//Functional inteface chơi với LAMDA EXPRESSION
//Comparable/Comparator
//3. Nhưng interface con lại : implements viet code cho tât cả các hàm 
// Lên mạng kiếm Dependecy LOMBOK, Giups bạn constructor,GET SET NGẮN GỌN 
// Y CHANG C#,@@
public class Book  implements Serializable{
    
    private String isbn;
    private String title;
    private String author;
    private int edition;
    private int publishedYear;

    public Book() {
    }
    
    public Book(String isbn, String title, String author, int edition, int publishedYear) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.edition = edition;
        this.publishedYear = publishedYear;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    @Override
    public String toString() {
        return "Book{" + "isbn=" + isbn + ", title=" + title + ", author=" + author + ", edition=" + edition + ", publishedYear=" + publishedYear + '}';
    }
    
    

}
