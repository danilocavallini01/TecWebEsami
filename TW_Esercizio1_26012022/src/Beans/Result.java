package Beans;

public class Result {
    private String result;
    private int count;

    public int getCount() {
    	return count;
	}
	
    public void setCount(int count) {
		this.count = count;
	}
	
	public String getResult() {
        return result;
    }
	
    public void setResult(String result) {
        this.result = result;
    }

    public Result(String result, int count) {
        this.result = result;
        this.count = count;
    }
}
