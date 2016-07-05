package weather;

import weather.wscode.ArrayOfString;
import weather.wscode.WeatherWebService;
import weather.wscode.WeatherWebServiceSoap;

/**
 * 查询城市天气
 * Created by dell on 2016/7/5.
 */
public class weatherTest {
    public static void main(String[] args) {
        WeatherWebService weatherWebService = new WeatherWebService();
        WeatherWebServiceSoap soap = weatherWebService.getWeatherWebServiceSoap();
        ArrayOfString result = soap.getWeatherbyCityName("北京");
        System.out.println(result.getString().toString());
    }
}
