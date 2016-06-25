package ws1;

import ws1.client.HelloService;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.xml.namespace.QName;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 * 客户端调用
 * Created by lx on 2016/6/25.
 */
public class SoapClient {
    public static void main(String[] args) throws MalformedURLException, ParseException {
        QName qName = new QName("http://ws1/","HelloService");
        HelloService helloService = new HelloService(new URL("http://127.0.0.1:8080/ws1.Hello?wsdl"),qName);
        Hello hello = helloService.getPort(Hello.class);

        hello.printContext();

        System.out.println("---------------------------------------------");

        Customer customer = new Customer();
        customer.setName("why");
        DataSource ds = hello.selectCustomerByName(customer).getImageData().getDataSource();
        String attachmentMimeType = ds.getContentType();
        System.out.println(attachmentMimeType);
        try {
            InputStream is = ds.getInputStream();
            OutputStream os = new FileOutputStream("f:\\why_temp.jpg");
            byte[] b = new byte[1024];
            int c;
            while((c = is.read()) != -1){
                os.write(b,0,c);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("--------------------------------------------------");
        Customer c1 = new Customer();
        c1.setId(1);
        c1.setName("why");
        GregorianCalendar calendar = (GregorianCalendar) GregorianCalendar.getInstance();
//        calendar.setTime(new SimpleDateFormat("yyyy-MM-dd").parse("1985-10-07"));
        c1.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse("1985-10-07"));
//        try {
//            c1.setBirthday(DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar));
//        } catch (DatatypeConfigurationException e) {
//            e.printStackTrace();
//        }
        c1.setImageData(new DataHandler(new FileDataSource("f:\\c1.jpg")));



        Customer c2 = new Customer();
        c2.setId(1);
        c2.setName("abc");
        calendar.setTime(new SimpleDateFormat("yyyy-MM-dd").parse("1985-10-07"));
        c2.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse("1985-10-07"));
//        try {
//            c1.setBirthday(DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar).);
//        } catch (DatatypeConfigurationException e) {
//            e.printStackTrace();
//        }
        c2.setImageData(new DataHandler(new FileDataSource("f:\\c2.jpg")));
        Customer c = hello.selectMaxAgeCustomer(c1,c2);
        System.out.println(c.getName());

    }
}
