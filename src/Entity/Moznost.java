package Entity;

public class Moznost {
    private String textMoznosti;
    private Boolean jeSpravna;

    public Moznost(String textMoznosti, Boolean jeSpravna) {
        this.textMoznosti = textMoznosti;
        this.jeSpravna = jeSpravna;
    }

    public String getTextMoznosti() {
        return textMoznosti;
    }

    public void setTextMoznosti(String textMoznosti) {
        this.textMoznosti = textMoznosti;
    }

    public Boolean getJeSpravna() {
        return jeSpravna;
    }

    public void setJeSpravna(Boolean jeSpravna) {
        this.jeSpravna = jeSpravna;
    }
}
