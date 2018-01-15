package ir.plusrobot.dairyapp;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

public class AboutDialog extends Dialog
    implements View.OnClickListener{


    public AboutDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_about_us);

        findViewById(R.id.iv_close).setOnClickListener(this);

        setCancelable(false);

        TextView tvContent = (TextView) findViewById(R.id.tv_content);
        tvContent.setText("این برنامه توسط تیم برنامه نویسی قدسیه تهیه گردیده." + "\n"
        + "آی دی تلگرام: @ghodsie" + "\n"
        + "شماره تماس: 09111111266");


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.iv_close:
                this.dismiss();//Close the Dialog
                break;

            default:
                break;
        }
    }


}
