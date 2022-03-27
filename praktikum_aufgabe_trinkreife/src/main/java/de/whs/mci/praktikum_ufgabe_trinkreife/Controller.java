package de.whs.mci.praktikum_ufgabe_trinkreife;

import static java.lang.Math.abs;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;

/**
 * Aktuelles
 *
 * @author Abidi
 */
public class Controller {

    // alle rectangle
    @FXML
    private Rectangle bereichZuFrueh, bereichAufsteigend, bereichOptimal;
    private Rectangle links_balkon;
    @FXML
    private Rectangle bereichAbsteigend, bereichUngeniessbar,linkesSchieber;
    // alle label
    @FXML
    private Label anfangsJahrdesSpanne, aktuellesJahr, endesJahrderSpanne, leerLabel;
    //alle static konstant
    private static final double bereichZufruehProzent = 1 / 8.0, bereichAufsteigendProzent = 5 / 16.0;
    private static final double bereichOptimalProzent = 0.5, bereichAbsteigendProzent = 1 / 16.0;
    private static final int ANFANGSJAHR_DESBALKON = 2016, ENDSJAHR_DESBALKON = 2026, ALTESTE_JAHRWEIN = 1850;
    private static final double JAHR_BREITE = 50.0,BALKON_HOEHE=50.0;
    private static double GesamteBreite;
    private static double GesamteBreitederBalkon;
    // alle static variablen 
    public static int gesamtJahr;
    public static double rectanglenBereite, bereichZuFruehBereite, bereichAufsteigendbereite;
    public static double bereichOptimalBereite, bereichAbsteigendBereite, linkesBalkonBereit;
    private static double AnfangsleerBereich;
    private final static int ZAHL_NULL = 0;
    @FXML
    private HBox hbox;

