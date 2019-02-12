package test.java;

import main.java.Lexer;
import main.java.Parser;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;

public class ParserTest {

    Parser newParser(String f) throws IOException, ParseException {
        return new Parser(
                new Lexer(
                        new StringReader(f)
                )
        );
    }

    @Test
    public void parseExpr() throws IOException, ParseException {
        assert newParser("5 + 1023 + 4 - 27").parseExpr() == 1005;
        assert newParser("7 - 7 + 14").parseExpr() == 14;
        try {
            newParser("8 - 78 +").parseExpr();
            assert false;
        } catch (ParseException e){
            assert true;
        }
    }

    @Test
    public void parseTerm() throws IOException, ParseException{
        assert newParser("150 * 5 * 8 ").parseTerm() == 6000;
        assert newParser("900 * 8 / 12").parseTerm() == 600;
        try {
            newParser("1/").parseTerm();
            assert false;
        } catch (ParseException e){
            assert true;
        }
    }

    @Test
    public void parseFlat() throws IOException, ParseException{
        assert newParser("2 ^ 2 ^ 3 ").parseFlat() == 256;
    }

    @Test
    public void parsePower() throws IOException, ParseException{
        assert newParser("-0").parsePower() == 0;
        assert newParser("-983").parsePower() == -983;
        assert newParser("250").parsePower() == 250;
    }

    @Test
    public void parseAtom() throws IOException, ParseException{
        assert newParser("78").parseAtom() == 78;
        assert newParser("(84)").parseAtom() == 84;
        assert newParser("(2+3)").parseAtom() == 5;
        try {
            newParser("(4+8").parseAtom();
            assert false;
        } catch (ParseException e){
            assert true;
        }
    }

    @Test
    public void calculate() throws IOException, ParseException{
        assert newParser("1 + 2*3 - 4/2 + (6-2^2)^(2-1) - 4*2 - -1").calculate() == 0;
        try {
            newParser("(5+6))").calculate();
            assert false;
        } catch (ParseException e){
            assert true;
        }

    }
}