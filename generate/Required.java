import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "foo",
    "bar",
    "baz"
})
@Generated("jsonschema2pojo")
public class Required {

    @JsonProperty("foo")
    private String foo;
    @JsonProperty("bar")
    private Integer bar;
    @JsonProperty("baz")
    private Boolean baz;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("foo")
    public String getFoo() {
        return foo;
    }

    @JsonProperty("foo")
    public void setFoo(String foo) {
        this.foo = foo;
    }

    @JsonProperty("bar")
    public Integer getBar() {
        return bar;
    }

    @JsonProperty("bar")
    public void setBar(Integer bar) {
        this.bar = bar;
    }

    @JsonProperty("baz")
    public Boolean getBaz() {
        return baz;
    }

    @JsonProperty("baz")
    public void setBaz(Boolean baz) {
        this.baz = baz;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Required.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("foo");
        sb.append('=');
        sb.append(((this.foo == null)?"<null>":this.foo));
        sb.append(',');
        sb.append("bar");
        sb.append('=');
        sb.append(((this.bar == null)?"<null>":this.bar));
        sb.append(',');
        sb.append("baz");
        sb.append('=');
        sb.append(((this.baz == null)?"<null>":this.baz));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.bar == null)? 0 :this.bar.hashCode()));
        result = ((result* 31)+((this.baz == null)? 0 :this.baz.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.foo == null)? 0 :this.foo.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Required) == false) {
            return false;
        }
        Required rhs = ((Required) other);
        return (((((this.bar == rhs.bar)||((this.bar!= null)&&this.bar.equals(rhs.bar)))&&((this.baz == rhs.baz)||((this.baz!= null)&&this.baz.equals(rhs.baz))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.foo == rhs.foo)||((this.foo!= null)&&this.foo.equals(rhs.foo))));
    }

}
