/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modul;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author nur13
 */
public class User {
    
    private int id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String address;
    private String profileDesc;
    private Date birthDate;
    private Country nationality;
    private Country birthPlace;
    private List<UserSkill> skills;

    public User() {
    }

    public User(int id) {
        this.id = id;
    }

//    public User(int id, String name, String surname, String email,String profileDesc, Date birthDate, Country nationality, Country birthPlace) {
//         this.id = id;
//        this.name = name;
//        this.surname = surname;
//        this.email = email;
//        this.profileDesc = profileDesc;
//        this.birthDate = birthDate;
//        this.nationality = nationality;
//        this.birthPlace = birthPlace;
//        this.skills = skills;
//    }

    public User(int id, String name, String surname, String email, String phone, String address, String profileDesc, Date birthDate, Country nationality, Country birthPlace) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.profileDesc = profileDesc;
        this.birthDate = birthDate;
        this.nationality = nationality;
        this.birthPlace = birthPlace;
       // this.skills = skills;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    

    public String getProfileDesc() {
        return profileDesc;
    }

    public void setProfileDesc(String profileDesc) {
        this.profileDesc = profileDesc;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Country getNationality() {
        return nationality;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    public Country getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(Country birthPlace) {
        this.birthPlace = birthPlace;
    }

    public List<UserSkill> getSkills() {
        return skills;
    }

    public void setSkills(List<UserSkill> skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", surname=" + surname + ", email=" + email + ", phone=" + phone + ", address=" + address + ", profileDesc=" + profileDesc + ", birthDate=" + birthDate + ", nationality=" + nationality + ", birthPlace=" + birthPlace + ", skills=" + skills + '}';
    }

    
     

   

    
    
    
}
