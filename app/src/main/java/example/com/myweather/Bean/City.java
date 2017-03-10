package example.com.myweather.Bean;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by 20256473 on 2017/3/9.
 */

public class City extends RealmObject {


    @PrimaryKey
    private Integer id;
    private  String City_name;
    private String Province_name;
    private  String District_name;
    private Integer Code;

    public City(Integer id, String city_name, String province_name, String district_name, Integer code) {
        this.id = id;
        City_name = city_name;
        Province_name = province_name;
        District_name = district_name;
        Code = code;
    }

    public Integer getCode() {
        return Code;
    }

    public void setCode(Integer code) {
        Code = code;
    }



    public City(Integer id, String city_name, String province_name, String district_name) {
        this.id = id;
        City_name = city_name;
        Province_name = province_name;
        District_name = district_name;

    }
    public String getProvince_name() {
        return Province_name;
    }

    public void setProvince_name(String province_name) {
        Province_name = province_name;
    }

    public String getDistrict_name() {
        return District_name;
    }

    public void setDistrict_name(String district_name) {
        District_name = district_name;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity_name() {
        return City_name;
    }

    public void setCity_name(String city_name) {
        City_name = city_name;
    }

    public City() {
    }




}

