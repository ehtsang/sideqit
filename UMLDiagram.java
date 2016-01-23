import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UMLDiagram implements Diagram {

    public String generateDiagram() {return "";}

    private ArrayList<Method> methods;
    private ArrayList<Variable> variables;

    public void findMethods(String path) {
        String contents = FileFinder.readFile(path);
        Matcher matcher = Pattern.compile("((?:public|protected|private|abstract|\\s)(?:\\sstatic)?) +([\\w\\<\\>\\[\\]]+)\\s+(\\w+) *\\(([^\\)]*)\\) *(\\{?|[^;])").matcher(contents);
        while (matcher.find()) {
            String modifier = matcher.group(1).trim();
            String returnType = matcher.group(2).trim();
            String name = matcher.group(3).trim();
            ArrayList<String> parameters = new ArrayList<String>(Arrays.asList(matcher.group(4).trim().split(",")));
            Method method = new Method(name, returnType, modifier, parameters);
            methods.add(method);
        }
    }

    public static void main(String[] args) {
        UMLDiagram diagram = new UMLDiagram();
        diagram.findMethods("UMLDiagram.java");
    }
}
