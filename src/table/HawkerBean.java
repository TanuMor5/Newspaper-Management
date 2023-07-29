package table;

public class HawkerBean {
        String hname;
        String mobile;
        String alloarea;
        String doj;
        public HawkerBean(String area, String paper) {}



public HawkerBean(String hname,String mobile, String alloarea, String doj) {
	super();
	this.hname=hname;
	this.mobile=mobile;
	this.alloarea=alloarea;
	this.doj=doj;
	
}


public String getHname() {
	return hname;
}


public void setHname(String hname) {
	this.hname = hname;
}


public String getMobile() {
	return mobile;
}


public void setMobile(String mobile) {
	this.mobile = mobile;
}


public String getAlloarea() {
	return alloarea;
}


public void setAlloarea(String alloarea) {
	this.alloarea = alloarea;
}


public String getDoj() {
	return doj;
}


public void setDoj(String doj) {
	this.doj = doj;
}
}

