package Entity;

public class User implements Comparable<User>{
    private Integer userId;
    private String meno;
    private String priezvisko;
    private String email;
    private String heslo;
    private Integer skore;
    private Integer jeAdmin; //hodnota 1 je admin, 0 nie je admin

    public User(String meno, String priezvisko, String email, String heslo) {
        this.meno = meno;
        this.priezvisko = priezvisko;
        this.email = email;
        this.heslo = heslo;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getMeno() {
        return meno;
    }

    public String getPriezvisko() {
        return priezvisko;
    }

    public String getEmail() {
        return email;
    }

    public String getHeslo() {
        return heslo;
    }

    public Integer getSkore() {
        return skore;
    }

    public Integer getJeAdmin() {
        return jeAdmin;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }

    public void setPriezvisko(String priezvisko) {
        this.priezvisko = priezvisko;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setHeslo(String heslo) {
        this.heslo = heslo;
    }

    public void setSkore(Integer skore) {
        this.skore = skore;
    }

    public void setJeAdmin(Integer jeAdmin) {
        this.jeAdmin = jeAdmin;
    }

    @Override
    public int compareTo(User o) {
        //zostupne
        return o.getSkore().compareTo(this.skore);
        //vzostupne
        //return this.skore.compareTo(o.getSkore());
    }
}
