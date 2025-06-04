package com.wanderfun.infrastructurelayer.persistence.entity.users;

import com.wanderfun.infrastructurelayer.persistence.entity.addresses.AddressEntity;
import com.wanderfun.infrastructurelayer.persistence.entity.auths.AccountEntity;
import com.wanderfun.infrastructurelayer.persistence.entity.images.ImageEntity;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "is_created_profile")
    private Boolean isCreatedProfile = false;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "avatar_image_id")
    private ImageEntity avatarImage;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "gender", length = 20)
    private String gender;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private AddressEntity address;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    private AccountEntity account;

    public UserEntity() {
    }

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

    public Boolean getCreatedProfile() {
        return isCreatedProfile;
    }

    public void setCreatedProfile(Boolean createdProfile) {
        isCreatedProfile = createdProfile;
    }

    public ImageEntity getAvatarImage() {
        return avatarImage;
    }

    public void setAvatarImage(ImageEntity avatarImage) {
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

    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }
}

