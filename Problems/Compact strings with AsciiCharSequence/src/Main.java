import java.util.*;
class AsciiCharSequence implements CharSequence {
    byte[] characters;
    AsciiCharSequence(byte[] characters) {
        this.characters = characters;
    }

    public int length() {
        return characters.length;
    }

    public char charAt(int index) {
        return (char) characters[index];
    }

    public AsciiCharSequence subSequence(int start, int end) {
        byte[] newCharaters = Arrays.copyOfRange(characters, start, end);
        return new AsciiCharSequence(newCharaters);
    }

    public String toString() {
        return new String(this.characters);
    }
}
