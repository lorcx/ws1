package axis2;

import java.util.Random;

/**
 * 简单的webService发布
 * Created by dell on 2016/7/13.
 */
public class SimpleService {
    /**
     * greeting打招呼
     * @param name
     * @return
     */
    public String getGreeting(String name){
        return name;
    }

    /**
     * 获得价格
     * @return
     */
    public int getPrice(){
        return new Random().nextInt(1000);
    }

}
