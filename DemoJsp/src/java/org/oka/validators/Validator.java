package org.oka.validators;

import javax.servlet.http.HttpServletRequest;

public class Validator {

   

    public boolean validateKullanici(String userName,
            String firstName,
            String lastName,
            String passWord,
            String email,
            HttpServletRequest request) {

        boolean errorFlag = false;

        String userNameError;
        String firstNameError;
        String lastNameError;
        String passWordError;
        String emailError;
       

        if (userName == null
                || userName.equals("")
                || userName.length() > 8) {
            errorFlag = true;
            userNameError = "kullanici adi 8 karakterden uzun olamaz";
            request.setAttribute("userNameError", userNameError);
           
        }

        if (firstName == null
                || firstName.equals("")
                || firstName.length() > 12) {
            errorFlag = true;
            firstNameError = "Isim 12 karakterden uzun olamaz";
            request.setAttribute("firstNameError", firstNameError);
        }
        if (lastName == null
                || lastName.equals("")
                || lastName.length() > 12) {
            errorFlag = true;
            lastNameError = "soyad 12 karakterden uzun olamaz";
            request.setAttribute("lastNameError", lastNameError);
        }
        if (passWord == null
                || passWord.equals("")
                || passWord.length() > 8) {
            errorFlag = true;
            passWordError = "Sifre 8 karakterden uzun olamaz";
            request.setAttribute("passWordError", passWordError);
        }

        if (email == null
                || email.equals("")
                || !email.contains("@")) {
            errorFlag = true;
            emailError = "Email Hatali";
            request.setAttribute("emailError", emailError);
        }

        return errorFlag;
    }

}
