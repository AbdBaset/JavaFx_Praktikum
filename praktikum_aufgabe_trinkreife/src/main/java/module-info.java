module de.whs.mci.praktikum_ufgabe_trinkreife {
    requires javafx.controls;
    requires javafx.fxml;

    opens de.whs.mci.praktikum_ufgabe_trinkreife to javafx.fxml;
    exports de.whs.mci.praktikum_ufgabe_trinkreife;
    requires javafx.graphics;
    requires java.base;
}
