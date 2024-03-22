package com.traindiorama.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;

public class WindowController {
    private static boolean allwaysApplyDuty = false;
    private static double duty = 0D;

    //------------------------------------// FXML //------------------------------------//

    @FXML Slider mortorSliderDuty;
    @FXML Button mortorApplyButton;
    @FXML CheckBox mortorCheckboxAllwaysApply;

    @FXML
        public void onMouse() {
            if (allwaysApplyDuty) {
                duty = mortorSliderDuty.getValue();
            }
        }

    @FXML
        public void onCheckBoxClicked() {
            allwaysApplyDuty = mortorCheckboxAllwaysApply.selectedProperty().get();
        }

    @FXML
        public void onClicked() {
            duty = mortorSliderDuty.getValue();
        }

    //------------------------------------// FXML //------------------------------------//

    public static boolean allwaysApplyDuty() {
        return allwaysApplyDuty;
    }

    public static int getDutyAsInt() {
        return Math.toIntExact(Math.round(duty));
    }
}
