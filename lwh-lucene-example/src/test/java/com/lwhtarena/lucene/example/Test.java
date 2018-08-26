package com.lwhtarena.lucene.example;


import org.apache.lucene.queryparser.classic.ParseException;

import java.io.IOException;

public class Test {


    @org.junit.Test
    public static void test() throws IOException, ParseException {
        String indexDir = "D:\\mylog";
        String str = "localhost-startStop";
        Searcher.search(indexDir, str);
    }
}
