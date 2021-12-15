package org.factoriaf5.bftp2project4grupo4.repositories;

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
    private String title;
    private String platform;
    private int year;
    private double price1;
    private int discount;
    private double price2;
    private String category;
    private String publisher;
    private int pegi;
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

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }










}












