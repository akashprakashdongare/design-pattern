package main.builder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class HtmlElement{
    public String name, text;
    public List<HtmlElement> elements = new ArrayList<>();
    private final int indentSize = 2;
    private final String newLine = System.lineSeparator();

    public HtmlElement(){}

    public HtmlElement(String name, String text){
        this.name = name;
        this.text = text;
    }

    private String toStringImpl(int indent){
        StringBuilder sb = new StringBuilder();
        String i = String.join("", Collections.nCopies(indent * indentSize, " "));
        sb.append(String.format("%s<%s>%s", i, name, newLine));
        if(null != text && !text.isEmpty()){
            sb.append(String.join("", Collections.nCopies(indentSize*(indent+1), " ")))
              .append(text)
              .append(newLine);
        }

        for(HtmlElement e : elements)
            sb.append(e.toStringImpl(indent+1));

        sb.append(String.format("%s<%s>%s", i, name, newLine));
        return sb.toString();
    }

    @Override
    public String toString() {
        return toStringImpl(0);
    }


}

class HtmlElementBuilder{
    private String rootName;
    private HtmlElement root = new HtmlElement();

    public HtmlElementBuilder(String rootName){
        this.rootName = rootName;
        root.name = rootName;
    }

    public HtmlElementBuilder addChild(String childName, String childText){
        HtmlElement element = new HtmlElement(childName, childText);
        root.elements.add(element);
        return this;
    }

    public void clear(){
        root = new HtmlElement();
        root.name = rootName;
    }

    @Override
    public String toString() {
        return root.toString();
    }

    
}
public class BuilderExp {
    public static void main(String[] args) {
        // String hello = "Hello";
        // System.out.println("<p>" + hello + "</p>");

        // String[] words = {"hello", "world"};

        // StringBuilder sb = new StringBuilder();
        // sb.append("<ul>\n");
        // for(String word : words){
        //     sb.append(String.format("  <li>%s</li>\n", word));
        // }
        // sb.append("</ul>");
        
        // System.out.println(sb);

        HtmlElementBuilder builder = new HtmlElementBuilder("ul");
        builder.addChild("li", "hello")
               .addChild("li", "word");
        System.out.println(builder);

    }
}
