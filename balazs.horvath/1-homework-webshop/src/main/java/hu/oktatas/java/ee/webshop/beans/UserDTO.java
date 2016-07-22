package hu.oktatas.java.ee.webshop.beans;

import hu.oktatas.java.ee.webshop.constraint.Phone;
import hu.oktatas.java.ee.webshop.constraint.Password;
import hu.oktatas.java.ee.webshop.constraint.Address;
import hu.oktatas.java.ee.webshop.constraint.Email;
import hu.oktatas.java.ee.webshop.constraint.DateOfBirth;
import java.util.Calendar;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import hu.oktatas.java.ee.webshop.constraint.Name;

@Name
@DateOfBirth
public class UserDTO {

    @NotNull
    @Size(min = 6)
    private String userName;

    @NotNull
    @Password
    private String password;

    private String firstName;

    private String lastName;

    @Address
    private String address;

    @Phone
    private String phone;

    @NotNull
    @Email
    private String email;

    private Sex sex;

    @NotNull
    @Past
    private Calendar registrationDate;

    private Calendar dateOfBirth;

    private boolean admin;

    public UserDTO(String username, String password, String email, Calendar registrationDate) {
        this.userName = username;
        this.password = password;
        this.email = email;
        this.registrationDate = registrationDate;
    }

    public UserDTO(String userName, String password, String address, String email, Calendar registrationDate) {
        this.userName = userName;
        this.password = password;
        this.address = address;
        this.email = email;
        this.registrationDate = registrationDate;
    }

    public UserDTO(String userName, String password, String address, String phone, String email, Calendar registrationDate) {
        this.userName = userName;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.registrationDate = registrationDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Calendar getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Calendar registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Calendar getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Calendar dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

}
