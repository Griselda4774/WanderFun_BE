package com.project2.wanderfun.domain.model.users;

import com.project2.wanderfun.domain.model.addresses.Address;
import com.project2.wanderfun.domain.model.images.Image;

import java.util.Date;

public class User{
    private Long id;
    private UserRole role;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private boolean isVerified;
    private boolean isCreatedProfile;
    private Image avatarImage;
    private Date dateOfBirth;
    private String gender;
    private String phoneNumber;
    private int point;
    private Address address;

    public User() {
    }
}
