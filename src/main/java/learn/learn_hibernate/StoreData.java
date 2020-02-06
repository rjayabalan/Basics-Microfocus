package learn.learn_hibernate;

import org.hibernate.Session;     
  
public class StoreData {    
public static void main(String[] args) {    
        
    
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
            
    //Adding a new Employee
    Employee e1=new Employee();  
      
    e1.setId(101);    
    e1.setFirstName("Gaurav");    
    e1.setLastName("Chawla");    
        
    session.save(e1);  
    session.getTransaction().commit();
    HibernateUtil.shutdown();
        
}    
}   