module by.miapr.maximins {
    requires javafx.controls;
    requires javafx.fxml;

    opens by.miapr.maximins.scenes.controllers to javafx.fxml;

    exports by.miapr.maximins;
    exports by.miapr.maximins.alghoritm;
    exports by.miapr.maximins.alghoritm.data;
}
