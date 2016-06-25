package ws;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

/**
 * Created by lx on 2016/6/25.
 */
//发布ws的注解
@WebService
public class WsServiceTest {

    /**
     * 发布的方法
     * @return
     */
    public String HelloWorld(){
        return "helloWorld";
    }

    /**
     * 最简单的ws发布
     * @param args
     */
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8080/ws.WsServiceTest",new WsServiceTest());
        System.out.println("success");
    }
}
