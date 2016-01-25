public class Variable extends Structure {

    private String type;

    /*
       O(1)
       Pre-condition: No input
       Post-condition: Sets up default values for all necessary variables
       */
    public Variable() {
        super();
        type = "";
    }

    /*
       O(1)
       Pre-condition: Takes a string that is the name of the variable
       Post-condition: An object with a set name
       */
    public Variable(String name) {
        this();
        this.name = name;
    }

    /*
       O(1)
       Pre-condition: Takes three strings that are the name, modifier, and type
       Post-condition: An object with a set name, modifier, and type
       */
    public Variable(String name, String modifier, String type) {
        this(name);
        this.modifier = modifier;
        this.type = type;
    }

    //O(1)
    public String getType() {
        return type;
    }

    /*
       O(1)
       Pre-condition: Takes a desired type
       Post-condition: Sets the type to the input
       */
    public void setType(String type) {
        this.type = type;
    }

    /*
       O(N)
       Pre-condition: Takes a Variable object
       Post-condition: Stringified version of Variable object
       */
    public String toString() {
        return getType() + " " + getName();
    }
}
