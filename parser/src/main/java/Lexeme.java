
package main.java;

import java.util.Objects;

public class Lexeme {
    public enum Type {
        PLUS,
        MINUS,
        MULT,
        DIV,
        POW,
        NUMBER,
        OPEN, CLOSE, EOF
    }
    public Type type;
    public String value;

    public Lexeme(Type type, String value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lexeme)) return false;
        Lexeme lexeme = (Lexeme) o;
        return type == lexeme.type &&
                Objects.equals(value, lexeme.value);
    }

    @Override
    public int hashCode() {

        return Objects.hash(type, value);
    }
}

