package ws1;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by lx on 2016/6/25.
 */
@XmlRootElement(name = "Customer")
//只关注字段名不关注方法
@XmlAccessorType(XmlAccessType.FIELD)
public class Customer {
    private long id;
    private String name;
    private Date birthday;
    //标注这是一个二进制文件
    @XmlMimeType("application/octet-stream")
    private DataHandler imageData;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public DataHandler getImageData() {
        return imageData;
    }

    public void setImageData(DataHandler imageData) {
        this.imageData = imageData;
    }
}
