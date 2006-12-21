package com.thoughtworks.cozmos.sitemesh;

import junit.framework.TestCase;

import com.opensymphony.module.sitemesh.Page;

public class WikiWordsParserTestCase extends TestCase {

    private WikiWordsParser parser;

    protected void setUp() throws Exception {
        super.setUp();
        parser = new WikiWordsParser();
    }

    public void xtestShouldConvertWikiWordsOutsideOfAnyHTMLTagsIntoHTMLAnchors() throws Exception {
        Page page = parser.parse("[hello world]".toCharArray());
        assertEquals("<a href=\"hello_world\">hello world</a>", page.getPage());
    }

    public void testShouldNotConvertWikiWordsInsideHTMLPreTagsToAnchors() throws Exception {
        Page page = parser.parse("<pre>[should not be converted]</pre>".toCharArray());
        assertEquals("<pre>[should not be converted]</pre>", page.getPage());
    }
}
