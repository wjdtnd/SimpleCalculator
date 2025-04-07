package kr.ac.kopo.simplecalculator;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText edit1, edit2;
    TextView textResult;
    Button btnPlus, btnMinus, btnMultiply, btnDivide, btnMod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edit1 = findViewById(R.id.edit1);
        edit2 = findViewById(R.id.edit2);
        btnPlus = findViewById(R.id.btn_plus);
        btnMinus = findViewById(R.id.btn_minus);
        btnMultiply = findViewById(R.id.btn_multiply);
        btnDivide = findViewById(R.id.btn_divide);
        btnMod = findViewById(R.id.btn_mod);
        textResult = findViewById(R.id.text_result);

//        btnPlus.setOnClickListener(btnListener);
//        btnMinus.setOnClickListener(btnListener);
//        btnMultiply.setOnClickListener(btnListener);
//        btnDivide.setOnClickListener(btnListener);

        btnPlus.setOnTouchListener(btnTouchListener);
        btnMinus.setOnTouchListener(btnTouchListener);
        btnMultiply.setOnTouchListener(btnTouchListener);
        btnDivide.setOnTouchListener(btnTouchListener);
        btnMod.setOnTouchListener(btnTouchListener);

    }

    View.OnTouchListener btnTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event){
            String num1Str = edit1.getText().toString();
            String num2Str = edit2.getText().toString();

            if(num1Str.equals("") || num2Str.equals("")){
                Toast.makeText(getApplicationContext(),"숫자 입력이 되지 않았습니다.", Toast.LENGTH_SHORT).show();
                edit1.setFocusable(true);
            }else{
                double num1 = Double.parseDouble(num1Str);
                double num2 = Double.parseDouble(num2Str);
                double result = 0;

                Button btnEvent = (Button) v;

                if(btnEvent == btnPlus)
                    result = num1 + num2;
                else if(btnEvent == btnMinus)
                    result = num1 - num2;
                else if(btnEvent == btnMultiply)
                    result = num1 * num2;
                else if(btnEvent == btnDivide)
                    if(num2 == 0){
                        Toast.makeText(getApplicationContext(),"0으로 나눌 수 없습니다.",Toast.LENGTH_SHORT).show();
                        edit2.setText("");
                        edit2.setFocusable(true);
                    }else{
                        result = num1/num2;
                    }
                else
                    result = num1 % num2;

                textResult.setText(String.format("계산 결과 : %.3f", result));
            }
            return true;
        }
    };

    View.OnClickListener btnListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            String num1Str = edit1.getText().toString();
            String num2Str = edit2.getText().toString();

            double num1 = Double.parseDouble(num1Str);
            double num2 = Double.parseDouble(num2Str);
            double result = 0;

            Button btnEvent = (Button) v;

            if(btnEvent == btnPlus)
                result = num1 + num2;
            else if(btnEvent == btnMinus)
                result = num1 - num2;
            else if(btnEvent == btnMultiply)
                result = num1 * num2;
            else
                if(num2 == 0){
                    Toast.makeText(getApplicationContext(),"0으로 나눌 수 없습니다.",Toast.LENGTH_SHORT).show();
                    edit2.setText("");
                    edit2.setFocusable(true);
                }else{
                    result = num1/num2;
                }

            textResult.setText("계산 결과  : " + result);
        }
    };
}