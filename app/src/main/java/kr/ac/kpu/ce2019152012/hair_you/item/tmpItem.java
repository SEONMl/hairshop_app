package kr.ac.kpu.ce2019152012.hair_you.item;

public class tmpItem {
    private String name;
    private String date;
    private String content;
    private String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public tmpItem(String name, String date, String content, String phone) {
        this.name = name;
        this.date = date;
        this.content = content;
        this.phone = phone;
    }
}
