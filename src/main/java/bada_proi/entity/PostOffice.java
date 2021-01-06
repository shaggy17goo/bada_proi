package bada_proi.entity;

public class PostOffice {
    private int postOfficeId;
    private String code;
    private String city;

    public PostOffice() {
    }

    public PostOffice(int postOfficeId, String code, String city) {
        this.postOfficeId = postOfficeId;
        this.code = code;
        this.city = city;
    }

    public int getPostOfficeId() {
        return postOfficeId;
    }

    public void setPostOfficeId(int postOfficeId) {
        this.postOfficeId = postOfficeId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "PostOffice{" +
                "id=" + postOfficeId +
                ", code='" + code + '\'' +
                ", city='" + city + '\'' +
                '}'+ '\n';
    }
}
