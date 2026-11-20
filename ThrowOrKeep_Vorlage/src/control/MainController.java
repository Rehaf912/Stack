package control;

import jdk.jfr.ContentType;
import view.ViewWindow;
import model.Card;
import model.Stack;

public class MainController {

    private Stack<Card> stackOrigin;  //Ursprünglicher Stapel mit zufälligen Zahlen/Karten
    private Stack<Card> stackKeep;    //Stapel der Karten, der behalten wird

    public static void main(String[] args) {
        new MainController();
    }

    public MainController() {
        new ViewWindow(this);

        startProgram();
    }

    /**
     * Erstellt die zwei benötigten Stacks und füllt stackOrigin mit Karten. Die Karten haben einen zufälligen Wert zwischen 0 und 20
     * Zufallswert: Z.b.: Math.random() -> Double zwischen 0 und 1
     */
    public void startProgram() {
        //TODO 08: Implementiere die Methode gemäß des Kommentars
        stackOrigin = new Stack<>();
        stackKeep = new Stack<>();

        for (int i = 0; i < 10; i++) {
            int zufallswert = (int) (Math.random() * 21);
            stackOrigin.push(new Card(zufallswert));
        }
    }

    /**
     * Wert der obersten Karte von stackOrigin wird ermittelt. Falls es keine oberste Karte gibt wird -1 zurückgegeben.
     * @return Wert der obersten Karte oder -1
     */
    public int showNextCard() {
        //TODO 09: Implementiere die Methode gemäß des Kommentars
        if(!cardStackEmpty()) {
            return stackOrigin.top().getWert();
        }
        return -1;
    }

    /**
     * Falls stackOrigin nicht leer ist, wird die oberste Karte von stackOrigin auf stackKeep gelegt.
     * @return true, falls eine Karte auf stackKeep gelegt wird, sonst false.
     */
    public boolean keep() {
        //TODO 10: Implementiere die Methode gemäß des Kommentars
        if(!cardStackEmpty()){
            stackKeep.push(stackOrigin.top());
            stackOrigin.pop();
            return true;
        }
        return false;
    }

    /**
     * Falls stackOrigin nicht leer ist, wird die oberste Karte von stackOrigin entfernt.
     * @return true, falls eine Karte auf stackOrigin entfernt wird, sonst false.
     */
    public boolean throwCard() {
        //TODO 11: Implementiere die Methode gemäß des Kommentars
        if(!cardStackEmpty()){
            stackOrigin.pop();
            return true;
        }
        return false;
    }

    /**
     * Zählt die Karten von stackKeep. Zunächst wird geprüft, ob stackKeep den Regeln entspricht (Nur Karten in aufsteigender Reihenfolge abgelegt).
     * Zählt anschließend die abgelegten Karten, falls stackKeep regelkonform ist.
     * @return Die Anzahl der abgelegten Karten, falls stackKeep regelkonform ist, sonst -1.
     */
    public int inspect() {
        //TODO 12: Implementiere die Methode gemäß des Kommentars
        if(!keepCorrect()) return -1;

        //Stack<Card> help = new Stack<>();
        int count = 0;
        while(!stackKeep.isEmpty()) {
            //help.push(stackKeep.top());
            stackKeep.pop();
            /*stackKeep.pop
            count++; Wenn man keinen Help Stack braucht kann man das auch einfach nur poppen und die anzahl davon sichern
             */
            count++;
        }

        /*while(!help.isEmpty()){
            stackKeep.push(help.top());
            help.pop();
        }*/
        return count;
    }

    /**
     * Prüft, ob stackKeep regelkonform ist. Dh. die Karten liegen (von oben nach unten) in absteigender Reihenfolge.
     * @return true, falls regelkonform. Sonst false.
     */
    public boolean keepCorrect() {
        Stack<Card> help = new Stack<>();
        while (!stackKeep.isEmpty()) {
            if (!help.isEmpty()) {
                if (stackKeep.top().getWert() < help.top().getWert()) {
                    help.push(stackKeep.top());
                    stackKeep.pop();
                } else {
                    return false;
                }
            } else {
                help.push(stackKeep.top());
                stackKeep.pop();
            }
        }
        stackKeep = help;
        return true;
    }

    public boolean cardStackEmpty() {
        return stackOrigin.isEmpty();
    }

}

