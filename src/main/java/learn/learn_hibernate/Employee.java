package learn.learn_hibernate;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;  
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;  
  
@Entity  
@Table(name= "emp500" ,uniqueConstraints = {
    @UniqueConstraint (columnNames = "ID")
})   
public class Employee implements Serializable{    
  
    private static final long serialVersionUID = -1798070786993154676L;

    @Id   
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private int id;    

    @Column(name="FNAME", unique = false, nullable = false, length = 100)
    private String firstName;
    
    @Column(name = "LNAME", unique = false, nullable = false, length = 100)
    private String lastName;

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
}   