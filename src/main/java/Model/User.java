package Model;

import java.util.Date;

public class User {
    private Long id;
    private String email;
    private String password;

//    national id data
    private String firstName;
    private String fullName;
    private String birthDate;
    private String area;
    private String street;
    private String governorate;
    private String nationalId;
    private String nidSerialNumber;
    private String gender;
    private String religion;
    private String spouseName;
    private String profession1;
    private String profession2;
    private Date   releaseDate;
    private String maritalStatus;


    //business data
    private String companyName;
    private String yearStartedOperations;
    private String companyIndustry;
    private String lastYearSales;
    private int    numberEmployees;
    private Date   commercialRegistrationDate;
    private String taxRegistrationNumber;
    private String taxCardNumber;
    private String ownerNationality;
    private double score;
    private String website;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getGovernorate() {
        return governorate;
    }

    public void setGovernorate(String governorate) {
        this.governorate = governorate;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getNidSerialNumber() {
        return nidSerialNumber;
    }

    public void setNidSerialNumber(String nidSerialNumber) {
        this.nidSerialNumber = nidSerialNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getSpouseName() {
        return spouseName;
    }

    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
    }

    public String getProfession1() {
        return profession1;
    }

    public void setProfession1(String profession1) {
        this.profession1 = profession1;
    }

    public String getProfession2() {
        return profession2;
    }

    public void setProfession2(String profession2) {
        this.profession2 = profession2;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getYearStartedOperations() {
        return yearStartedOperations;
    }

    public void setYearStartedOperations(String yearStartedOperations) {
        this.yearStartedOperations = yearStartedOperations;
    }

    public String getCompanyIndustry() {
        return companyIndustry;
    }

    public void setCompanyIndustry(String companyIndustry) {
        this.companyIndustry = companyIndustry;
    }

    public String getLastYearSales() {
        return lastYearSales;
    }

    public void setLastYearSales(String lastYearSales) {
        this.lastYearSales = lastYearSales;
    }

    public int getNumberEmployees() {
        return numberEmployees;
    }

    public void setNumberEmployees(int numberEmployees) {
        this.numberEmployees = numberEmployees;
    }

    public Date getCommercialRegistrationDate() {
        return commercialRegistrationDate;
    }

    public void setCommercialRegistrationDate(Date commercialRegistrationDate) {
        this.commercialRegistrationDate = commercialRegistrationDate;
    }

    public String getTaxRegistrationNumber() {
        return taxRegistrationNumber;
    }

    public void setTaxRegistrationNumber(String taxRegistrationNumber) {
        this.taxRegistrationNumber = taxRegistrationNumber;
    }

    public String getTaxCardNumber() {
        return taxCardNumber;
    }

    public void setTaxCardNumber(String taxCardNumber) {
        this.taxCardNumber = taxCardNumber;
    }

    public String getOwnerNationality() {
        return ownerNationality;
    }

    public void setOwnerNationality(String ownerNationality) {
        this.ownerNationality = ownerNationality;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
