package Beans;

public class Result {
   private String result;
   private int length;

    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }
    public int getLength() {
        return length;
    }
    public void setLength(int length) {
        this.length = length;
    }
    public Result(String result) {
        this.result = result;
        this.length = result.length();
    }

    
}
