package demo;

import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.en.KStemFilter;
import org.apache.lucene.analysis.standard.StandardFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Utility {
    private static final String stopwordsFile = "stopwords.txt";
    private static CharArraySet getStopwords() {
        List<String> stopwordsList = new ArrayList<String>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(stopwordsFile));
            String line;
            while((line = reader.readLine()) != null) {
                stopwordsList.add(line.trim());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new CharArraySet(stopwordsList, true);
    }

    public static String strJoin(List<String> aArr, String sSep) {
        StringBuilder sbStr = new StringBuilder();
        for (int i = 0, il = aArr.size(); i < il; i++) {
            if (i > 0)
                sbStr.append(sSep);
            sbStr.append(aArr.get(i));
        }
        return sbStr.toString();
    }

    // tokenize, remove stop word, stem
    public static List<String> cleanedTokenize(String input) {
        List<String> tokens = new ArrayList<String>();
        StringReader reader = new StringReader(input.toLowerCase());
        Tokenizer tokenizer = new StandardTokenizer();
        tokenizer.setReader(reader);
        TokenStream tokenStream = new StandardFilter(tokenizer);
        tokenStream = new StopFilter(tokenStream, getStopwords());
        tokenStream = new KStemFilter(tokenStream);
        CharTermAttribute charTermAttribute = tokenizer.addAttribute(CharTermAttribute.class);
        try {
            tokenStream.reset();
            while (tokenStream.incrementToken()) {
                String token = charTermAttribute.toString();
                if(token.length() < 2 || token.matches("[0-9.]+")) continue;
                tokens.add(token);
            }
            tokenStream.end();
            tokenStream.close();
            tokenizer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tokens;
    }
}
