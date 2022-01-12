package org.factoriaf5.bftp2project4grupo4.repositories;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "juegos")
public class Juego implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private String coverImage;
    @NotNull
    private String platform;
    @NotNull
    private int year;
    private int discount;
    @NotNull
    private String category;
    @NotNull
    private String publisher;
    @NotNull
    private int pegi;
    private String pegiContent;
    private double price;
    private double priceWithDiscount;


    public Juego() {

    }

    public Juego(String title, String coverImage, String platform, int year, double price, int discount, double priceWithDiscount, String category, String publisher, int pegi, String pegiContent) {

        this.title = title;
        this.coverImage = coverImage;
        this.platform = platform;
        this.year = year;
        this.price = price;
        this.discount = discount;
        this.priceWithDiscount = priceWithDiscount;
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
        return "Juego{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", coverImage='" + coverImage + '\'' +
                ", platform='" + platform + '\'' +
                ", year='" + year + '\'' +
                ", price='" + price + '\'' +
                ", discount='" + discount + '\'' +
                ", priceWithDiscount='" + priceWithDiscount + '\'' +
                ", category='" + category + '\'' +
                ", publisher='" + publisher + '\'' +
                ", pegi='" + pegi + '\'' +
                ", pegiContent='" + pegiContent + '\'' +
                '}';
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
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


    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Juego juego = (Juego) o;
        return Objects.equals(id, juego.id) && Objects.equals(title, juego.title) && Objects.equals(coverImage, juego.coverImage) && Objects.equals(platform, juego.platform) && Objects.equals(year, juego.year) && Objects.equals(price, juego.price) && Objects.equals(discount, juego.discount) && Objects.equals(priceWithDiscount, juego.priceWithDiscount) && Objects.equals(category, juego.category) && Objects.equals(publisher, juego.publisher) && Objects.equals(pegi, juego.pegi) && Objects.equals(juego.pegiContent, juego.pegiContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, coverImage, title, platform, year, price, discount, price, category, publisher, pegi, pegiContent);
    }


    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public double getPriceWithDiscount() {
        return this.priceWithDiscount;
    }

    public void setPriceWithDiscount(double price, double discount) {
        this.priceWithDiscount = priceWithDiscount;
    }

    /*public double getPriceWithDiscount() {
        return this.price * (1 - this.discount / 100);
    }

    public void setPriceWithDiscount(double price, double discount) {
        this.priceWithDiscount = price * (1 - this.discount / 100);
    }*/
}




















