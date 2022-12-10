module csci205_final_project{
    requires java.base;
    requires java.desktop;
    requires java.sql;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    exports org.Fearsome_Foursome.Creatures;
    opens org.Fearsome_Foursome.Creatures to javafx.fxml;

    exports org.Fearsome_Foursome.Battle;
    opens org.Fearsome_Foursome.Battle to javafx.fxml;

    exports org.Fearsome_Foursome.Moves;
    opens org.Fearsome_Foursome.Moves to javafx.fxml;

    exports org.Fearsome_Foursome.Application.Controllers;
    opens org.Fearsome_Foursome.Application.Controllers to javafx.fxml;

    exports org.Fearsome_Foursome.Application;
    opens org.Fearsome_Foursome.Application to javafx.fxml;
}