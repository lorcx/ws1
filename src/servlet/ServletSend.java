package servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * servlet 发送webservice
 * Created by dell on 2016/7/12.
 */
public class ServletSend extends HttpServlet{
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        URL url = new URL("http://localhost:8080/ws.WsServiceTest?wsdl");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        String soap = "";//soap数据
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestProperty("Content-type","text/xml;charset=utf-8");
        OutputStream os = connection.getOutputStream();
        os.write(soap.getBytes("utf-8"));//发送请求
        os.close();

        //响应
        int responseCode = connection.getResponseCode();
        if(responseCode == HttpURLConnection.HTTP_ACCEPTED) {//成功
            InputStream is = connection.getInputStream();//获取接收数据
            System.out.println("length : " + is.available());//请求的长度
            //将数据写到页面上
            byte[] bytes = new byte[1024];
            int n;
            ServletOutputStream sos = null;
            while ((n = is.read(bytes)) > 0) {
                response.setContentType("text/text;charset=utf-8");
                sos = response.getOutputStream();
                sos.write(bytes, 0, n);
            }
            sos.flush();
            sos.close();
        }

    }
}
