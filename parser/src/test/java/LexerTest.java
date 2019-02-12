package test.java;

import main.java.Lexeme;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;

import main.java.Lexer;

public class LexerTest {

    @Test
    public void nextLexeme() throws IOException, ParseException {
        Lexer lexer = new Lexer(
                new StringReader("1^/62(-)9    -74 ")
        );


        assert lexer.nextLexeme().equals(new Lexeme(Lexeme.Type.NUMBER, "1"));
        assert lexer.nextLexeme().equals(new Lexeme(Lexeme.Type.POW, "^"));
        assert lexer.nextLexeme().equals(new Lexeme(Lexeme.Type.DIV, "/"));
        assert lexer.nextLexeme().equals(new Lexeme(Lexeme.Type.NUMBER, "62"));
        assert lexer.nextLexeme().equals(new Lexeme(Lexeme.Type.OPEN, "("));
        assert lexer.nextLexeme().equals(new Lexeme(Lexeme.Type.MINUS, "-"));
        assert lexer.nextLexeme().equals(new Lexeme(Lexeme.Type.CLOSE, ")"));
        assert lexer.nextLexeme().equals(new Lexeme(Lexeme.Type.NUMBER, "9"));
        assert lexer.nextLexeme().equals(new Lexeme(Lexeme.Type.MINUS, "-"));
        assert lexer.nextLexeme().equals(new Lexeme(Lexeme.Type.NUMBER, "74"));
        assert lexer.nextLexeme().equals(new Lexeme(Lexeme.Type.EOF, ""));
    }

}