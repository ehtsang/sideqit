import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UMLDiagram implements Diagram {

    public String generateDiagram() {return "";}

    public ArrayList<Method> findMethods(String path) {
        String name, modifier, returnType;

        ArrayList<Method> methods = new ArrayList<Method>();
        String contents = FileFinder.readFile(path);

        String className = new File(path).getName();
        className = className.substring(0, className.length()-5);

        // Regex for constructors
        Matcher matcher = Pattern.compile("(?:public) " + className + "\\(([^\\)]*)\\) *(\\{?|[^;])").matcher(contents);
        while (matcher.find()) {
            modifier = "public";
            returnType = "";
            name = className;
            ArrayList<String> parameters = new ArrayList<String>(Arrays.asList(matcher.group(1).trim().split(",")));
            Method constructor = new Method(name, "", modifier, parameters);
            methods.add(constructor);
        }

        // Regex for other methods
        matcher = Pattern.compile("((?:public|protected|private|static|final)+) +([\\w\\<\\>\\[\\]]+) +(\\w+) *\\(([^\\)]*)\\) *(\\{?|[^;])").matcher(contents);
        while (matcher.find()) {
            modifier = matcher.group(1).trim();
            returnType = matcher.group(2).trim();
            name = matcher.group(3).trim();
            ArrayList<String> parameters = new ArrayList<String>(Arrays.asList(matcher.group(4).trim().split(",")));
            Method method = new Method(name, returnType, modifier, parameters);
            methods.add(method);
        }

        return methods;
    }

    public ArrayList<Variable> findVariables(String path) {
        ArrayList<Variable> variables = new ArrayList<Variable>();
        String contents = FileFinder.readFile(path);
        Matcher matcher = Pattern.compile("((?:public|protected|private|static|final)+) +([\\w\\<\\>\\[\\]]+) +(\\w+)(;|=)").matcher(contents);
        while (matcher.find()) {
            String modifier = matcher.group(1).trim();
            String type = matcher.group(2).trim();
            String name = matcher.group(3).trim();
            // System.out.println(modifier); //TESTING PURPOSES
            Variable variable = new Variable(name, modifier, type);
            variables.add(variable);
        }
        return variables;
    }

    public static void main(String[] args) {
        ArrayList<Method> methods = new ArrayList<Method>();
        ArrayList<Variable> variables = new ArrayList<Variable>();
        UMLDiagram diagram = new UMLDiagram();
        methods = diagram.findMethods("Method.java");
        variables = diagram.findVariables("Method.java");
        System.out.println("-->Methods");
        for(Method method : methods){
            System.out.println("--|" + method);
        }
        System.out.println("-->Variables");
        for(Variable variable : variables){
            System.out.println("--|" + variable);
        }
    }
}
