package w3;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.xml.namespace.QName;
import javax.xml.soap.*;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.UUID;

/**
 * 客户端调用
 * Created by lx on 2016/6/25.
 */
public class SoapClient {
    public static void main(String[] args) throws Exception {
        printContext();
        selectCustomerByName();
        selectMaxAgeCustomer();
    }

    /**
     * 调用一个无参函数
     * @throws Exception
     */
    public static void printContext()throws Exception{
        //获取soap连接工厂
        SOAPConnectionFactory factory = SOAPConnectionFactory.newInstance();
        //从soap连接工厂创建soap连接对象
        SOAPConnection connection = factory.createConnection();
        //获取消息工厂
        MessageFactory mFactory = MessageFactory.newInstance();
        //从消息工厂创建soap消息对象
        SOAPMessage message = mFactory.createMessage();
        //创建soapPart对象
        SOAPPart part = message.getSOAPPart();
        //创建soap信封对象
        SOAPEnvelope envelope = part.getEnvelope();
        //创建soapHeader对象
        SOAPHeader header = message.getSOAPHeader();
        // 创建soapBody对象
        SOAPBody body = envelope.getBody();

        //创建xml的根元素
        SOAPBodyElement bodyElement = body.addBodyElement(new QName("http://localhost:8080/ws3/","printContext","ns1"));
        //访问web服务器地址
        SOAPMessage soapMessage = connection.call(message,new URL("http://localhost:8080/ws3.Hello"));
        //控制台输出soap消息
        OutputStream os = System.out;
        soapMessage.writeTo(os);
        connection.close();
    }


    /**
     * 调用一个在soap:header中传参数的函数
     * @throws Exception
     */
    public static void selectCustomerByName()throws Exception{
        //获取soap连接工厂
        SOAPConnectionFactory factory = SOAPConnectionFactory.newInstance();
        //从soap连接工厂创建soap连接对象
        SOAPConnection connection = factory.createConnection();
        //获取消息工厂
        MessageFactory mFactory = MessageFactory.newInstance();
        //从消息工厂创建soap消息对象
        SOAPMessage message = mFactory.createMessage();
        //创建soapPart对象
        SOAPPart part = message.getSOAPPart();
        //创建soap信封对象
        SOAPEnvelope envelope = part.getEnvelope();
        //创建soapHeader对象
        SOAPHeader header = message.getSOAPHeader();
        // 创建soapBody对象
        SOAPBody body = envelope.getBody();

        //创建xml的根元素
        SOAPHeaderElement headerElementRoot = header.addHeaderElement(new QName("http://localhost:8080/ws3/","c","ns1"));
        SOAPBodyElement bodyElementRoot = body.addBodyElement(new QName("http://localhost:8080/ws3/","selectCustomerByName","ns1"));
        headerElementRoot.addChildElement(new QName("name")).addTextNode("why");
        //访问web服务器地址
        SOAPMessage soapMessage = connection.call(message,new URL("http://localhost:8080/ws3.Hello"));
        //控制台输出soap消息
        OutputStream os = System.out;
        soapMessage.writeTo(os);

        //输出soap消息中的附件
        Iterator<AttachmentPart> it = soapMessage.getAttachments();
        while(it.hasNext()){
            InputStream is = it.next().getDataHandler().getInputStream();
            byte[] b = new byte[is.available()];
            os = new FileOutputStream("f://aaa.jpg");
            while(is.read(b) != -1){
                os.write(b);
            }
            os.close();
        }
        connection.close();
    }


