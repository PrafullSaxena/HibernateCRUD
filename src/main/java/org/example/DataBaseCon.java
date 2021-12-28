package org.example;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class DataBaseCon {

        Scanner sc = new Scanner(System.in);
        Configuration con = null;
        StandardServiceRegistry reg = null;
        SessionFactory sf = null;

    void sessionSetup(){
        File f = new File("/Users/prafullsaxena/Desktop/Projects/HibernateCRUD/src/main/java/org/example/hibernate.cfg.xml");
        con = new Configuration().configure(f).addAnnotatedClass(Student.class);
        reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
        sf = con.buildSessionFactory(reg);
    }

    void create(int rollNo, int marks, String name){
//        System.out.println("Working!!");

        Student stu = new Student();
        stu.setRollNo(rollNo);
        stu.setMarks(marks);
        stu.setName(name);
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        session.save(stu);

        tx.commit();
        session.close();


    }

    void retrieve(){
//        System.out.println("Working!!");

        System.out.print("Please Enter Roll number to Get the Details (type 0 for all results) - ");
        int rollNo = sc.nextInt();

        if(rollNo != 0 ) retrieveByRollNo(rollNo);
        else retrieveAll();

    }


    void retrieveAll(){
        String query = "from Student";

        Session session = sf.openSession();
        Query q = session.createQuery(query);

        try{
            List<Student> students = q.list();
            for(Student st : students){
                System.out.println(st.getRollNo()+" | "+st.getMarks()+" | "+st.getName());
            }
        }catch (Exception e){
            System.out.println("No Values in the database");
        }

        System.out.println("RollNumber | Marks | Name");



        session.close();
    }

    void retrieveByRollNo(int rollNo){
        Session session = sf.openSession();

        try {
            Student st = (Student)session.get(Student.class, rollNo);
            System.out.println("Name - "+st.getName()+" | RollNumber - "+st.getRollNo()+" | Marks - "+st.getMarks());
        }catch (Exception NullPointerException){
            System.out.println("Didn't find this value!!");
        }

        session.close();

    }



    void update(int rollNo){
//        System.out.println("Got - "+rollNo+" !!");

        Session session = sf.openSession();
        session.beginTransaction();

        try{

            Student st = (Student) session.get(Student.class, rollNo);
            System.out.print("Please enter new marks for "+st.getName()+" :: ");
            int marks = sc.nextInt();
            st.setMarks(marks);
            session.update(st);


        }catch (Exception NullPointerException){
            System.out.println("Didn't find the Element!");
        }

        session.getTransaction().commit();
        session.close();

    }

    void delete(int rollNo){
//        System.out.println("Got - "+rollNo+" !!");

        Session session = sf.openSession();
        session.beginTransaction();

        try{

            Student st = (Student) session.get(Student.class, rollNo);
            session.delete(st);


        }catch (Exception NullPointerException){
            System.out.println("Didn't find the Element!");
        }

        session.getTransaction().commit();
        session.close();

    }

}
