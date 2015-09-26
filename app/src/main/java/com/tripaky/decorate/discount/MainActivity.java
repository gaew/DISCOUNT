package com.tripaky.decorate.discount;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ProgressBar mProgressBar, disProgressBar;
    private Handler handler = new Handler();
    public String result_p, result_d, price, disp1, disp2, disp3, disp4, disb1, disb2, disb3, disb4 = "1";
    public static Double result;
    public TextView Result_price, Result_discount, unt,priceShow,discountShow;
    public EditText Input, price_input, field1, field2, field3, field4;
    public Switch swith;
    public boolean switch_chood;
    public int onstart;
    public Double discount1=0.0, discount2=0.0, discount3=0.0, discount4=0.0, discount5=0.0,price_out=0.0,price_in =0.0 ;
    LinearLayout add_field;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgressBar = (ProgressBar) findViewById(R.id.circle_progress_bar);
        disProgressBar = (ProgressBar) findViewById(R.id.circle_progress_bar2);

        Button btn = (Button) findViewById(R.id.button);

        Result_price = (TextView) findViewById(R.id.result_price);
        Result_discount = (TextView) findViewById(R.id.result_discount);
        unt = (TextView) findViewById(R.id.index_unit);
        priceShow = (TextView) findViewById(R.id.price_show_inring);
        discountShow = (TextView) findViewById(R.id.discount_show_inring);

        Input = (EditText) findViewById(R.id.percent_int);
        price_input = (EditText) findViewById(R.id.price_input);

        swith = (Switch) findViewById(R.id.swith_s);

        add_field = (LinearLayout) findViewById(R.id.container);

        swith.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    unt.setText("บาท");
                } else {

                    unt.setText("%");
                }
            }
        });

        Input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                AfterText_change();
            }
        });

        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((!price_input.getText().toString().trim().isEmpty())&&(!Input.getText().toString().trim().isEmpty())) {
                    price = price_input.getText().toString();
                } else
                    Toast.makeText(getApplicationContext(), "กรุณาใส่ราคาสินค้าและส่วนลด", Toast.LENGTH_SHORT).show();

                read_discount();
                cal_discount();

                Result_price.setText( String.format( "%.2f"+"฿", price_out ) );
                Result_discount.setText( String.format("%.2f"+"฿",(price_in-price_out)));
                play1();
                play2();
            }
        });
    }

    private void play1() {
        new Thread(new Runnable() {
            int i = 0;
            int progressStatus = 0;

            public void run() {
              //  Double result =  price_out/price_in*100;
                int go = ( (Double) Math.ceil( price_out/price_in*100 ) ).intValue();
               // int go = result.intValue();
                while (progressStatus < go) {
                    progressStatus += 1;
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // Update the progress bar
                    handler.post(new Runnable() {
                        public void run() {
                            mProgressBar.setProgress(progressStatus);
                            priceShow.setText(""+progressStatus+"%");

                            i++;
                        }
                    });

                }

            }
        }).start();
    }

    private void play2() {
        new Thread(new Runnable() {
            int i = 0;
            int progressStatus = 0;

            public void run() {
              //  Double result = (price_in-price_out)/price_in*100;
                int go = ( (Double) ((price_in-price_out)/price_in*100 )).intValue();
              //  int go = result.intValue();
                while (progressStatus < go) {
                    progressStatus += 1;
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // Update the progress bar
                    handler.post(new Runnable() {
                        public void run() {
                            disProgressBar.setProgress(progressStatus);
                            discountShow.setText("" + progressStatus +"%");
                            i++;
                        }
                    });
                }
            }
        }).start();
    }

    private void AfterText_change() {
        if (Input.length() == 1) {
            if (onstart == 0) {
                onstart = 1;
                LayoutInflater layoutInflater2 = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View addView = layoutInflater2.inflate(R.layout.discount_field, null);
                field1 = (EditText) addView.findViewById(R.id.field_input);
                add_field.addView(addView);
                field1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        if (field1.length() == 1) {
                            if (onstart == 1) {
                                onstart = 2;
                                LayoutInflater layoutInflater2 = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                final View addView = layoutInflater2.inflate(R.layout.discount_field, null);
                                field2 = (EditText) addView.findViewById(R.id.field_input);
                                add_field.addView(addView);
                                field2.addTextChangedListener(new TextWatcher() {
                                    @Override
                                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                    }

                                    @Override
                                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                                    }

                                    @Override
                                    public void afterTextChanged(Editable s) {
                                        if (field2.length() == 1) {
                                            if (onstart == 2) {
                                                onstart = 3;
                                                LayoutInflater layoutInflater2 = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                                final View addView = layoutInflater2.inflate(R.layout.discount_field, null);
                                                field3 = (EditText) addView.findViewById(R.id.field_input);
                                                add_field.addView(addView);
                                                field3.addTextChangedListener(new TextWatcher() {
                                                    @Override
                                                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                                    }

                                                    @Override
                                                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                                                    }

                                                    @Override
                                                    public void afterTextChanged(Editable s) {
                                                        if (field3.length() == 1) {
                                                            if (onstart == 3) {
                                                                onstart = 4;
                                                                LayoutInflater layoutInflater2 = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                                                final View addView = layoutInflater2.inflate(R.layout.discount_field, null);
                                                                field4 = (EditText) addView.findViewById(R.id.field_input);
                                                                add_field.addView(addView);
                                                            }
                                                        }

                                                    }
                                                });
                                            }
                                        }

                                    }
                                });

                            }
                        }
                    }
                });
            }

        }

    }

    private void read_discount() {
        switch (onstart) {
            case 0:
                if (!Input.getText().toString().trim().isEmpty()) {
                    discount1 = Double.parseDouble(Input.getText().toString());
                }else discount1 = 0.0;
                break;
            case 1:
                if (!Input.getText().toString().trim().isEmpty()) {
                    discount1 = Double.parseDouble(Input.getText().toString());
                }else
                if (!field1.getText().toString().trim().isEmpty()) {
                    discount2 = Double.parseDouble(field1.getText().toString());
                }
                else if(Input.getText().toString().trim().isEmpty()){
                    discount1 = 0.0;
                }else if(field1.getText().toString().trim().isEmpty()){
                    discount2 = 0.0;
                }
                break;
            case 2:
                if (!Input.getText().toString().trim().isEmpty()) {
                    discount1 = Double.parseDouble(Input.getText().toString());
                }
                if (!field1.getText().toString().trim().isEmpty()) {
                    discount2 = Double.parseDouble(field1.getText().toString());
                }
                if (!field2.getText().toString().trim().isEmpty()) {
                    discount3 = Double.parseDouble(field2.getText().toString());
                }
                else if(Input.getText().toString().trim().isEmpty()){
                    discount1 = 0.0;
                }else if(field1.getText().toString().trim().isEmpty()){
                    discount2 = 0.0;
                }else if(field2.getText().toString().trim().isEmpty()){
                    discount3 = 0.0;
                }
                break;
            case 3:
                if (!Input.getText().toString().trim().isEmpty()) {
                    discount1 = Double.parseDouble(Input.getText().toString());
                }
                if (!field1.getText().toString().trim().isEmpty()) {
                    discount2 = Double.parseDouble(field1.getText().toString());
                }
                if (!field2.getText().toString().trim().isEmpty()) {
                    discount3 = Double.parseDouble(field2.getText().toString());
                }
                if (!field3.getText().toString().trim().isEmpty()) {
                    discount4 = Double.parseDouble(field3.getText().toString());
                }
                else if(Input.getText().toString().trim().isEmpty()){
                    discount1 = 0.0;
                }else if(field1.getText().toString().trim().isEmpty()){
                    discount2 = 0.0;
                }else if(field2.getText().toString().trim().isEmpty()){
                    discount3 = 0.0;
                }else if(field3.getText().toString().trim().isEmpty()){
                    discount4 = 0.0;
                }
                break;
            case 4:
                if (!Input.getText().toString().trim().isEmpty()) {
                    discount1 = Double.parseDouble(Input.getText().toString());
                }
                if (!field1.getText().toString().trim().isEmpty()) {
                    discount2 = Double.parseDouble(field1.getText().toString());
                }
                if (!field2.getText().toString().trim().isEmpty()) {
                    discount3 = Double.parseDouble(field2.getText().toString());
                }
                if (!field3.getText().toString().trim().isEmpty()) {
                    discount4 = Double.parseDouble(field3.getText().toString());
                }
                if (!field4.getText().toString().trim().isEmpty()) {
                    discount5 = Double.parseDouble(field4.getText().toString());
                }
                else if(Input.getText().toString().trim().isEmpty()){
                    discount1 = 0.0;
                }else if(field1.getText().toString().trim().isEmpty()){
                    discount2 = 0.0;
                }else if(field2.getText().toString().trim().isEmpty()){
                    discount3 = 0.0;
                }else if(field3.getText().toString().trim().isEmpty()){
                    discount4 = 0.0;
                }
                else if(field4.getText().toString().trim().isEmpty()){
                    discount5 = 0.0;
                }
                break;
        }

    }

    private void cal_discount(){
        if (!swith.isChecked())///%
        {
            if (!price_input.getText().toString().trim().isEmpty()) {
                price_in=Double.parseDouble(price_input.getText().toString());
            }else  Toast.makeText(getApplicationContext(), "กรุณาใส่ราคาสินค้าและส่วนลด", Toast.LENGTH_SHORT).show();
            price_out = price_in*(100-discount1)/100;
            price_out = price_out*(100-discount2)/100;
            price_out = price_out*(100-discount3)/100;
            price_out = price_out*(100-discount4)/100;
            price_out = price_out*(100-discount5)/100;
        }else  if (swith.isChecked())///บาท
        {
            price_in=Double.parseDouble(price_input.getText().toString());
            price_out = price_in-discount1;
            price_out = price_out-discount2;
            price_out = price_out-discount3;
            price_out = price_out-discount4;
            price_out = price_out-discount5;

        }
    }

}
