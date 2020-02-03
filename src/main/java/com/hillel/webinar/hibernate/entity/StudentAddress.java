package com.hillel.webinar.hibernate.entity;


import javax.persistence.Column;
import javax.persistence.Embeddable;
/*
Чтобы использовать в аннотациях класс, для которого нет соответствующей в БД таблицы, используются аннотации @Embedded
 и @Embeddable.
Embeddable - это встраеваемый класс. Такую аннотацию дают, когда связь с классом существует, и в структуре классов
следует показать это, но таблицы для этого класса нет, а значит, связи @One-To-Many или @One-To-One использовать
 не получится.
 В БД это будет соответствовать УЖЕ СУЩЕСТВУЮЩЕЙ ТАБЛИЦЕ students с новыми полями country, city и address/
 */
@Embeddable//"встраиваемый" позволяет указать класс, экземпляры которого хранятся как неотъемлемая часть объекта-владельца
public class StudentAddress {

    @Column(name="country")
    private String country;

    @Column(name="city")
    private String city;

    @Column(name="address")
    private String address;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "StudentAddress{" +
                "country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
