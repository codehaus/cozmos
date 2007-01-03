package com.thoughtworks.cozmos;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ExtraneousWordDocConvertor {


    private static final Pattern SPELLING_ISSUES = Pattern.compile("<span class=SpellE>(.*?)</span>", Pattern.DOTALL);
    private static final Pattern GRAMMAR_ISSUES = Pattern.compile("<span class=GramE>(.*?)</span>", Pattern.DOTALL);


    private String pageText;

    public ExtraneousWordDocConvertor(String pageText) {

        this.pageText = pageText;
    }

    public String convert() {
        Matcher matcher = SPELLING_ISSUES.matcher(pageText);
        StringBuffer buffer = new StringBuffer(pageText);
        while (matcher.find()) {
            int startingPoint = buffer.indexOf(matcher.group());
            int endingPoint = endingPoint(startingPoint, matcher);
            String phrase = matcher.group(1);
            buffer.replace(startingPoint, endingPoint, phrase);
        }
        matcher = GRAMMAR_ISSUES.matcher(buffer);
        while (matcher.find()) {
            int startingPoint = buffer.indexOf(matcher.group());
            int endingPoint = endingPoint(startingPoint, matcher);
            String phrase = matcher.group(1);
            buffer.replace(startingPoint, endingPoint, phrase);
        }

        return buffer.toString();

    }

    private static int endingPoint(int startingPoint, Matcher matcher) {
        return startingPoint + matcher.end() - matcher.start();
    }

}
