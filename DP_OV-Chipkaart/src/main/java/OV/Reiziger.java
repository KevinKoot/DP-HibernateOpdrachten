package OV;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "reiziger")
public class Reiziger {

    @Id
    @Column(name = "reiziger_id")
    private int id;
    @Column(name = "voorletters")
    private String voorLetters;
    @Column(name = "tussenvoegsel")
    private String tussenVoegsel;
    @Column(name = "achternaam")
    private String achterNaam;
    @Column(name = "geboortedatum")
    private Date geboorteDatum;




    public Reiziger(){

    }


    public Reiziger(int id, String voorletters, String tussenvoegsel, String achterNaam, Date geboorteDatum) {
        this.id = id;
        this.voorLetters = voorletters;
        this.tussenVoegsel = tussenvoegsel;
        this.achterNaam = achterNaam;
        this.geboorteDatum = geboorteDatum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVoorLetters() {
        return voorLetters;
    }

    public void setVoorLetters(String voorLetters) {
        this.voorLetters = voorLetters;
    }

    public String getTussenVoegsel() {
        return tussenVoegsel;
    }

    public void setTussenVoegsel(String tussenVoegsel) {
        this.tussenVoegsel = tussenVoegsel ;
    }

    public String getAchterNaam() {
        return achterNaam;
    }

    public void setAchterNaam(String achterNaam) {
        this.achterNaam = achterNaam;
    }

    public Date getGeboorteDatum() {
        return geboorteDatum;
    }

    public void setGeboorteDatum(Date geboorteDatum) {
        this.geboorteDatum = geboorteDatum;
    }

    public String toString(){
        if(tussenVoegsel != null){
            return "     #" + id + ": " + voorLetters + ". " + tussenVoegsel + " " + achterNaam + "  " + geboorteDatum;
        } else {
            return "     #" + id + ": " + voorLetters + ". " + achterNaam + "  (" + geboorteDatum + ")";

        }
    }
}
