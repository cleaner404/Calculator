package com.example.cleaner208.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;

import bsh.EvalError;
import bsh.Interpreter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText rsText = null;  //显示器
    boolean isClear = false; //用于是否显示器需要被清理
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //fun 功能按钮
        rsText = (EditText)findViewById(R.id.rsText);
        Button btnDel = (Button)findViewById(R.id.delete);
        Button btnPlu = (Button)findViewById(R.id.plus);
        Button btnMin = (Button)findViewById(R.id.minus);
        Button btnMul = (Button)findViewById(R.id.multiply);
        Button btnDiv = (Button)findViewById(R.id.division);
        Button btnEqu = (Button)findViewById(R.id.equ);
        Button btnTono = (Button)findViewById(R.id.tonone);
        Button btnLeft = (Button)findViewById(R.id.left);
        Button btnRight = (Button)findViewById(R.id.right);
        Button btnsqrt = (Button)findViewById(R.id.keysart);
        Button btnsin = (Button)findViewById(R.id.keysin);
        Button btncos = (Button)findViewById(R.id.keycos);
        Button btn_CF=(Button)findViewById(R.id.btn_CF);
        Button btn_FC=(Button)findViewById(R.id.btn_FC);
        Button btn_RD=(Button)findViewById(R.id.btn_RD);
        Button btn_DR=(Button)findViewById(R.id.btn_DR);


        //num 数字按钮
        Button num0 = (Button)findViewById(R.id.num0);
        Button num1 = (Button)findViewById(R.id.num1);
        Button num2 = (Button)findViewById(R.id.num2);
        Button num3 = (Button)findViewById(R.id.num3);
        Button num4 = (Button)findViewById(R.id.num4);
        Button num5 = (Button)findViewById(R.id.num5);
        Button num6 = (Button)findViewById(R.id.num6);
        Button num7 = (Button)findViewById(R.id.num7);
        Button num8 = (Button)findViewById(R.id.num8);
        Button num9 = (Button)findViewById(R.id.num9);
        Button dot = (Button)findViewById(R.id.dot);

        //listener
        btnTono.setOnClickListener(this);
        btnLeft.setOnClickListener(this);
        btnRight.setOnClickListener(this);
        btnDel.setOnClickListener(this);
        btnPlu.setOnClickListener(this);
        btnMin.setOnClickListener(this);
        btnMul.setOnClickListener(this);
        btnDiv.setOnClickListener(this);
        btnEqu.setOnClickListener(this);
        btnsqrt.setOnClickListener(this);
        btnsin.setOnClickListener(this);
        btncos.setOnClickListener(this);
        btn_CF.setOnClickListener(this);
        btn_FC.setOnClickListener(this);
        btn_RD.setOnClickListener(this);
        btn_DR.setOnClickListener(this);
        num0.setOnClickListener(this);
        num1.setOnClickListener(this);
        num2.setOnClickListener(this);
        num3.setOnClickListener(this);
        num4.setOnClickListener(this);
        num5.setOnClickListener(this);
        num6.setOnClickListener(this);
        num7.setOnClickListener(this);
        num8.setOnClickListener(this);
        num9.setOnClickListener(this);
        dot.setOnClickListener(this);
    }
    @Override
    public void onClick(View e) {
        Button btn = (Button)e;
        String exp = rsText.getText().toString();
        if(isClear &&(
                btn.getText().equals("0")
                        ||btn.getText().equals("1")
                        ||btn.getText().equals("2")
                        ||btn.getText().equals("3")
                        ||btn.getText().equals("4")
                        ||btn.getText().equals("5")
                        ||btn.getText().equals("6")
                        ||btn.getText().equals("7")
                        ||btn.getText().equals("8")
                        ||btn.getText().equals("9")
                        ||btn.getText().equals("."))
                ||btn.getText().equals("算数公式错误")){
            rsText.setText("");
            isClear = false;
        }
        if(btn.getText().equals("C")){
            rsText.setText("");
        }else if(btn.getText().equals("清除")){
            if(isEmpty(exp)) return;
            else
                rsText.setText(exp.substring(0, exp.length()-1));
        }
        //实现sin，cos，开根，幂函数
        else if(btn.getText().equals("sart")){
            TextView sart=(TextView) findViewById(R.id.rsText);
            double a=Double.parseDouble(sart.getText().toString());
            double num1=Math.sqrt(a);
            rsText.setText(Double.toString(num1));
        }
        else if(btn.getText().equals("sin(")){
            TextView sart=(TextView) findViewById(R.id.rsText);
            double a=Double.parseDouble(sart.getText().toString());
            double num2=Math.sin(a*Math.PI/180);
            rsText.setText(Double.toString(num2));
        }
        else if(btn.getText().equals("cos(")){
            TextView sart=(TextView) findViewById(R.id.rsText);
            double a=Double.parseDouble(sart.getText().toString());
            double num3=Math.sin(a*Math.PI/180);
            rsText.setText(Double.toString(num3));
        }



        else if(btn.getText().equals("=")){
            if(isEmpty(exp)) return;
            exp = exp.replaceAll("×", "*");
            exp = exp.replaceAll("÷", "/");
            rsText.setText(getRs(exp));
            isClear = false;
        }
        //实现单位换算
        else if(btn.getText().equals("℃→℉")){
            TextView sart=(TextView) findViewById(R.id.rsText);
            double a=Double.parseDouble(sart.getText().toString());
            double f=32+a*1.8;
            rsText.setText(Double.toString(f));
        }
        else if(btn.getText().equals("℉→℃")){
            TextView sart=(TextView) findViewById(R.id.rsText);
            double a=Double.parseDouble(sart.getText().toString());
            double c=(a-32)/1.8;
            rsText.setText(Double.toString(c));
        }
        else if(btn.getText().equals("¥→$")){
            TextView sart=(TextView) findViewById(R.id.rsText);
            double a=Double.parseDouble(sart.getText().toString());
            double D=a*0.1497;
            rsText.setText(Double.toString(D));
        }
        else if(btn.getText().equals("$→¥")){
            TextView sart=(TextView) findViewById(R.id.rsText);
            double a=Double.parseDouble(sart.getText().toString());
            double R=a*6.6798;
            rsText.setText(Double.toString(R));
        }




        else{
            rsText.setText(rsText.getText()+""+btn.getText());
            isClear = false;
        }
        //操作完成后始终保持光标在最后一位
        rsText.setSelection(rsText.getText().length());
    }

    /***
     * @param  exp 算数表达式
     * @return 根据表达式返回结果
     */
    private String getRs(String exp){
        Interpreter bsh = new Interpreter();
        Number result = null;
        try {
            exp = filterExp(exp);
            result = (Number)bsh.eval(exp);
        } catch (EvalError e) {
            e.printStackTrace();
            isClear = true;
            return "算数公式错误";
        }
        exp = result.doubleValue()+"";
        if(exp.endsWith(".0"))
            exp = exp.substring(0, exp.indexOf(".0"));
        return exp;
    }


    /**
     * 因为计算过程中,全程需要有小数参与,所以需要过滤一下
     * @param exp 算数表达式
     * @return
     */
    private String filterExp(String exp) {
        String num[] = exp.split("");
        String temp = null;
        int begin=0,end=0;
        for (int i = 1; i < num.length; i++) {
            temp = num[i];
            if(temp.matches("[+-/()*]")){
                if(temp.equals(".")) continue;
                end = i - 1;
                temp = exp.substring(begin, end);
                if(temp.trim().length() > 0 && temp.indexOf(".")<0)
                    num[i-1] = num[i-1]+".0";
                begin = end + 1;
            }
        }
        return Arrays.toString(num).replaceAll("[\\[\\], ]", "");
    }

    /***
     * @param str
     * @return 字符串非空验证
     */
    private boolean isEmpty(String str){
        return (str==null || str.trim().length()==0);
    }

}
