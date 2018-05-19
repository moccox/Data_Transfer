package com.example.administrator.data_transfer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.text.TextUtils.isEmpty;


/**
 *充值界面
 * 1.组件：TextView显示余额数据
 *         Button实现充值功能、界面跳转（数据回传至主界面）
 *         相关textView提示界面功能和具体余额数据
 * 实现功能：app余额充值（将卡内余额充值到app余额），并且将最终数据回传到主界面
 */

public class RechargeActivity extends Activity{

    private TextView mmoneyshow = (TextView) this.findViewById(R.id.show);
    private Button mmakesure = (Button) this.findViewById(R.id.makesure);
    private Button mexit = (Button) this.findViewById(R.id.exit);
    private EditText mcount = (EditText) this.findViewById(R.id.count);

    private double mcard,mapp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);

        Intent intent1 =getIntent();
        mcard = intent1.getDoubleExtra("card_money",0);
        mapp=intent1.getDoubleExtra("app_money",0);


        mmoneyshow.setText("卡内余额："+mcard+"    应用余额："+mapp);

        mmakesure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isEmpty(mcount.getText().toString().trim())){
                    Toast.makeText(RechargeActivity.this,"请输入充值金额！",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (Double.valueOf(mcount.getText().toString().trim()) > mcard) {
                    Toast.makeText(RechargeActivity.this,"充值金额过大！",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(Double.valueOf(mcount.getText().toString().trim()) <= mcard){
                    mcard -= Double.valueOf(mcount.getText().toString().trim());
                    mapp += Double.valueOf(mcount.getText().toString().trim());
                    mmoneyshow.setText("卡内余额："+mcard+"    应用余额："+mapp);
                    Toast.makeText(RechargeActivity.this,"充值成功！",Toast.LENGTH_LONG).show();
                    return;
                 }
            }
        });

        mexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent();
                intent2.putExtra("cardresult",mcard);
                intent2.putExtra("appresult",mapp);
                setResult(2,intent2);

                finish();
            }
        });


    }

}
