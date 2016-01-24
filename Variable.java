public class Variable extends Structure {

    private String type;

    public Variable() {
        super();
        type = "";
    }

    public Variable(String name) {
        this();
        this.name = name;
    }

    public Variable(String name, String modifier, String type) {
        this(name);
        this.modifier = modifier;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String toString() {
        return getType() + " " + getName();
    }
}
