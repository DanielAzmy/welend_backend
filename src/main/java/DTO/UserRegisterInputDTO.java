package DTO;

import java.sql.SQLException;

import java.util.regex.Pattern;

public class UserRegisterInputDTO {
    private String email;
    private String password;
    private String nidFrontSide;
    private String nidBackSide;
    private String selfie;

    public UserRegisterInputDTO() {
    }

    public UserRegisterInputDTO(String email, String password, String nidFrontSide, String nidBackSide, String selfie) {
        this.email = email;
        this.password = password;
        this.nidFrontSide = nidFrontSide;
        this.nidBackSide = nidBackSide;
        this.selfie = selfie;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNidFrontSide() {
        return nidFrontSide;
    }

    public void setNidFrontSide(String nidFrontSide) {
        this.nidFrontSide = nidFrontSide;
    }

    public String getnidBackSide() {
        return nidBackSide;
    }

    public void setnidBackSide(String nidBackSide) {
        this.nidBackSide = nidBackSide;
    }

    public String getSelfie() {
        return selfie;
    }

    public void setSelfie(String selfie) {
        this.selfie = selfie;
    }

    public void validate() throws SQLException
    {
        if(email == null || password == null || nidFrontSide == null || nidBackSide == null)
            throw new SQLException("invalid input");

    }
    public static boolean isEmailValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
}
