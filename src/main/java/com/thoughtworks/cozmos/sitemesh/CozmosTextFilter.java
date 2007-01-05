package com.thoughtworks.cozmos.sitemesh;

import com.opensymphony.module.sitemesh.html.TextFilter;
import com.thoughtworks.cozmos.ExtraneousWordDocConvertor;
import com.thoughtworks.cozmos.WikiWordsConvertor;

public class CozmosTextFilter implements TextFilter {
    public String filter(String text) {
        text = new ExtraneousWordDocConvertor(text).convert();
        text = new WikiWordsConvertor(text).convert();
        return text;
    }
}
