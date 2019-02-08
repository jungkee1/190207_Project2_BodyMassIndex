package org.keetech.a190207_project2_bodymassindex;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String gender;
    String blood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText edit1 = findViewById(R.id.edt1); //키
        final EditText edit2 = findViewById(R.id.edt2); //몸무게

        final RadioButton rb1 = findViewById(R.id.rb1); // 성별 남
        final RadioButton rb2 = findViewById(R.id.rb2); // 성별 여

        final Spinner spin1 = findViewById(R.id.spin1); //혈액형

        final CheckBox chk1 = findViewById(R.id.chk1); //습관1
        final CheckBox chk2 = findViewById(R.id.chk2); //습관2
        final CheckBox chk3 = findViewById(R.id.chk3); //습관3

        Button btn1 = findViewById(R.id.btn1); // 결과보기 버튼

        rb1.setOnClickListener(new View.OnClickListener() { //성별 선택 남자
            @Override
            public void onClick(View v) {
                RadioButton rb = (RadioButton) v;
                gender = rb.getText().toString();
            }
        });
        rb2.setOnClickListener(new View.OnClickListener() { //성별 선택 여자
            @Override
            public void onClick(View v) {
                RadioButton rb = (RadioButton) v;
                gender = rb.getText().toString();
            }
        });

        final String blood_type[] = {"A","B","O","AB"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,blood_type);
        spin1.setAdapter(adapter);



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {// 버튼 누름

                if(edit1.getText().toString().equals("") || edit2.getText().toString().equals("")){
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("키와 체중")
                            .setView(getLayoutInflater().inflate(R.layout.custom_dialog, null))
                            .show();
                }else{
                    blood = spin1.getSelectedItem().toString(); //혈액형

                    TextView tv1 = findViewById(R.id.tv1);
                    TextView tv2 = findViewById(R.id.tv2);
                    tv1.setText(blood + "형 " + gender +" 입니다!"); //tv1 수정

                    //tv2 수정
                    double height = Double.parseDouble(edit1.getText().toString()); //키
                    double weight = Double.parseDouble(edit2.getText().toString()); //몸무게
                    double result1 = weight/((height/100)*(height/100)); //
                    String mass = String.format("%.2f",result1); //소수점 두번째 자리까지

//                double mass = ((int)(result1*10))/10.0F;
                    tv2.setText("신체 질량 지수는" + mass + "입니다");

                    //습관에 따른 그림

                    Gallery gal = findViewById(R.id.gal);
                    ArrayList<Integer> mThumblds = new ArrayList<>();

                    if(chk1.isChecked()){
                        mThumblds.add(R.drawable.drinking);
                    }
                    if(chk2.isChecked()){
                        mThumblds.add(R.drawable.ciga);
                    }
                    if(!chk3.isChecked()){
                        mThumblds.add(R.drawable.running);
                    }

                    if(chk1.isChecked() || chk2.isChecked() || !chk3.isChecked()){

                        gal.setAdapter(new ImageAdapter(MainActivity.this,mThumblds)); //(위치, 이미지 ID 배열)
                    }else{
                        gal.removeAllViewsInLayout();
                    }
                }


            }
        });
    }
}
