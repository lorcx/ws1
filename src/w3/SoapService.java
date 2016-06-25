package w3;

import javax.xml.ws.Endpoint;

/**
 * 发布ws
 * Created by lx on 2016/6/25.
 */
public class SoapService {
    public static void main(String[] args) {
       Endpoint.publish("http://localhost:8080/ws3.Hello",new HelloImpl());
    }
}
