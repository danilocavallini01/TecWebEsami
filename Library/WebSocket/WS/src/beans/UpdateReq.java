package beans;

public class UpdateReq {
	
	private String update;
	private String valore;
	public String getOp() {
		return update;
	}
	public void setOp(String op) {
		this.update = op;
	}
	public String getValore() {
		return valore;
	}
	public void setValore(String valore) {
		this.valore = valore;
	}
	@Override
	public String toString() {
		return "UpdateReq [op=" + update + ", valore=" + valore + "]";
	}
	public UpdateReq() {
		super();
	}
	
	

}
