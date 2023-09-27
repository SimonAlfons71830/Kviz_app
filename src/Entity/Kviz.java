package Entity;

import java.util.ArrayList;

public class Kviz {
    private String nazovKvizu;
    private Kategoria kategoria;
    private ArrayList<Otazka> otazky;
    private Integer obtiaznost; //1..5

    //defaultny konstruktor


    public String getNazovKvizu() {
        return nazovKvizu;
    }

    public void setNazovKvizu(String nazovKvizu) {
        this.nazovKvizu = nazovKvizu;
    }

    public Kategoria getKategoria() {
        return kategoria;
    }

    public void setKategoria(Kategoria kategoria) {
        this.kategoria = kategoria;
    }

    public ArrayList<Otazka> getOtazky() {
        return otazky;
    }

    public void setOtazky(ArrayList<Otazka> otazky) {
        this.otazky = otazky;
    }

    public Integer getObtiaznost() {
        return obtiaznost;
    }

    public void setObtiaznost(Integer obtiaznost) {
        this.obtiaznost = obtiaznost;
    }

    /**
     * metoda, ktora do AL otazok prida novu otazku, ktora je vs. parametrom metodt
     * v pripade ze AL nieje inicializovany, vytvori sa a az potom sa do neho vlozi otazka
     * @param novaOtazka
     */
    public void pridajOtazku(Otazka novaOtazka){
        if (this.otazky == null){
            this.otazky = new ArrayList<>();
        }
        this.otazky.add(novaOtazka);
    }


}
