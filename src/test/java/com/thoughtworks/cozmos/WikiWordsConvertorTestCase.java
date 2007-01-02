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

import junit.framework.TestCase;

public class WikiWordsConvertorTestCase extends TestCase {

    public void testShouldNotConvertEmptyText() throws Exception {
        WikiWordsConvertor convertor = convertor("");
        assertEquals("", convertor.convert());
    }

    public void testShouldNotConvertPlainText() throws Exception {
        WikiWordsConvertor convertor = convertor("The quick brown fox");
        assertEquals("The quick brown fox", convertor.convert());
    }

    public void testShouldNotConvertHTMLAnchors() throws Exception {
        WikiWordsConvertor convertor = convertor("<a href=\"not_a_wiki_link\">not a wiki link</a>");
        assertEquals(anchor("not_a_wiki_link", "not a wiki link"), convertor.convert());
    }

    public void testShouldConvertSingleWordWikiWordLinkToSimpleHTMLAnchor() throws Exception {
        WikiWordsConvertor convertor = convertor("[simple]");
        assertEquals(anchor("simple"), convertor.convert());
    }

    public void testShouldConvertMultipleWordWikiWordLinksToHTMLAnchorsWithUnderscores() throws Exception {
        WikiWordsConvertor convertor = convertor("[more complicated]");
        assertEquals(anchor("more_complicated", "more complicated"), convertor.convert());
    }

    public void testShouldConvertMultipleWikiWordLinksToSeparateHTMLAnchors() throws Exception {
        WikiWordsConvertor simpleConvertor = convertor("[foo][bar]");
        assertEquals(anchor("foo") + anchor("bar"), simpleConvertor.convert());

        WikiWordsConvertor multiWordConvertor = convertor("[foo bar][goo gle]");
        assertEquals(anchor("foo_bar", "foo bar") + anchor("goo_gle", "goo gle"), multiWordConvertor.convert());
    }

    public void testShouldNotConvertMismatchedLeftSquareBraces() throws Exception {
        WikiWordsConvertor emptyLeftSquareBraceConvertor = convertor("[[broken]");
        assertEquals("[[broken]", emptyLeftSquareBraceConvertor.convert());

        WikiWordsConvertor errantLeftSquareBraceConvertor = convertor("[word[broken]");
        assertEquals("[word[broken]", errantLeftSquareBraceConvertor.convert());
    }

    public void testShouldLeaveExtraRightSquareBracesAlone() throws Exception {
        WikiWordsConvertor convertor = convertor("[broken]]");
        assertEquals(anchor("broken") + "]", convertor.convert());
    }

    public void testShouldNotConvertWikiWordsWhichContainHTML() throws Exception {
        WikiWordsConvertor fullHTMLTagConvertor = convertor("[with html <br>]");
        assertEquals("[with html <br>]", fullHTMLTagConvertor.convert());

        WikiWordsConvertor errantLeftAngleBracketConvertor = convertor("[with html <]");
        assertEquals("[with html <]", errantLeftAngleBracketConvertor.convert());
    }

    // public void testShouldIgnoreLeftSquareBracketsInsideHTMLPreTags() throws
    // Exception {
    // WikiWordsConvertor convertor = convertor("<pre>[</pre>");
    // assertEquals("<pre>[</pre>", convertor.convert());
    //        
    // convertor = convertor("<pre>[unconverted]</pre>");
    // assertEquals("<pre>[unconverted]</pre>", convertor.convert());
    // }

    private static String anchor(String link) {
        return anchor(link, link);
    }

    private static String anchor(String href, String label) {
        return "<a href=\"" + href + "\">" + label + "</a>";
    }

    private static WikiWordsConvertor convertor(String wikiText) {
        return new WikiWordsConvertor(wikiText);
    }
}
