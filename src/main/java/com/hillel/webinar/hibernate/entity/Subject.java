package com.hillel.webinar.hibernate.entity;


import javax.persistence.*;

@Entity
@Table(name="subjects")
public class Subject {

     @Id//помечаем поле как первичный ключ
     @GeneratedValue(strategy = GenerationType.IDENTITY)//автоматическое инкрементирование поля id
    private int id;

     @Column(name="name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
