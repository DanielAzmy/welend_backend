package com.welend.welend.model;

import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Table()
public class User {
    private int id;
    private String email;
    private String password;

//    national id data
    private String first_name;
    private String full_name;
    private String birth_date;
    private String area;
    private String street;
    private String governorate;
    private String national_id;
    private String nid_serial_number;
    private String gender;
    private String religion;
    private String spouse_name;
    private String profession_1;
    private String profession_2;
    private Date   release_date;
    private String marital_status;

//    business data
    private String company_name;
    private String Year_started_operations;
    private String company_industry;
    private String last_year_sales;
    private int number_employees;
    private Date commercial_registration_date;
    private String tax_registration_number;
    private String tax_card_number;
    private String owner_nationality;
    private double score;
    private String website;

}
