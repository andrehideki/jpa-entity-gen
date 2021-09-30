import javax.persistence.Column;

public class MyClass2 {

    @Column(name = "TESTE")
    private Integer age;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}
