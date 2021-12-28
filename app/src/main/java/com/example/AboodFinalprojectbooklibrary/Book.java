package com.example.AboodFinalprojectbooklibrary;

import java.io.Serializable;

public class Book implements Serializable {

    int id,category;
    String image,name,author,release,pages;


    public Book(int id,String image,String name,String author,String release,String pages,int category){
        this.id=id;
        this.image=image;
        this.name=name;
        this.author=name;
        this.release=release;
        this.pages=pages;
        this.category=category;
    }
    public Book(String image,String name,String author,String release,String pages,int category){
        this.image=image;
        this.name=name;
        this.author=name;
        this.release=release;
        this.pages=pages;
        this.category=category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }
}
