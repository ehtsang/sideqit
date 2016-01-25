import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UMLDiagram implements Diagram {

    /*
    O(N^2)
    Pre-condition: Takes a valid path
    Post-condition: Prints out a full UML diagram
    */
    public static void generateDiagram(String path) {
        ArrayList<Method> methods = new ArrayList<Method>();
        ArrayList<Variable> variables = new ArrayList<Variable>();
        ArrayList<String> files = new ArrayList<String>();
        FileFinder.findFiles(path, files, ".java");
        //System.out.println(files);
        UMLDiagram diagram = new UMLDiagram();
        for(String filePath : files){
	    String os = System.getProperty("os.name");
	    System.out.println(os);
            System.out.println(filePath.substring(filePath.lastIndexOf("\\")+1));
            methods = diagram.findMethods(filePath);
            variables = diagram.findVariables(filePath);
            System.out.println("-->Methods:");
            for(Method method : methods){
                System.out.println("----|" + method);
            }
            System.out.println("-->Variables:");
            for(Variable variable : variables){
                System.out.println("----|" + variable);
            }
            System.out.println();
        }
    }

    /*
    O(1)
    Pre-condition: None
    Post-condition: Outputs name of team members and their period
    */
    public static void generateCredits() {
        String edward = "Edward Tsang - Period 4";
        String leon = "Leon Loi - Period 3";
        String[][] credits = new String[1][2];
        credits[0][0] = edward;
        credits[0][1] = leon;
        System.out.println("Generator made by:");
        for (int i = 0; i < credits.length; i++){
            for(int j = 0; j < credits[0].length; j++){
                System.out.println(credits[i][j]);
            }
        }
    }

    /*
    O(N^2)
    Pre-condition: Path exists
    Post-condition: An ArrayList full of the found methods
    */
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
        matcher = Pattern.compile("((?:public|protected|private| ?abstract| ?static)+) +([\\w\\<\\>\\[\\]]+) +(\\w+) *\\(([^\\)]*)\\) *(\\{?|[^;])").matcher(contents);
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

    /*
    O(N^2)
    Pre-condition: The path exists
    Post-condition: An ArrayList full of the found variables
    */
    public ArrayList<Variable> findVariables(String path) {
        ArrayList<Variable> variables = new ArrayList<Variable>();
        String contents = FileFinder.readFile(path);
        Matcher matcher = Pattern.compile("((?:public|protected|private|static|final)+) +([\\w\\<\\>\\[\\]]+) +(\\w+)(;|=)").matcher(contents);
        while (matcher.find()) {
            String modifier = matcher.group(1).trim();
            String type = matcher.group(2).trim();
            String name = matcher.group(3).trim();
            Variable variable = new Variable(name, modifier, type);
            variables.add(variable);
        }
        return variables;
    }

    /*
    O(N^2)
    Pre-condition: A potential argument may be given
    Post-condition: A UML Diagram is generated
    */
    public static void main(String[] args) {
        if(args.length < 1){
            System.out.println("Please provide an argument.");
            System.exit(1);
        }
        File directory = new File(args[0]);
        if (!directory.exists()){
            System.out.println("Please provide a valid path.");
            System.exit(1);
        }
        generateDiagram(args[0]);
        generateCredits();
    }
}
