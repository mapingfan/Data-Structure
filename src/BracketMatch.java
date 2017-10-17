import Stack.StackArray.StackArray;

public class BracketMatch {

    public static void main(String[] args) {
        String str ="(1+2)*3/4}";
        if (BracketMatch.bracketMatch(str)) {
            System.out.println("Legal");
        } else {
            System.out.println("Illegal");
        }

    }

    public static boolean bracketMatch(String string) {
        char tmp;
        StackArray stackArray = new StackArray();
        Object tmp_object;
        char tmp_char;
        for (int i = 0; i < string.length(); i++) {
            tmp = string.charAt(i);
            switch (tmp) {
                case '{':
                case '(':
                case '[':
                    stackArray.push(tmp);
                    break;
                case '}':
                    if (stackArray.isEmpty()) {
                        return false;
                    }
                    tmp_object = stackArray.pop();
                    tmp_char = (char) tmp_object;
                    if (tmp_char == '{') {
                        break;
                    } else {
                        return false;
                    }
                case ']':
                    if (stackArray.isEmpty()) {
                        return false;
                    }
                    tmp_object = stackArray.pop();
                    tmp_char = (char) tmp_object;
                    if (tmp_char == '[') {
                        break;
                    } else {
                        return false;
                    }
                case ')':
                    if (stackArray.isEmpty()) {
                        return false;
                    }
                    tmp_object = stackArray.pop();
                    tmp_char = (char) tmp_object;
                    if (tmp_char == '(') {
                        break;
                    } else {
                        return false;
                    }
            }
        }
        if (stackArray.isEmpty()) {
            return true;
        }
        return false;
    }
}