    public void trinkwein_diagramm(int jahrgang_des_Weins, int Aktuelle_Jahr, int lagedauer) {
        // Inializierte zustand 
        GesamteBreitederBalkon = 600.0;
        leerLabel.setText("");
        anfangsJahrdesSpanne.setText("");
        endesJahrderSpanne.setText("");
        leerLabel.setPrefWidth(0.0);
        anfangsJahrdesSpanne.setPrefWidth(0.0);
        endesJahrderSpanne.setPrefWidth(0.0);
        linkesSchieber.setWidth(0.0);
        bereichZuFrueh.setWidth(0.0);
        bereichAufsteigend.setWidth(0.0);
        bereichOptimal.setWidth(0.0);
        bereichAbsteigend.setWidth(0.0);
        bereichUngeniessbar.setWidth(0.0);
        if (lagedauer < 0 || lagedauer > 1000 || jahrgang_des_Weins < ALTESTE_JAHRWEIN
                || jahrgang_des_Weins > Aktuelle_Jahr) {
            throw new IllegalArgumentException("bitte kontrollieren Sie Ihre Eingabe ");
        } else {
            //--------------lebel breite rechnen  -------------------
            leerLabel.setPrefWidth((jahrgang_des_Weins >= ANFANGSJAHR_DESBALKON)
                    ? ((jahrgang_des_Weins + 1) - ANFANGSJAHR_DESBALKON) * JAHR_BREITE
                    : 0.0);
            anfangsJahrdesSpanne.setPrefWidth((jahrgang_des_Weins >= ANFANGSJAHR_DESBALKON)
                    ? (((jahrgang_des_Weins + lagedauer + 1) > ENDSJAHR_DESBALKON))
                            ? GesamteBreitederBalkon - leerLabel.getPrefWidth()
                            : ((lagedauer + 1) * JAHR_BREITE)
                    : ((jahrgang_des_Weins + lagedauer + 1 > ENDSJAHR_DESBALKON))
                            ? GesamteBreitederBalkon
                            : (GesamteBreitederBalkon + JAHR_BREITE) - (((ENDSJAHR_DESBALKON + 2) - (jahrgang_des_Weins + lagedauer + 1)) * JAHR_BREITE));
            endesJahrderSpanne.setPrefWidth(((jahrgang_des_Weins + lagedauer + 1 > ENDSJAHR_DESBALKON))
                    ? (JAHR_BREITE)
                    : (jahrgang_des_Weins + lagedauer + 1 >= (ANFANGSJAHR_DESBALKON - 1))
                            ? (((ENDSJAHR_DESBALKON + 2) - (jahrgang_des_Weins + lagedauer + 1)) * JAHR_BREITE)
                            : 0.0);
            anfangsJahrdesSpanne.setText((anfangsJahrdesSpanne.getPrefWidth() > 0.0)
                    && (jahrgang_des_Weins != Aktuelle_Jahr) ? jahrgang_des_Weins + "" : "");
            aktuellesJahr.setText(Aktuelle_Jahr + "");
            endesJahrderSpanne.setText(
                    (endesJahrderSpanne.getPrefWidth() > 0.0) && ((jahrgang_des_Weins + lagedauer + 1) != Aktuelle_Jahr)
                    ? jahrgang_des_Weins + lagedauer + 1 + "" : "");
            //-------------------rectangle breite berechnen --------------------

            if ((jahrgang_des_Weins + lagedauer + 1) < ANFANGSJAHR_DESBALKON) {

            } else {
                AnfangsleerBereich = (jahrgang_des_Weins - ANFANGSJAHR_DESBALKON) * JAHR_BREITE;
                gesamtJahr = lagedauer + 1;
                linkesSchieber.setWidth(AnfangsleerBereich);
                GesamteBreite = gesamtJahr * JAHR_BREITE;
                bereichZuFruehBereite = GesamteBreite * bereichZufruehProzent;
                bereichAufsteigendbereite = GesamteBreite * bereichAufsteigendProzent;
                bereichOptimalBereite = GesamteBreite * bereichOptimalProzent;
                bereichAbsteigendBereite = GesamteBreite * bereichAbsteigendProzent;
                bereichZuFrueh.setWidth(bereichZuFruehBereite);
                bereichAufsteigend.setWidth(bereichAufsteigendbereite);
                bereichOptimal.setWidth(bereichOptimalBereite);
                bereichAbsteigend.setWidth(bereichAbsteigendBereite);
                bereichUngeniessbar.setWidth((jahrgang_des_Weins + lagedauer + 1 >= ANFANGSJAHR_DESBALKON
                        && jahrgang_des_Weins + lagedauer + 1 <= ENDSJAHR_DESBALKON) ? JAHR_BREITE : ZAHL_NULL);
                // unsere maske rechts und links berechnen
                if (AnfangsleerBereich < ZAHL_NULL && (jahrgang_des_Weins + lagedauer < ENDSJAHR_DESBALKON)) {
                    Rectangle maskelinks = new Rectangle();
                    maskelinks.setWidth(10 * JAHR_BREITE);
                    maskelinks.setHeight(BALKON_HOEHE);
                    maskelinks.setX(abs(AnfangsleerBereich));
                    hbox.setLayoutX(JAHR_BREITE * (jahrgang_des_Weins - (ANFANGSJAHR_DESBALKON - 1)));
                    hbox.setClip(maskelinks);
                } else if (AnfangsleerBereich < ZAHL_NULL && jahrgang_des_Weins + lagedauer >= ENDSJAHR_DESBALKON) {
                    Rectangle maske = new Rectangle();
                    maske.setWidth(11 * JAHR_BREITE);
                    maske.setHeight(BALKON_HOEHE);
                    maske.setX(abs(AnfangsleerBereich));
                    hbox.setLayoutX(JAHR_BREITE * (jahrgang_des_Weins - (ANFANGSJAHR_DESBALKON - 1)));
                    hbox.setClip(maske);
                } else {
                    hbox.setLayoutX(JAHR_BREITE);
                }
                if (GesamteBreite + AnfangsleerBereich > (11 * JAHR_BREITE) &&(AnfangsleerBereich>=0)) {
                    Rectangle maskerechts = new Rectangle();
                    maskerechts.setWidth(11 * JAHR_BREITE);
                    maskerechts.setHeight(BALKON_HOEHE);
                    hbox.setClip(maskerechts);

                }
            }
        }
    }

}
