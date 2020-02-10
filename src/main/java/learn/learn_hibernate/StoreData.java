package learn.learn_hibernate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.hibernate.Session;

public class StoreData {

    private static int empid = 0;

    public static void main(String[] args) {

        // addData();

        // getData(empid);

        addBLOBData();

        getBLOBData();

        HibernateUtil.shutdown();

    }

    public static void addData() {
        // Opening Session to Add
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        // Adding a new Employee
        Employee e1 = new Employee();

        e1.setFirstName("Ravi");
        e1.setLastName("Balg");

        session.save(e1);

        empid = e1.getId();
        session.getTransaction().commit();
    }

    public static void getData(int empid) {
        // Otherwise called as load()

        // Opening session to test load() methods
        Session sessionone = HibernateUtil.getSessionFactory().openSession();
        sessionone.beginTransaction();

        Employee emp = new Employee();
        sessionone.load(emp, empid);

        System.out.println(emp.getFirstName() + " ~ " + emp.getLastName());

        sessionone.getTransaction().commit();

    }

    public static void addBLOBData() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        File file = new File("src\\main\\java\\learn\\learn_hibernate\\W-text.jpg");
        byte[] imageData = new byte[(int) file.length()];

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(imageData);
            fileInputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        Employee employee = new Employee();
        employee.setFirstName("Imagetest");
        employee.setLastName("W-Text");
        employee.setImage(imageData);

        session.save(employee);

        empid = employee.getId();

        session.getTransaction().commit();

    }

    private static void getBLOBData() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Employee employee = session.get(Employee.class, empid);

        byte[] newImg = employee.getImage();

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(
                    "src\\main\\java\\learn\\learn_hibernate\\getW-text.jpg");
            fileOutputStream.write(newImg);
            fileOutputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        session.getTransaction().commit();

    }

}