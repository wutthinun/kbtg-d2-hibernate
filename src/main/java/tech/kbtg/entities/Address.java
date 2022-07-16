package tech.kbtg.entities;

import javax.persistence.*;

@Entity
@Table(name = "Address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "province")
    private String province;
    @Column(name = "district")
    private String district;
    @Column(name = "sub_district")
    private String subDistrict;
    @Column(name = "house_no")
    private String houseNo;
    @Column(name = "zip_code")
    private String zipCode;

    public Address() {
    }

    public Address(String province, String district, String subDistrict, String houseNo, String zipCode) {
        this.province = province;
        this.district = district;
        this.subDistrict = subDistrict;
        this.houseNo = houseNo;
        this.zipCode = zipCode;
    }

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getSubDistrict() {
        return subDistrict;
    }

    public void setSubDistrict(String subDistrict) {
        this.subDistrict = subDistrict;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", province='" + province + '\'' +
                ", district='" + district + '\'' +
                ", subDistrict='" + subDistrict + '\'' +
                ", houseNo='" + houseNo + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}
