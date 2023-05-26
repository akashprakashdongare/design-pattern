package main.flyweight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class FormattingText{
    private String plainText;
    private boolean[] capitals;

    public FormattingText(String plainText) {
        this.plainText = plainText;
        this.capitals = new boolean[plainText.length()];
    }
    public void capitalization(int start, int end){
        for(int i = start; i <= end; ++i){
            capitals[i] = true;
        }
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < plainText.length(); ++i){
            char c = plainText.charAt(i);
            sb.append(
                capitals[i] ? Character.toUpperCase(c) : c
            );
        }
        return sb.toString();
    }
}
class BetterFormatting{
    private String plainText;
    private List<TextRange> formatting = new ArrayList<>();

    public BetterFormatting(String plainText) {
        this.plainText = plainText;
    }

    public TextRange getRange(int start, int end){
        TextRange range = new TextRange(start, end);
        formatting.add(range);
        return range;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < plainText.length(); ++i){
            char c = plainText.charAt(i);
            for(TextRange range : formatting){
                if(range.covers(i) && range.capitalize){
                    c = Character.toUpperCase(c);
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }

    class TextRange{
        public int start, end;
        public boolean capitalize, bold, italic;

        public TextRange(int start, int end){
            this.start = start;
            this.end = end;
        }
        public boolean covers(int position){
            return position >= start && position <= end;
        }
    }
}
public class TextFormatting {
    public static void main(String[] args) {
        FormattingText ft = new FormattingText("This is a brave new world");
        ft.capitalization(10, 15);
        System.out.println(ft);

        BetterFormatting btf = new BetterFormatting("Make America Great Again");
        btf.getRange(13, 18).capitalize = true;
        System.out.println(btf);
    }
}
