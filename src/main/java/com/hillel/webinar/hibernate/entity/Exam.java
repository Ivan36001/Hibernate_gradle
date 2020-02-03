package com.hillel.webinar.hibernate.entity;


import javax.persistence.*;
@Entity
@Table(name="exams")//указываем название таблицы в БД
public class Exam {

    @Id//помечаем поле как первичный ключ
    @GeneratedValue(strategy = GenerationType.IDENTITY)//автоматическое инкрементирование поля id
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)//fetch = FetchType.LAZY-данные будут загружаться только после запроса (у множест-
    //ва экзаменов один студент)
    @JoinColumn(name="student_id")//указываем что столбец является внешним ключем (связан с первичным ключем другой
    // таблицы)
    private Student student;//указываем тип данных поля а не его id (как в БД)

    @ManyToOne(fetch = FetchType.LAZY)//fetch = FetchType.LAZY-данные будут загружаться только после запроса
    @JoinColumn(name="subject_id")//указываем что столбец является внешним ключем
    private  Subject subject;//указываем тип данных поля а не его id (как в БД)

    @Column(name="grade")
    private int grade;//поле оценка

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", student=" + student +
                ", subject=" + subject +
                ", grade=" + grade +
                '}';
    }
}
