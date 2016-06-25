package w3;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.soap.MTOM;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

/**
 * 实现类
 * Created by lx on 2016/6/25.
 */
@WebService(serviceName = "HelloService",portName = "HelloServicePort",targetNamespace = "http://ws3/"
            ,endpointInterface = "w3.Hello")
@MTOM
public class HelloImpl implements Hello {

    //上下文环境
    @Resource
    private WebServiceContext context;

    @Override
    public void printContext() {
        MessageContext ctx = context.getMessageContext();
        Set<String> set = ctx.keySet();
        for(String key : set){
            System.out.println("{" + key + "," + ctx.get(key) + "}");
            try {
                System.out.println("key.scope=" + ctx.getScope(key));
            } catch (Exception e) {
                System.out.println(key + " is not exits");
            }
        }
    }

    @Override
    public Customer selectCustomerByName(Customer customer) {
        if("why".equals(customer.getName())){
            customer.setId(1);
            try {
                customer.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse("1993-05-20"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            customer.setImageData(new DataHandler(new FileDataSource(new File("f:" + File.separator + "why.jpg"))));
        }else{
            customer.setId(2);
            customer.setBirthday(new Date());
            customer.setImageData(new DataHandler(new FileDataSource(new File("f:" + File.separator + "origin.jpg"))));
        }
        return customer;
    }

    @Override
    public Customer selectMaxAgeCustomer(Customer c1, Customer c2) {
        try {
            //输出接收到的附加
            System.out.println("c1.getImageData().getContentType()=" + c1.getImageData().getContentType());
            InputStream is = c1.getImageData().getInputStream();
            OutputStream os = new FileOutputStream("f:\\temp1.jpg");
            int c;
            byte[] b = new byte[1024];
            while((c = is.read()) != -1){
                os.write(b,0,c);
            }
            os.close();

            System.out.println("c2.getImageData().getContentType()=" + c2.getImageData().getContentType());
            is = c2.getImageData().getInputStream();
            os = new FileOutputStream("f:\\temp2.jpg");
            while((c = is.read()) != -1){
                os.write(b,0,c);
            }
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(c1.getBirthday().compareTo(c2.getBirthday()) > 0){
            return c2;
        }else{
            return c1;
        }
    }
}
