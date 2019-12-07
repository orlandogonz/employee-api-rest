
package com.orlando.java.web;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author orlando
 * [www.github.com/orlandogonz]
 * [www.linkedin.com/in/orlandogonz]
 * [orlandogonz.dev@gmail.com]
 */

@XmlRootElement//Asigna una clase o un tipo de enumeración a un elemento XML.

//Clase Employee
public class Employee {
    
    //Atributos de la clase Employee
    private int id;
    private String firstName;
    private String lastName;
    private String title;
    private String city;
    private String officePhone;
    private String cellPhone;
    private String email;
    private String picture;
    private String department;
    private Employee manager;
    private int reportCount;
    
    //Constructor de la clase Employee
    public Employee(){
        super ();
    }
    
    //Getters y Setters de la clase Employee
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public int getReportCount() {
        return reportCount;
    }

    public void setReportCount(int reportCount) {
        this.reportCount = reportCount;
    }

    
    
    
            
    
}
