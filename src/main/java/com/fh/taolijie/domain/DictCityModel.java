package com.fh.taolijie.domain;

public class DictCityModel {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dict_city.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dict_city.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dict_city.province_id
     *
     * @mbggenerated
     */
    private Integer provinceId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dict_city.id
     *
     * @return the value of dict_city.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dict_city.id
     *
     * @param id the value for dict_city.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dict_city.name
     *
     * @return the value of dict_city.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dict_city.name
     *
     * @param name the value for dict_city.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dict_city.province_id
     *
     * @return the value of dict_city.province_id
     *
     * @mbggenerated
     */
    public Integer getProvinceId() {
        return provinceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dict_city.province_id
     *
     * @param provinceId the value for dict_city.province_id
     *
     * @mbggenerated
     */
    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }
}