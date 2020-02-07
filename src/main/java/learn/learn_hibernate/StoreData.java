package learn.learn_hibernate;

import org.hibernate.Session;     
  
public class StoreData {    

    private static int empid = 0; 
    public static void main(String[] args) {    
            
        
        addData();

        getData(empid);

        HibernateUtil.shutdown();
        

    }    

    public static void addData(){
        //Opening Session to Add
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
                
        //Adding a new Employee
        Employee e1=new Employee();  
        
        e1.setFirstName("Ravi");    
        e1.setLastName("Balg");    
            
        session.save(e1);  

        empid = e1.getId();
        session.getTransaction().commit();
    }

    public static void getData(int empid){
        //Otherwise called as load()

        //Opening session to test load() methods
        Session sessionone = HibernateUtil.getSessionFactory().openSession();
        sessionone.beginTransaction();

        Employee emp = new Employee();
        sessionone.load(emp, empid);

        System.out.println(emp.getFirstName() + " ~ " + emp.getLastName());

        sessionone.getTransaction().commit();


    }

}   