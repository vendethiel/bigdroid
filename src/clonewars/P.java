package clonewars;

import java.util.InputMismatchException;
import java.util.Scanner;

// "Player", as in user interacting, class
public class P {
    static public void s(Object... s) {
        for (Object o : s) {
            System.out.print(o);
        }
        System.out.println("");
    }


    static public void print(Object... s) {
        for (Object o : s) {
            System.out.print(o);
        }
    }

    static public boolean ask(String q) {
        s(q + " [oui|non]");
        for (;;) {
            String answer = getScanner().next();
            if (answer.equals("yes") || answer.equals("y") || answer.equals("oui") || answer.equals("o") || answer.equals("1")) {
                return true;
            } else if (answer.equals("non") || answer.equals("n") || answer.equals("no") || answer.equals("0")) {
                return false;
            } else {
                s("Reponse invalide. Choix: [oui|non]");
            }
        }
    }

    static public int readIntBound(int max) {
        return readIntBound(1, max);
    }

    static public int readIntBound(int min, int max) {
        for (;;) {
            try {
                int select = P.readInt();
                if (select < min || select > max) {
                    throw new InputMismatchException();
                }

                return select;
            } catch (InputMismatchException ex) {
                P.s("Entree invalide !");
            }
        }
    }

    static public long r(double d) {
        return Math.round(d);
    }

    static public int readInt() {
        return getScanner().nextInt();
    }

    static public String read() {
        return getScanner().next();
    }

    static private Scanner getScanner() {
        return new Scanner(System.in).useDelimiter("\n");
    }

}
