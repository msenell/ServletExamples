/*
    Veritabanı üzerinde CRUD işlemleri yapacak olan sınıf.
 */
package com.org.servletexamples;


import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class DatabaseFunctions
{
    private SessionFactory factory;
    public DatabaseFunctions(SessionFactory factory)
    {
        this.factory = factory;
    }
    /*
        public Integer addEmployee(String fname, String lname, int age):
        Tanım: 
            Kendisine parametre olarak gönderilen değerler ile bir Employee objesi oluşturan ve
            bu objeyi veritabanına yeni bir kayıt olarak ekleyen method.
        Parametreler:
            -fName: Employee'nin isim bilgisi.
            -lName: Employee'nin soyadı bilgisi.
            -salary: Employee'nin yaş bilgisi.
        Geri dönüş:
            -Integer: Eklenecek Employee'e atanan id bilgisi.
    */
    public Integer addEmployee(String fName, String lName, int age)
    {
        Session session = factory.openSession();
        Transaction tx = null;
        Integer employeeID = null;
      
        try
        {
            tx = session.beginTransaction();
            Employee employee = new Employee(fName, lName, age);
            employeeID = (Integer) session.save(employee); 
            tx.commit();
        }catch(HibernateException e)
        {
            if (tx!=null) tx.rollback();
            e.printStackTrace(); 
        }finally
        {
            session.close(); 
        }
        return employeeID;
    }
        
    /*
        public void listEmployees():
        Tanım:
            EMPLOYEE tablosunda bulunan kayıtları Java ortamına Employee objeleri halinde çeken ve
            bunları geri döndüren method.
    */
    public List listEmployees( )
    {
        Session session = factory.openSession();
        Transaction tx = null;
        List employees = null;
      
        try
        {
            tx = session.beginTransaction();
            employees = session.createQuery("FROM com.org.servletexamples.Employee").list();
            tx.commit();
        }catch(HibernateException e)
        {
            if (tx!=null) tx.rollback();
            e.printStackTrace(); 
        }finally
        {
             if (session != null && session.isOpen()) {
             session.close();
         }
        }
        return employees;
    }
    
    /*
        public void updateEmployee(Integer EmployeeID, int age ):
        Tanım:
            Id'si gönderilen Employee'nin yaşını güncelleyen method.
        Parametreler:
            -EmployeeID: Maaş bilgisi güncellenecek Employee'nin id'si.
            -age: Employee'nin yeni yaş değeri.
    */
    public void updateEmployee(Integer EmployeeID, int age )
    {
        Session session = factory.openSession();
        Transaction tx = null;
      
        try
        {
            tx = session.beginTransaction();
            Employee employee = (Employee)session.get(Employee.class, EmployeeID); 
            employee.setAge( age );
            session.update(employee); 
            tx.commit();
        }catch(HibernateException e)
        {
            if (tx!=null) tx.rollback();
            e.printStackTrace(); 
        }finally
        {
            session.close(); 
        }
    }
    
    /*
        public void deleteEmployee(Integer EmployeeID):
        Tanım:
            ID bilgisi gönderilen Employee'nin kaydını veritabanından silen method.
        Parametreler:
            -EmployeeID: Kaydı silinecek Employee'nin ID bilgisi.
    */
    public void deleteEmployee(Integer EmployeeID)
    {
        Session session = factory.openSession();
        Transaction tx = null;
      
        try
        {
            tx = session.beginTransaction();
            Employee employee = (Employee)session.get(Employee.class, EmployeeID); 
            session.delete(employee); 
            tx.commit();
        }catch(HibernateException e)
        {
            if (tx!=null) tx.rollback();
            e.printStackTrace(); 
        }finally
        {
            session.close(); 
        }
    }
}
