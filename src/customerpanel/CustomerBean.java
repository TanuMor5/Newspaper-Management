package customerpanel;

public class CustomerBean {
    private String name;
    private String mobile;
    private String hawker;
    private String address;
    private String email;

    public CustomerBean(String name, String mobile, String hawker, String address, String email) {
        this.name = name;
        this.mobile = mobile;
        this.hawker = hawker;
        this.address = address;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getHawker() {
        return hawker;
    }

    public void setHawker(String hawker) {
        this.hawker = hawker;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
