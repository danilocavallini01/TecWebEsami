package Beans;

public class Result {
    private Long serverTime;
    private Long beanTime;
    private int serverCount;
    private int beanCount;

    public Long getServerTime() {
        return serverTime;
    }
    public void setServerTime(Long serverTime) {
        this.serverTime = serverTime;
    }
    public Long getBeanTime() {
        return beanTime;
    }
    public void setBeanTime(Long beanTime) {
        this.beanTime = beanTime;
    }
    public int getServerCount() {
        return serverCount;
    }
    public void setServerCount(int serverCount) {
        this.serverCount = serverCount;
    }
    public int getBeanCount() {
        return beanCount;
    }
    public void setBeanCount(int beanCount) {
        this.beanCount = beanCount;
    }

    @Override
    public String toString() {
        return "Result [serverTime=" + serverTime + ", beanTime=" + beanTime + ", serverCount=" + serverCount
                + ", beanCount=" + beanCount + "]";
    }
    public Result() {
        this.beanCount = 0;
        this.serverCount = 0;
        this.serverTime = 0l;
        this.beanTime = 0l;
    }
}
