import javax.persistence.Column;
import javax.persistence.Id;

public class MyClass2 {

    @Column
    @Id
    private Integer age;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}
