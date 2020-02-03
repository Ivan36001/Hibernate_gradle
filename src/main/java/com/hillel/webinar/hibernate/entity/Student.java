package com.hillel.webinar.hibernate.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity//помечаем класс который должен быть связан с таблицей в БД
@Table(name = "students")//указываем название таблицы в БД
public class Student {

    @Id//помечаем поле как первичный ключ
    @GeneratedValue(strategy = GenerationType.IDENTITY)//автоматическое инкрементирование поля id
    @Column(name = "id")//указываем что поле класса id должно быть связано с колонкой id в БД
    private int id;

    @Column(name = "firstName")//указываем что поле класса firstname должно быть связано с колонкой firstname в БД
    private String firstName;

    @Column(name = "lastName")//указываем что поле класса lastname должно быть связано с колонкой lastname в БД
    private String lastName;

    @Column(name = "age")//указываем что поле класса age должно быть связано с колонкой age в БД
    private int age;

    @Embedded//используется для указания постоянного поля или свойства объекта, значение которого является экземпляром
    // встраиваемого класса
    private StudentAddress studentAddress;

    @OneToMany(fetch =FetchType.LAZY, mappedBy = "student")//LAZY-пока не обратимся к сету гибернейт не загрузит его из БД,
    //mappedBy-указывает по какому полю в классе Exam мы выполняем маппинг One to Many (у студента может быть множество
    //экзаменов)
    private Set<Exam> exams;//используем множество всех экзаменов котрые должен сдать студент так как название всех
    // экзаменов уникально

    public StudentAddress getStudentAddress() {
        return studentAddress;
    }

    public void setStudentAddress(StudentAddress studentAddress) {
        this.studentAddress = studentAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Set<Exam> getExams() {
        return exams;
    }

    public void setExams(Set<Exam> exams) {
        this.exams = exams;
    }

    @Override
    public String toString() {//переопределяем метод toString для вывода на печать наших объектов student
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }

}
/*
    JPA(java Persistens API)-спецификация для получения, записи и использования данных реляционной базы данных посредст-
    вом классов и объектов программы.(интерфейсы которые надо реализовать, одна из реализаций Hibernate).
    Java приложение----> Hibernate native API (или JPA)----->Hibernate----> JDBC(библиотека для связи с БД)----->БД
    Основные объекты Hibernate:
    Configuration(указываем путь, сервер, порт, название БД пользователь и пароль hibernate.cfg.xml)----->SessionFactory
 (хранит настройки БД и Hibernate создается один раз)---->Session (физическое соединение с БД, выполнение CRUD)---->JDBC
                                                              |
                                                              |
                         Transaction(любой запрос к БД желательно выполнять в пределах одной транзакции)
     Какие бывают запросы:
     -HQL (если используем Hibernate native API) или JPQL (если используем Java Persistence API (JPA))
     -Criteria API
     -Native SQL

 */