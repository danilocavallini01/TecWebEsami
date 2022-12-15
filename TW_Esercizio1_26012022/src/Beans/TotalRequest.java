package Beans;

import java.util.Date;

public class TotalRequest {
	private Date date;
	private int operations;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getOperations() {
		return operations;
	}
	public void setOperations(int operations) {
		this.operations = operations;
	}
	public void addOperation() {
		this.operations++;
	}
	
	public TotalRequest() {
		this.date = new Date();
		this.operations = 0;
	}
}
