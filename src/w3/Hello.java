package w3;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * 对外发布的接口类
 * Created by lx on 2016/6/25.
 */
@WebService(name = "Hello")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface Hello {
    public void printContext();
    public Customer selectCustomerByName(@WebParam(name = "c",header = true) Customer customer);
    public Customer selectMaxAgeCustomer(Customer c1, Customer c2);
}
