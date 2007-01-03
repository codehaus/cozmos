/*
 * Copyright (c) 2006, 2007 ThoughtWorks, Inc.
 * 
 * Permission to use, copy, modify, and distribute this software for any
 * purpose with or without fee is hereby granted, provided that the above
 * copyright notice and this permission notice appear in all copies.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR
 * ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF
 * OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 * 
 */

package com.thoughtworks.cozmos;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WikiWordsConvertor {
    private static final String ANYTHING_BETWEEN_SQUARE_BRACES = "\\[(.*?)\\]";

    private static final Pattern WIKI_WORDS = Pattern.compile(ANYTHING_BETWEEN_SQUARE_BRACES, Pattern.DOTALL);

    private static final String SPACE = " ";

    private static final String UNDERSCORE = "_";

    private final String wikiText;

    public WikiWordsConvertor(String wikiText) {
        this.wikiText = wikiText;
    }

    public String convert() {
        Matcher wikiWordsMatcher = WIKI_WORDS.matcher(wikiText);
        StringBuffer buffer = new StringBuffer(wikiText);
        while (wikiWordsMatcher.find()) {
            int startingPoint = buffer.indexOf(wikiWordsMatcher.group());
            int endingPoint = endingPoint(startingPoint, wikiWordsMatcher);

            String wikiWord = wikiWordsMatcher.group(1);
            if (wikiWord.indexOf('[') == -1 && wikiWord.indexOf('<') == -1) {
                String anchor = toAnchor(wikiWord);
                buffer.replace(startingPoint, endingPoint, anchor);
            }
        }
        return buffer.toString();
    }

    private static int endingPoint(int startingPoint, Matcher matcher) {
        return startingPoint + matcher.end() - matcher.start();
    }

    private static String toAnchor(String wikiWord) {
        return "<a href=\"" + underscorify(wikiWord) + ".html\">" + wikiWord.replaceAll("\n", SPACE) + "</a>";
    }

    private static String underscorify(String wikiWord) {
        return wikiWord.replaceAll(SPACE, UNDERSCORE).replaceAll("\n", UNDERSCORE);
    }
}
