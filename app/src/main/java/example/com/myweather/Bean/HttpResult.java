package example.com.myweather.Bean;

/**
 * Created by 20256473 on 2017/3/9.
 */
public class HttpResult<T>{
    public int resultcode;
    public String reason;


    public T result;
    public int error_code;
}