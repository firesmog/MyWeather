package example.com.myweather.Bean;

/**
 * Created by 20256473 on 2017/3/9.
 */

public class JsonResuly {

    private Integer id;
    private String province;
    private String city;
    private String district;
    public JsonResuly(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public JsonResuly(Integer id, String province, String city, String district) {
        this.id = id;
        this.province = province;
        this.city = city;
        this.district = district;
    }





    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }





}
