import java.util.ArrayList;

public class Method extends Structure {

    private String returnType;
    private ArrayList<String> parameters;

    /*
       O(1)
       Pre-condition: No input
       Post-condition: Sets up default values for all necessary variables
       */
    public Method() {
        name = "";
        returnType = "";
        modifier = "";
        parameters = new ArrayList<String>();
    }

    /*
       O(1)
       Pre-condition: Takes a string that is the name of the method
       Post-condition: An object with a set name
       */
    public Method(String name) {
        this();
        this.name = name;
    }

    /*
       O(1)
       Pre-condition: Takes three strings that are the name, return type, and modifier
       Post-condition: An object with a set name, return type, and modifier
       */
    public Method(String name, String returnType, String modifier) {
        this(name);
        this.returnType = returnType;
        this.modifier = modifier;
    }

    /*
       O(1)
       Pre-condition: Takes four strings that are the name, return type, modifier, and an ArrayList of parameters
       Post-condition: An object with a set name, return type, modifier, and an ArrayList of parameter
       */
    public Method(String name, String returnType, String modifier, ArrayList<String> parameters) {
        this(name, returnType, modifier);
        this.parameters = parameters;
    }

    //O(1)
    public String getReturnType() {
        return returnType;
    }

    /*
       O(1)
       Pre-condition: Takes a desired returnType
       Post-condition: Sets the returnType to the input
       */
    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    //O(1)
    public ArrayList<String> getParameters() {
        return parameters;
    }

    /*
       O(1)
       Pre-condition: Takes a desired parameter ArrayList
       Post-condition: Sets the parameter ArrayList to the input
       */
    public void setParameters(ArrayList<String> parameters) {
        this.parameters = parameters;
    }

    /*
       O(N)
       Pre-condition: Takes a Method object
       Post-condition: Stringified version of Method object
       */
    public String toString() {
        //String parameters = getParameters().toString().replace("[", "(").replace("]", ")").replace("  ", " "); //Bad solution, a reminder to never do this
        String param = "(";
        for(String parameter : parameters) {
            param += parameter + ",";
        }
        if (!param.equals("(")) {
            param = param.substring(0, param.length()-1);
        }
        param += ")";
        if(!getReturnType().equals("")) {
            return getModifier() + " " + getReturnType() + " " + getName() + param;
        } else {
            return getModifier() + " " + getName() + param;
        }
    }
}
