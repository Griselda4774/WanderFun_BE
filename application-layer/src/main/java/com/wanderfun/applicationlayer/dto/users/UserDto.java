package com.wanderfun.applicationlayer.dto.users;

import com.wanderfun.applicationlayer.dto.addresses.AddressDto;
import com.wanderfun.applicationlayer.dto.images.ImageDto;

import java.time.LocalDate;

public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private boolean isCreatedProfile;
    private ImageDto avatarImage;
    private LocalDate dateOfBirth;
    private String gender;
    private String phoneNumber;
    private int point;
    private int checkInCount;
    private int placeCheckInCount;
    private AddressDto address;
    private Long accountId;

    public UserDto() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public boolean isCreatedProfile() {
        return isCreatedProfile;
    }

    public void setCreatedProfile(boolean createdProfile) {
        isCreatedProfile = createdProfile;
    }

    public ImageDto getAvatarImage() {
        return avatarImage;
    }

    public void setAvatarImage(ImageDto avatarImage) {
        this.avatarImage = avatarImage;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getCheckInCount() {
        return checkInCount;
    }

    public void setCheckInCount(int checkInCount) {
        this.checkInCount = checkInCount;
    }

    public int getPlaceCheckInCount() {
        return placeCheckInCount;
    }

    public void setPlaceCheckInCount(int placeCheckInCount) {
        this.placeCheckInCount = placeCheckInCount;
    }

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
}
