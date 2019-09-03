package com.zucc.smartfurniture.views.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zucc.smartfurniture.R;
import com.zucc.smartfurniture.module.mine.model.DeviceSelectListener;

/**
 * Author: shenzh
 * E-mail: shenzh@erongdu.com
 * Date: 2018/3/6$ 15:03$
 * <p/>
 */
public class CustomizationDeviceAddDialog extends Dialog implements View.OnClickListener {
    private View view;
    private TextView open;
    private TextView close;
    private DeviceSelectListener deviceSelectListener;
    public CustomizationDeviceAddDialog(Context context,DeviceSelectListener deviceSelectListener){
        super(context, R.style.Dialog);
        this.deviceSelectListener = deviceSelectListener;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.mine_dialog_customization_add,null);
        Window window = getWindow();
        window.setContentView(view);
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        window.setWindowAnimations(R.style.PopupAnimation);
        open= (TextView) view.findViewById(R.id.open);
        close = (TextView) view.findViewById(R.id.close);
        open.setOnClickListener(this);
        close.setOnClickListener(this);
        final LinearLayout layout = (LinearLayout) view.findViewById(R.id.layout);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int height = layout.getTop();
                int y      = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                System.out.println("height="+height);
                System.out.println("y="+y);
                return true;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.open:
                deviceSelectListener.open();
                dismiss();
                break;
            case R.id.close:
                deviceSelectListener.close();
                dismiss();
                break;
        }
    }
}
