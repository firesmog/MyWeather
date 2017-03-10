package example.com.myweather.Bean.weatherDataBean;

import java.io.Serializable;

/**
 * Created by 20256473 on 2017/3/10.
 */

public class Weather_id implements Serializable{/*天气唯一标识*/

    public String getFa() {
        return fa;
    }

    public void setFa(String fa) {
        this.fa = fa;
    }

    public String getFb() {
        return fb;
    }

    public void setFb(String fb) {
        this.fb = fb;
    }

    public Weather_id(String fa, String fb) {
        this.fa = fa;
        this.fb = fb;
    }

    public Weather_id() {
    }

    private String fa;	/*天气标识00：晴*/
    private String fb;	/*天气标识53：霾 如果fa不等于fb，说明是组合天气*/
}
