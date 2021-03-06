package com.bw.paydemo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.bw.paydemo.pay.PayResult;
import com.bw.paydemo.pay.db.DaoSession;
import com.bw.paydemo.pay.db.UserMoneyDao;
import com.bw.paydemo.pay.entity.UserMoney;
import com.bw.paydemo.pay.util.MoneyManager;
import com.bw.paydemo.pay.util.OrderInfoUtil2_0;
import com.youth.banner.Banner;

import java.util.List;
import java.util.Map;

public class PayMainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button payMainPayBtn;

    private Double money = 50.0;
    private TextView payMainMoneyText;
    private LinearLayout payMainRadioGroup;
    private CheckBox payMainRadio50Yuan;
    private CheckBox payMainRadio80Yuan;
    private CheckBox payMainRadio120Yuan;
    private CheckBox payMainRadio200Yuan;
    private CheckBox payMainRadio340Yuan;
    private CheckBox payMainRadio648Yuan;

    private DaoSession daoSession;
    private int nowMoneyPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pay);

        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);


        initView();


        payMainPayBtn.setOnClickListener(this);

        payMainRadio50Yuan.setOnClickListener(this);
        payMainRadio80Yuan.setOnClickListener(this);
        payMainRadio120Yuan.setOnClickListener(this);
        payMainRadio200Yuan.setOnClickListener(this);
        payMainRadio340Yuan.setOnClickListener(this);
        payMainRadio648Yuan.setOnClickListener(this);

        daoSession = MoneyManager.getInstance(PayMainActivity.this).getDaoSession();

        refreshMoney();

    }

    private void refreshMoney() {

        //????????????
        List<UserMoney> userMonies = daoSession.loadAll(UserMoney.class);
        //???????????????id ??????
        nowMoneyPosition = userMonies.size();

        //???????????????????????????
        if (nowMoneyPosition == -1){
            payMainMoneyText.setText(""+0.0);
        }else {
            //??????????????????
            UserMoney userNow = daoSession.queryBuilder(UserMoney.class).where(UserMoneyDao.Properties.Id.eq(nowMoneyPosition)).build().unique();

            //??????Money
            Double money = userNow.getMoney();


            payMainMoneyText.setText(""+money);
        }

    }


    /**
     * ???????????????????????????????????? app_id???
     */
    public static final String APPID = "2021000118607477";

    /**
     * ???????????????????????????????????????????????? pid???
     */
    public static final String PID = "";

    /**
     * ???????????????????????????????????????????????? target_id???
     */
    public static final String TARGET_ID = "";

    /**
     * pkcs8 ????????????????????????
     * <p>
     * ???????????????RSA2_PRIVATE ?????? RSA_PRIVATE ?????????????????????????????????????????????????????? Demo ?????????
     * ?????? RSA2_PRIVATE???RSA2_PRIVATE ??????????????????????????????????????????????????????????????????????????????
     * RSA2_PRIVATE???
     * <p>
     * ?????????????????????????????????????????????????????????????????? RSA2_PRIVATE???
     * ???????????????https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1
     */
    public static final String RSA2_PRIVATE = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDAfzVSn57Pcg3RQ6bbV0UiXISlqEHHqpsApcWYR484xiMOSaeIUY5Hi3Qgj/8AhxZdNXuZYBU3zvRWbNWm0ILpTYx3it6TMrfCUNGdVRsNri7vTvbxXVknzcWp7if2JGavuzCt7HiqTH1oFYvxOR1+z2D/w+aOIt6BlJZZQWATXi+dd74pcaTN/c0vYCxFldXdZnMoYUcyrHgvXI3UYoGCL8/RIvCGvJZhrd/0kP+SPCCnx9IGlYJsMYQeX9e0xSX/XuqY2n/HdsGfk233dWreHTx0b0qYXAYers88QjKGWxAhJ5089UyQ+OTaxeFCchTkyvvoioHYpTZoMzv8PQH9AgMBAAECggEAMICc7emcOUbSpnS2WTP/Dm5jWd4wXN/m2F3skLcPJk3+TT5PwTBTekFwDDhcF0TyZJ/yOa4VCo/RzujzWGnXYnOQvho2hL5VwNdeSZdFDshhuRibbiuIKFhXwr5oQUPgv8yZsmkP6lEFlDFBppEulmIoXal9sTil+d53l5u6WoaD1H4216OuklpW/oesXKrV3jUktDUAm7zdpoUIzOClHp4fvtRXcASMeNnPU+fCXr0scXIet/iRZ37VZ2FX7HU/OPUQp3FwhfLjRIFAdvWQvzKKwI5mmlQMOBZfup5Qk2tYxQcuBuUblWUgPtBLB44hA+mt26FQlld4Pgy+BjtkfQKBgQDpZ3lkmEbp02u1am1YIdf+W2EBucuh+7uLxQJHTx4yLuuWwJYLbGt3SMBTJ4jkFuEVio6/v9C4lzZj97tprDHvFcoqPBn33BYqlNCjnl+04sZdjW16ReXFPMjHJWAD3oaIwQcbbRW63IcdYCAZDgLRBUQcECdWdm39HlGehNYsXwKBgQDTIeqj89HJaVctImfI1StsfY5lvMFCC93ZT1IF3+vNu21M0OzKvgM6JNB2trDKYNZR6A38dl+wHJHgsbErJDLaazJYRIj4C9GOenOWHYWliW9N2vl0iI6dln+jNj7LNVeabDxNkOH+5lZgJyfqh9p9d0Y7D0vNnJlRO4qUmWCvIwKBgAine4rNUqPM4QuC/Lbt2lJmFPy5ZOGGsfeGwITgXCBEORwH3AhkZr+vhOIQbzT13DauQoi9lgGiKbQfNWW/qsJQroROYjAciv3dMrc+YhIU7oH9gnesK87aaB9qNVsvXxGnBppvuwSItMHUFJyZNmWFqnbX6n18OqvN+fzEtCRFAoGASOATGoZFKi03DvgqNJMHywnHhexSFJb9+006tZZl0PxZrMMMiKpveLlOTzud6CzmrRzY/wRo4OProlATJB35g5SuqvL1CBEkQvaXQQBtqga3KXtk0Ul1WIWjBtXpdMCzvTSQ2AEjUJV2yPea+oPr8ZSi5C3Sb2UXMofHvKT4T1sCgYEAo4O9oU2jY06DR/aLQy1vodHCAFrb3swOFRmFh9QUu3Lbix1AdSjQZ2ZEqetk1LCFtO2KNlEtgZ+ytmfvU6zITP4mvn/Aey6It85e+6ZlnXAZAH+V76ZY7RXtIJ4LctVHr1RKW7yw1KIdidp4F//gB0GLR7ZZ6sreRg4Gy4wR/Cs=";
    public static final String RSA_PRIVATE = "";

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     * ???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
                     */
                    String resultInfo = payResult.getResult();// ?????????????????????????????????
                    String resultStatus = payResult.getResultStatus();
                    // ??????resultStatus ???9000?????????????????????
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // ??????????????????????????????????????????????????????????????????????????????
                        Toast.makeText(PayMainActivity.this, "????????????", Toast.LENGTH_SHORT).show();

                        UserMoney money1 = daoSession.queryBuilder(UserMoney.class).where(UserMoneyDao.Properties.Id.eq(nowMoneyPosition)).unique();

                        UserMoney userMoney = new UserMoney();
                        userMoney.setMoney(PayMainActivity.this.money +money1.getMoney());
                        daoSession.insert(userMoney);




                    } else {
                        // ???????????????????????????????????????????????????????????????????????????
                        Toast.makeText(PayMainActivity.this, "????????????", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
            }
        }
    };

    public void payV2(Double money) {
        if (TextUtils.isEmpty(APPID) || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))) {
            Toast.makeText(PayMainActivity.this, "????????????", Toast.LENGTH_SHORT).show();
            return;
        }

        /*
         * ????????????????????????????????????????????????????????????????????????????????????Demo?????????????????????????????????????????????
         * ??????App??????privateKey??????????????????????????????????????????????????????????????????????????????
         * ????????????????????????????????????????????????????????????????????????????????????????????????
         *
         * orderInfo ?????????????????????????????????
         */
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa2, money);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
        final String orderInfo = orderParam + "&" + sign;

        final Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(PayMainActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // ??????????????????
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    private void initView() {
        payMainPayBtn = findViewById(R.id.pay_main_pay_btn);
        payMainMoneyText = findViewById(R.id.pay_main_money_text);
        payMainRadioGroup = findViewById(R.id.pay_main_radio_group);
        payMainRadio50Yuan = findViewById(R.id.pay_main_radio_50_yuan);
        payMainRadio80Yuan = findViewById(R.id.pay_main_radio_80_yuan);
        payMainRadio120Yuan = findViewById(R.id.pay_main_radio_120_yuan);
        payMainRadio200Yuan = findViewById(R.id.pay_main_radio_200_yuan);
        payMainRadio340Yuan = findViewById(R.id.pay_main_radio_340_yuan);
        payMainRadio648Yuan = findViewById(R.id.pay_main_radio_648_yuan);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.pay_main_radio_50_yuan) {
            payMainRadio50Yuan.setChecked(true);

            payMainRadio120Yuan.setChecked(false);
            payMainRadio200Yuan.setChecked(false);
            payMainRadio340Yuan.setChecked(false);
            payMainRadio648Yuan.setChecked(false);
            payMainRadio80Yuan.setChecked(false);

            money = 50.0;

        }else if (id == R.id.pay_main_radio_80_yuan){

            payMainRadio50Yuan.setChecked(false);
            payMainRadio120Yuan.setChecked(false);
            payMainRadio200Yuan.setChecked(false);
            payMainRadio340Yuan.setChecked(false);
            payMainRadio648Yuan.setChecked(false);
            payMainRadio80Yuan.setChecked(true);

            money = 80.0;

        }else if (id == R.id.pay_main_radio_120_yuan){

            payMainRadio50Yuan.setChecked(false);
            payMainRadio120Yuan.setChecked(true);
            payMainRadio200Yuan.setChecked(false);
            payMainRadio340Yuan.setChecked(false);
            payMainRadio648Yuan.setChecked(false);
            payMainRadio80Yuan.setChecked(false);

            money = 120.0;

        }else if (id == R.id.pay_main_radio_200_yuan){

            payMainRadio50Yuan.setChecked(false);
            payMainRadio120Yuan.setChecked(false);
            payMainRadio200Yuan.setChecked(true);
            payMainRadio340Yuan.setChecked(false);
            payMainRadio648Yuan.setChecked(false);
            payMainRadio80Yuan.setChecked(false);

            money = 200.0;

        }else if (id == R.id.pay_main_radio_340_yuan){

            payMainRadio50Yuan.setChecked(false);
            payMainRadio120Yuan.setChecked(false);
            payMainRadio200Yuan.setChecked(false);
            payMainRadio340Yuan.setChecked(true);
            payMainRadio648Yuan.setChecked(false);
            payMainRadio80Yuan.setChecked(false);

            money = 340.0;

        }else if (id == R.id.pay_main_radio_648_yuan){

            payMainRadio50Yuan.setChecked(false);
            payMainRadio120Yuan.setChecked(false);
            payMainRadio200Yuan.setChecked(false);
            payMainRadio340Yuan.setChecked(false);
            payMainRadio648Yuan.setChecked(true);
            payMainRadio80Yuan.setChecked(false);

            money = 648.0;

        }else if (id == R.id.pay_main_pay_btn){

            if (payMainRadio50Yuan.isChecked()||payMainRadio80Yuan.isChecked()||payMainRadio120Yuan.isChecked()||payMainRadio200Yuan.isChecked() ||payMainRadio340Yuan.isChecked()||payMainRadio648Yuan.isChecked()) {
                payV2(money);
            }
            else {
                Toast.makeText(this, "???????????????", Toast.LENGTH_SHORT).show();
            }


        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshMoney();
    }
}