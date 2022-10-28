package exercise;

// BEGIN
public class ReversedSequence implements CharSequence {
    private String line;

    public ReversedSequence(String line) {
        StringBuilder sb = new StringBuilder(line);
        this.line = sb.reverse().toString();
    }

    @Override
    public int length() {
        return line.length();
    }

    @Override
    public char charAt(int i) {
        return line.charAt(i);
    }

    @Override
    public CharSequence subSequence(int i, int i1) {
        return line.subSequence(i, i1);
    }

    @Override
    public String toString() {
        return line;
    }
}
// END
