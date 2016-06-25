package ws;

import ws.client.*;

/**
 * ws客户端调用
 * Created by lx on 2016/6/25.
 */
public class wsClient {
    public static void main(String[] args) {
        ws.client.WsServiceTest hw = new WsServiceTestService().getWsServiceTestPort();
        String result = hw.helloWorld();
        System.out.println(result);
    }
}
