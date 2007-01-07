package com.thoughtworks.cozmos;

import junit.framework.TestCase;

public class ExtraneousWordDocConvertorTestCase extends TestCase {

    public void testShouldNotConvertEmptyText() throws Exception {
        ExtraneousWordDocConvertor convertor = convertor("");
        assertEquals("", convertor.convert());
    }

    public void testShouldNotConvertPlainText() throws Exception {
        ExtraneousWordDocConvertor convertor = convertor("The quick brown fox");
        assertEquals("The quick brown fox", convertor.convert());
    }

    public void testShouldNotConvertUnrecognizedSpans() throws Exception {
        ExtraneousWordDocConvertor convertor = convertor("<span class=foo>Cozmos</span>");
        assertEquals("<span class=foo>Cozmos</span>", convertor.convert());
    }

    public void testShouldConvertAllegedSpellingMistakes() throws Exception {
        ExtraneousWordDocConvertor convertor = convertor("<span class=SpellE>Cozmos</span>");
        assertEquals("Cozmos", convertor.convert());
    }

    public void testShouldConvertAllegedGrammarMistakes() throws Exception {
        ExtraneousWordDocConvertor convertor = convertor("<span class=GramE>hello</span>");
        assertEquals("hello", convertor.convert());
    }

    public void testShouldConvertMultipleSpellingMistakes() throws Exception {
        ExtraneousWordDocConvertor convertor = convertor("<span class=SpellE>A</span><span class=SpellE>B</span>");
        assertEquals("AB", convertor.convert());
    }

    public void testShouldConvertBothSpellingAndGrammarMistakes() throws Exception {
        ExtraneousWordDocConvertor convertor = convertor("<span class=SpellE>DOESN'T</span> <span class=GramE>MATTER</span>");
        assertEquals("DOESN'T MATTER", convertor.convert());
    }

    public void testShouldConvertSpellingMistakesThatSpanMultipleLines() throws Exception {
        ExtraneousWordDocConvertor convertor = convertor("<span class=SpellE>\nDOESN'T MATTER</span>");
        assertEquals("DOESN'T MATTER", convertor.convert());
    }

    public void testShouldConvertGrammarMistakesThatSpanMultipleLines() throws Exception {
        ExtraneousWordDocConvertor convertor = convertor("<span class=GramE>\nDOESN'T MATTER</span>");
        assertEquals("DOESN'T MATTER", convertor.convert());
    }

    private static ExtraneousWordDocConvertor convertor(String pageText) {
        return new ExtraneousWordDocConvertor(pageText);
    }
}
