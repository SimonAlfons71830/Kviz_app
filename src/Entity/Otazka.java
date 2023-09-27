package Entity;

import java.util.ArrayList;

public class Otazka {
    private String textOtazky;
    private ArrayList<Moznost> moznosti;

    public Otazka(String textOtazky, ArrayList<Moznost> moznosti) {
        this.textOtazky = textOtazky;
        this.moznosti = moznosti;
    }

    public String getTextOtazky() {
        return textOtazky;
    }

    public void setTextOtazky(String textOtazky) {
        this.textOtazky = textOtazky;
    }

    public ArrayList<Moznost> getMoznosti() {
        return moznosti;
    }

    public void setMoznosti(ArrayList<Moznost> moznosti) {
        this.moznosti = moznosti;
    }
}
