package ws1;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.soap.MTOM;

/**
 * 对外发布的接口类
 * Created by lx on 2016/6/25.
 */
@WebService(name = "Hello")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@MTOM
public interface Hello {
    public void printContext();
    public Customer selectCustomerByName(@WebParam(name = "Customer")Customer customer);
    public Customer selectMaxAgeCustomer(Customer c1,Customer c2);
}
