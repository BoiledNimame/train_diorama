package com.traindiorama.gui;

import com.traindiorama.Main;
import com.traindiorama.pulse.FrequencyData;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;

public class WindowController {
    private static boolean allwaysApplyDuty = false;
    private static double duty = 0D;

    //------------------------------------// FXML //------------------------------------//

    @FXML Slider mortorSliderDuty;
    @FXML CheckBox mortorCheckboxAllwaysApply;

    @FXML
        public void onMouse() {
            if (allwaysApplyDuty) {
                duty = mortorSliderDuty.getValue();
                applyDuty(false);
            }
        }

    @FXML
        public void onCheckBoxClicked() {
            allwaysApplyDuty = mortorCheckboxAllwaysApply.selectedProperty().get();
        }

    @FXML
        public void onClicked() {
            duty = mortorSliderDuty.getValue();
            applyDuty(true);
        }

    @FXML
        public void callStop() {
            Main.getController().stopPwm(FrequencyData.id);
        }

    //------------------------------------// FXML //------------------------------------//

    public static int getDutyAsInt() {
        return Math.toIntExact(Math.round(duty));
    }

    private static void applyDuty(boolean dump) {
        Main.getController().applyDuty(FrequencyData.id, getDutyAsInt(), dump);
    }
}
