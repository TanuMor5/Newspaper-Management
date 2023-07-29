package billboard;



public class BillBean {
	String mobile;
	String datefrom;
	String dateto;
	String bill;
	
    public BillBean( String mobile,String datefrom, String dateto, String bill) {
    	this.mobile= mobile;
        this.datefrom = datefrom;
        this.dateto = dateto;
        this.bill =bill;
    }

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getDatefrom() {
		return datefrom;
	}

	public void setDatefrom(String datefrom) {
		this.datefrom = datefrom;
	}

	public String getDateto() {
		return dateto;
	}

	public void setDateto(String dateto) {
		this.dateto = dateto;
	}

	public String getBill() {
		return bill;
	}

	public void setBill(String bill) {
		this.bill = bill;
	}
    
    
    
    
}
