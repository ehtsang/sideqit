public abstract class Structure {

    protected String name;
    protected String modifier;

    //O(1)
    public String getName() {
        return name;
    }
    
    /*
    O(1)
    Pre-condition: Takes a desired name that is a string
    Post-condition: Sets name to the input
    */
    public void setName(String name) {
        this.name = name;
    }

    //O(1)
    public String getModifier() {
        return modifier;
    }

    /*
    O(1)
    Pre-condition: Takes a desired modifier that is a string
    Post-condition: Sets modifier to the input
    */
    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public abstract String toString();

}
