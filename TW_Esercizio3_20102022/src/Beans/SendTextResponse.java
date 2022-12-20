package Beans;

public class SendTextResponse extends Response {
    private String text;

    public SendTextResponse(String text) {
        super("send_text_response");
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
