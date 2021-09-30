import javax.persistence.Column;

public class MyClass3 {

    @Column(name = "TESTE")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
