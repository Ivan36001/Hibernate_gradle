package com.hillel.webinar.hibernate.reports;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

//класс который выдает все данные по экзаменам для конкретного студента
public class Reports {
    public List<ExamResult> getExamResults(String firstName, String lastName, Session session) {
        String query="select new com.hillel.webinar.hibernate.reports.ExamResult(e.student.lastName, e.subject.name, e.grade) "
//создаем объект класса ExamResult и передаем в конструктор указанные ниже поля
                +"from Exam e"+//выбираем таблицу и даем ей alias-e
                " where e.student.firstName=:firstName and e.student.lastName=:lastName ";//e.student.firstName-в классе
        //Exam выбираем поле student которое ссылается (благодоря аннотациям) на поле firstName в классе student и все
        // это приравниваем параметру :firstName который будет подставлятся в метод setParameter() см. ниже

        Query hibernateQuery=session.createQuery(query);//создаем запрос
        hibernateQuery.setParameter("firstName",firstName);//setParameter() - устанавливает значение параметра
        // запроса. При передаче в данный метод экземпляра сущности выполняет неявное преобразование экземпляра в
        // его идентификатор
        hibernateQuery.setParameter("lastName",lastName);
        return hibernateQuery.getResultList();// выполнит запрос (hibernateQuery) и вернет результаты в виде коллекции

    }

}
