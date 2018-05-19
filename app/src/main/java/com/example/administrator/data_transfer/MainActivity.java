package com.example.administrator.data_transfer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 主界面
 * 1.组件：TextView显示余额数据
 *         ImageView显示照片
 *         Button实现界面跳转：a.充值按钮跳转至充值界面，b.圆形按钮跳转至拍照界面
 * 2.实现功能：界面跳转，等待数据回传，并根据回传数据修改成员变量
 * */
public class MainActivity extends AppCompatActivity {

    //添加组件
    private Button mrecharg = (Button) this.findViewById(R.id.recharge);
    private ImageView mputphoto = (ImageView) this.findViewById(R.id.putphoto);
    private TextView mmoney = (TextView) this.findViewById(R.id.money);
    private ImageView mtakephoto = (ImageView) this.findViewById(R.id.takephoto);


    //记录数据
    private double mcard = 300.0, mapp=20.0;    //卡内余额（mcard） 应用余额（mapp）

    //设置各个界面的返回值
    private final int GO_TO_RECHARGE = 1;   //充值界面返回值为1

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mmoney.setText("卡内余额："+mcard+"        应用余额："+mapp);
        /**
         * 充值按钮监听设置
         **/
        mrecharg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //跳转到充值界面

                Intent intent1=new Intent();
                intent1.setAction("com.example.administrator.data_transfer.RECHARGE");
                intent1.addCategory("android.intent.category.DEFAULT");
                intent1.putExtra("card_money",mcard);
                intent1.putExtra("app_money",mapp);
                startActivityForResult(intent1,GO_TO_RECHARGE);

            }
        });


     }
    /**
     * 回调处理及结果
     **/

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        if(requestCode == GO_TO_RECHARGE){
            //处理充值界面回传
            if(resultCode == 2){
                mcard=data.getDoubleExtra("cardresult",mcard);
                mapp=data.getDoubleExtra("appresult",mapp);
                mmoney.setText("卡内余额："+mcard+"        应用余额："+mapp);
            }
            else{
                mmoney.setText("回传出错了");
            }
        }
    }

}
