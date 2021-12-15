package org.factoriaf5.bftp2project4grupo4.repositories;

import com.sun.istack.NotNull;
import org.hibernate.property.access.spi.Getter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.concurrent.Flow;

@Entity
@Table(name = "juegos")
public class Juego implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @NotNull
    private String title;
    @NotNull
    private String platform;
    @NotNull
    private int year;
    @NotNull
    private double price1;
    private int discount;
    private double price2;
    @NotNull
    private String category;
    @NotNull
    private String publisher;
    @NotNull
    private int pegi;
    @NotNull
    private String pegiContent;


    public Juego() {

    }

    public Juego(Long id, String title, String platform, int year, double price1, int discount, double price2, String category, String publisher, int pegi, String pegiContent) {

        this.id = id;
        this.title = title;
        this.platform = platform;
        this.year = year;
        this.price1 = price1;
        this.discount = discount;
        this.price2 = price2;
        this.category = category;
        this.publisher = publisher;
        this.pegi = pegi;
        this.pegiContent = pegiContent;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Juego{" + "id=" + id + ", title='" + title + '\'' + ", platform='" + platform + '\'' + ", year='" + year + '\'' + ", price1='" + price1 + '\'' + ", discount='" + discount + '\'' + ", price2='" + price2 + '\'' + ", category='" + category + '\'' + ", publisher='" + publisher + '\'' + ", pegi='" + pegi + '\'' + ", pegiContent='" + pegiContent + '\'' + '}';
    }


    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


    public double getPrice1() {
        return price1;
    }

    public void setPrice1(double price1) {
        this.price1 = price1;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public double getPrice2() {
        return price2;
    }

    public void setPrice2(double price2) {
        this.price2 = price2;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPegi() {
        return pegi;
    }

    public void setPegi(int pegi) {
        this.pegi = pegi;
    }

    public String getPegiContent() {
        return pegiContent;
    }

    public void setPegiContent(String pegiContent) {
        this.pegiContent = pegiContent;
    }
}












