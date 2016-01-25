import java.util.ArrayList;

public interface Diagram {

    public ArrayList<Method> findMethods(String path);

    public ArrayList<Variable> findVariables(String path);

}

