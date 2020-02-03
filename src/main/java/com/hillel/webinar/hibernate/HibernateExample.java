package com.hillel.webinar.hibernate;

import com.hillel.webinar.hibernate.entity.Exam;
import com.hillel.webinar.hibernate.entity.Student;
import com.hillel.webinar.hibernate.reports.ExamResult;
import com.hillel.webinar.hibernate.reports.Reports;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Set;

//это сама программа
public class HibernateExample {
    public static void main(String[] args) {
        SessionFactory sessionFactory=new Configuration()//создаем объект конфигурации
                .configure()//объект конфигурации считывает файл свойств hibernate.cfg.xml
                .buildSessionFactory();//создание объекта sessionFactory
        Session session=sessionFactory.openSession();// из sessionFactory получаем текущую сессию

        Transaction transaction=session.beginTransaction();//запускаем транзакцию для CRUD(четыре базовые функции,
        // используемые при работе с базами данных: создание (англ. create), чтение (read), модификация (update),
        // удаление (delete).)операций


        //СОЗДАЕМ ОБЪЕКТ student:
       // Student student=new Student();//создаем объект student
       // student.setFirstName("Tom");
       // student.setLastName("Tomson");
       // student.setAge(97);
       // session.save(student);//говорим hibernate занести новый объект в БД в рамках сессии



        //МОДИФИЦИРУЕМ ОБЪЕКТ (UPDATE):
       // Student student=session.get(Student.class,3);//получаем студента с id=3 из БД
        //student.setAge(9876);
       //session.save(student);//объект стал detached
        //Объект-сущность может находиться в одном из 3-х состояний (статусов):
         //persistent-"хранимая сущность", объект привязан к сессии и взаимодействует с БД (только в этом состоянии) все
        //изменения в рамках транзакции записываются в базу
        //detached-объект отсоединенный от сессии существующий или нет в БД
        //transient— это заполненные экземпляры классов-сущностей. Могут быть сохранены в БД. Не присоединены к сессии.
        // Поле Id не должно быть заполнено, иначе объект имеет статус detached

        //УДАЛЕНИЕ ОБЪЕКТА (DELETE):
       // Student student=session.get(Student.class,4);//получаем студента с id=1 из БД
       //session.delete(student);


         //ПОЛУЧАЕМ ОБЪЕКТ:
        //Student student=session.get(Student.class,1);//получаем студента с id=1 из БД
        //Set<Exam> exams=student.getExams();
       // Student student=session.load(Student.class,235); //то же что и get только если указать несуществующий id  в
        // консоли выпадет исключение в то время как get() просто возвращает NULL
        //System.out.println(student);//выводим студента на печать

        //HQL ЗАПРОСЫ К БД:
           // Query query=session.createQuery("from Student");//СОЗДАЕМ ОБЪЕКТ ЗАПРОСА в запросе используется
        //имя класса (!) а не наз-вание в таблице в БД. Т.о HQL оперерует классами приложения а не таблицами БД. Если
        // мы хотим загрузить в  память наши сохраняемые объекты, то мы будем использовать ключевое слово FROM
           // List<Student> students=query.getResultList();//getResultList() выполнит запрос (Query) и вернет результаты
        // в виде коллекции


            Reports reports=new Reports();//создали объект класса Reports который имеет метод getExamResults принимающий
        //в качестве параметров String firstName, String lastName, Session session
           List<ExamResult> results=reports.getExamResults("Ivan","Bogachev",session);
        System.out.println(results);
     /*Таким образом:
 1)В классе ExamResult:
   -создаем конструктор его объекта который на вход принимает String lastName, String subjectName, int grade
   -переопределяем метод toString() для последующего вывода полей объекта класса ExamResult на печать
2) В классе Reports:
 -в методе getExamResults() создаем объект класса ExamResult подставляя в конструктор поля класса Exam (при помощи
 HQL-запроса)ссылающиеся при помощи аннотаций на поля класса Student.
 -В методе getExamResults() так же описано создание запроса hibernateQuery и полей которые он принимает lastName и firstName
при помощи метода setParameter()
--В методе getExamResults() при помощи:  "return hibernateQuery.getResultList();" выполнится запрос (hibernateQuery)
который вернет результаты в виде коллекции
3) В классе программы HibernateExample (в этом классе):
-создаем объект класса Reports
-вызываем метод getExamResults() (класса Reports ) на созданном объекте который (метод) вернет результаты в виде
коллекции: List<ExamResult> result
-так как коллекция содержит объекты ExamResult с переопределенным методом toString() выводим список объектов на печать
(список экзаменов и оценок студента с указанными firstName и lastName)

      */


        transaction.commit();//метод для выполнения транзакции и для выполнения записи в БД в пределах этой транзакции


        session.close();//закрываем сессию
        sessionFactory.close();//закрываем sessionFactory


    }
}
