import java.util.ArrayList;

public class Method extends Structure {

    private String returnType;
    private ArrayList<String> parameters;

    public Method() {
        name = "";
        returnType = "";
        modifier = "";
        parameters = new ArrayList<String>();
    }

    public Method(String name) {
        this();
        this.name = name;
    }

    public Method(String name, String returnType, String modifier) {
        this(name);
        this.returnType = returnType;
        this.modifier = modifier;
    }

    public Method(String name, String returnType, String modifier, ArrayList<String> parameters) {
        this(name, returnType, modifier);
        this.parameters = parameters;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public ArrayList<String> getParameters() {
        return parameters;
    }

    public void setParameters(ArrayList<String> parameters) {
        this.parameters = parameters;
    }

    public String toString() {
    	if(!getReturnType().equals("")){
        	return getModifier() + " " + getReturnType() + " " + getName() + " " + getParameters();
    	} else {
		return getModifier() + " " + getName() + " " + getParameters();
	}
    }
}