    /**
     * 调用一个在soap:body中传参数的函数
     * @throws Exception
     */
    public static void selectMaxAgeCustomer()throws Exception{
        //获取soap连接工厂
        SOAPConnectionFactory factory = SOAPConnectionFactory.newInstance();
        //从soap连接工厂创建soap连接对象
        SOAPConnection connection = factory.createConnection();
        //获取消息工厂
        MessageFactory mFactory = MessageFactory.newInstance();
        //从消息工厂创建soap消息对象
        SOAPMessage message = mFactory.createMessage();
        //创建soapPart对象
        SOAPPart part = message.getSOAPPart();
        //创建soap信封对象
        SOAPEnvelope envelope = part.getEnvelope();
        //创建soapHeader对象
        SOAPHeader header = message.getSOAPHeader();
        // 创建soapBody对象
        SOAPBody body = envelope.getBody();

        //设置content-Type
        MimeHeaders hd = message.getMimeHeaders();
        hd.setHeader("Content-Type","application/xop+xml;charset=utf-8;type=\"text/xml\"");

        //创建xml的根元素
        SOAPBodyElement bodyElementRoot = body.addBodyElement(new QName("http://localhost:8080/ws3/","selectMaxAgeCustomer","ns1"));

        //创建customer1实例
        SOAPElement elementc1 = bodyElementRoot.addChildElement(new QName("arg0"));
        elementc1.addChildElement(new QName("id")).addTextNode("1");
        elementc1.addChildElement(new QName("name")).addTextNode("A");
        elementc1.addChildElement(new QName("birthday")).addTextNode("1989-01-28T00:00:00.000+08:00");

        //创附件对象
        AttachmentPart attachment = message.createAttachmentPart(new DataHandler(new FileDataSource("f:\\c1.jpg")));

        //设置content-ID
        attachment.setContentId("<" + UUID.randomUUID().toString() + ">");
        attachment.setMimeHeader("Content-Transfer-Encoding","binary");
        message.addAttachmentPart(attachment);
        SOAPElement elementData = elementc1.addChildElement(new QName("imageData"));

        //添加XOP支持
        elementData.addChildElement(new QName("http://www.w3.org/2004/08/xop/include","Include","xop"))
                            .addAttribute(new QName("href"),"cid" + attachment.getContentId().replaceAll("<","").replaceAll(">",""));

        //创建customer2实例2
        SOAPElement elementc2 = bodyElementRoot.addChildElement(new QName("arg1"));
        elementc2.addChildElement(new QName("id")).addTextNode("2");
        elementc2.addChildElement(new QName("name")).addTextNode("B");
        elementc2.addChildElement(new QName("birthday")).addTextNode("1989-01-28T00:00:00.000+08:00");

        AttachmentPart attachment2 = message.createAttachmentPart(new DataHandler(new FileDataSource("f:\\c2.jpg")));
        attachment2.setContentId("<" + UUID.randomUUID().toString() + ">");
        message.addAttachmentPart(attachment2);
        SOAPElement elementData2 = elementc2.addChildElement(new QName("imageData"));
        elementData2.addChildElement(new QName("http://www.w3.org/2004/08/xop/include","Include","xop"))
                                    .addAttribute(new QName("href"), "cid" + attachment.getContentId().replaceAll("<", "").replaceAll(">", ""));
        //控制台输出soap消息
        OutputStream os = new ByteArrayOutputStream();
        message.writeTo(os);
        String soapStr = os.toString();
        System.out.println("\n@@@@@@@@@@@@@@@@@@@@@\n" + soapStr + "\n@@@@@@@@@@@@@@@@@@@@@@@@");
        //访问web服务器地址
        SOAPMessage soapMessage = connection.call(message, new URL("http://localhost:8080/ws3.Hello"));
        //控制台输出返回的soap消息
        OutputStream bos = new ByteArrayOutputStream();
        soapMessage.writeTo(bos);
        String soapStr2 = bos.toString();
        System.out.println("\n@@@@@@@@@@@@@@@@@\n" + soapStr2 + "\n@@@@@@@@@@@@@@@");
        //输出soap消息中的第一个子元素的元素名称
        System.out.println("\n<<<<<<<<<<<<<<<<<<" + soapMessage.getSOAPBody().getFirstChild().getLocalName());

        //输出soap消息中的附件
        Iterator<AttachmentPart> it = soapMessage.getAttachments();
        while(it.hasNext()){
            InputStream is = it.next().getDataHandler().getInputStream();
            byte[] b = new byte[is.available()];
            os = new FileOutputStream("f://bbb.jpg");
            while(is.read(b) != -1){
                os.write(b);
            }
            os.close();
        }
        connection.close();
    }
}
