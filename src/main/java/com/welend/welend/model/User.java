package com.welend.welend.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import java.math.BigInteger;
import java.util.Date;

@Getter
@Setter
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "birth_date")
    private String birthDate;

    @Column(name = "area")
    private String area;

    @Column(name = "street")
    private String street;

    @Column(name = "governorate")
    private String governorate;

    @Column(name = "national_id")
    private String nationalId;

    @Column(name = "nid_serial_number")
    private String nidSerialNumber;

    @Column(name = "gender")
    private String gender;

    @Column(name = "religion")
    private String religion;

    @Column(name = "spouse_name")
    private String spouseName;

    @Column(name = "profession_1")
    private String profession1;

    @Column(name = "profession_2")
    private String profession2;

    @Column(name = "release_date")
    private Date releaseDate;

    @Column(name = "marital_status")
    private String maritalStatus;


    //    business data
    @Column(name = "company_name")
    private String companyName;

    @Column(name = "year_started_operations")
    private String yearStartedOperations;

    @Column(name = "company_industry")
    private String companyIndustry;

    @Column(name = "last_year_sales")
    private String lastYearSales;

    @Column(name = "number_employees")
    private int numberEmployees;

    @Column(name = "commercial_registration_date")
    private Date commercialRegistrationDate;

    @Column(name = "tax_registration_number")
    private String taxRegistrationNumber;

    @Column(name = "tax_card_number")
    private String taxCardNumber;

    @Column(name = "owner_nationality")
    private String ownerNationality;

    @Column(name = "score")
    private double score;

    @Column(name = "website")
    private String website;
}
