package bada_proi.entity;

public class PostOffice {
    private int id;
    private String code;
    private String city;

    public PostOffice() {
    }

    public PostOffice(int id, String code, String city) {
        this.id = id;
        this.code = code;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
                "id=" + id +
                ", code='" + code + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
