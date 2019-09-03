package com.zucc.smartfurniture;
import com.zucc.smartfurniture.baiduvoice.DigitalDialogInput;
/**
 * Created by fujiayi on 2017/10/18.
 */

public class SimpleTransApplication extends MyApplication {
    private DigitalDialogInput digitalDialogInput;
    public DigitalDialogInput getDigitalDialogInput() {
        return digitalDialogInput;
    }

    public void setDigitalDialogInput(DigitalDialogInput digitalDialogInput) {
        this.digitalDialogInput = digitalDialogInput;
    }
}
