package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.faendir.rhino_android.RhinoAndroidHelper;

import org.mozilla.javascript.ImporterTopLevel;
import org.mozilla.javascript.Scriptable;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    TextView editText;
    TextView textViewResult;
    Button buttonClr, buttonDot, buttonDiv, buttonMul, buttonAdd, buttonSub, buttonPer, buttonBracket, buttonErase, buttonEqual,
            buttonZero, buttonOne, buttonTwo, buttonThree, buttonFour, buttonFive, buttonSix, buttonSeven, buttonEight,
            buttonNine;
    String process;
    Boolean checkBracket = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonClr = (Button) findViewById(R.id.buttonClr);
        buttonDot = (Button) findViewById(R.id.buttonDot);
        buttonErase = (Button) findViewById(R.id.buttonErase);
        buttonBracket = (Button) findViewById(R.id.buttonBracket);
        buttonEqual = (Button) findViewById(R.id.buttonEqual);

        buttonDiv = (Button) findViewById(R.id.buttonDiv);
        buttonMul = (Button) findViewById(R.id.buttonMul);
        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonSub = (Button) findViewById(R.id.buttonSub);
        buttonPer = (Button) findViewById(R.id.buttonPer);

        buttonZero = (Button) findViewById(R.id.buttonZero);
        buttonOne = (Button) findViewById(R.id.buttonOne);
        buttonTwo = (Button) findViewById(R.id.buttonTwo);
        buttonThree = (Button) findViewById(R.id.buttonThree);
        buttonFour = (Button) findViewById(R.id.buttonFour);
        buttonFive = (Button) findViewById(R.id.buttonFive);
        buttonSix = (Button) findViewById(R.id.buttonSix);
        buttonSeven = (Button) findViewById(R.id.buttonSeven);
        buttonEight = (Button) findViewById(R.id.buttonEight);
        buttonNine = (Button) findViewById(R.id.buttonNine);
        editText = (TextView) findViewById(R.id.editText);
        textViewResult = (TextView) findViewById(R.id.textViewResult);

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );

        buttonClr.setOnClickListener(view -> {
            editText.setText("");
            textViewResult.setText("");
        });

        buttonDot.setOnClickListener(view -> {
            process = editText.getText().toString();
            if (process.equals("") || process.endsWith("×") || process.endsWith("-") || process.endsWith("+") ||
                    process.endsWith("%") || process.endsWith("÷")) {
                editText.setText(process + "0.");
            } else if (process.endsWith(".")) {
                editText.setText(process + "");
            } else {
                editText.setText(process + ".");
            }
        });

        buttonBracket.setOnClickListener(view -> {
            process = editText.getText().toString();
            if (checkBracket) {
                if (process.equals("") || process.endsWith(".") || process.startsWith(".")) {
                    editText.setText(process + "");
                } else {
                    editText.setText(process + ")");
                }
                checkBracket = false;
            } else {
                if (process.endsWith(".") || process.startsWith(".")) {
                    editText.setText(process + "");
                } else if (process.endsWith("0") || process.endsWith("1") || process.endsWith("2") ||
                        process.endsWith("3") || process.endsWith("4") || process.endsWith("5") ||
                        process.endsWith("6") || process.endsWith("7") || process.endsWith("8") ||
                        process.endsWith("9") || process.endsWith(")")){
                    editText.setText(process + "×(");
                }
                else {
                    editText.setText(process + "(");
                }
                checkBracket = true;
            }
        });

        buttonErase.setOnClickListener(view -> {
            process = editText.getText().toString();
            if (process.equals("")) {
                editText.setText(process + "");
            } else {
                StringBuffer erase = new StringBuffer(process);
                erase.deleteCharAt(process.length() - 1);
                editText.setText(erase);
            }
        });

        buttonDiv.setOnClickListener(view -> {
            process = editText.getText().toString();
            if (process.equals("") || process.endsWith(".") || process.startsWith(".") ||
                    process.endsWith("×") || process.endsWith("-") || process.endsWith("+") ||
                    process.endsWith("%") || process.endsWith("÷")) {
                editText.setText(process + "");
            } else {
                editText.setText(process + "÷");
            }
        });

        buttonMul.setOnClickListener(view -> {
            process = editText.getText().toString();
            if (process.equals("") || process.endsWith(".") || process.startsWith(".") ||
                    process.endsWith("×") || process.endsWith("-") || process.endsWith("+") ||
                    process.endsWith("%") || process.endsWith("÷")) {
                editText.setText(process + "");
            } else {
                editText.setText(process + "×");
            }
        });

        buttonAdd.setOnClickListener(view -> {
            process = editText.getText().toString();
            if (process.equals("") || process.endsWith(".") || process.startsWith(".") ||
                    process.endsWith("×") || process.endsWith("-") || process.endsWith("+") ||
                    process.endsWith("%") || process.endsWith("÷")) {
                editText.setText(process + "");
            } else {
                editText.setText(process + "+");
            }
        });

        buttonSub.setOnClickListener(view -> {
            process = editText.getText().toString();
            if (process.equals("") || process.endsWith(".") || process.startsWith(".") ||
                    process.endsWith("×") || process.endsWith("-") || process.endsWith("+") ||
                    process.endsWith("%") || process.endsWith("÷")) {
                editText.setText(process + "");
            } else {
                editText.setText(process + "-");
            }
        });

        buttonPer.setOnClickListener(view -> {
            process = editText.getText().toString();
            if (process.equals("") || process.endsWith(".") || process.startsWith(".") ||
                    process.endsWith("×") || process.endsWith("-") || process.endsWith("+") ||
                    process.endsWith("%") || process.endsWith("÷")) {
                editText.setText(process + "");
            } else {
                editText.setText(process + "%");
            }
        });

        buttonZero.setOnClickListener(view -> {
            process = editText.getText().toString();
            if (process.endsWith(")")) {
                editText.setText(process + "×0");
            } else {
                editText.setText(process + "0");
            }
        });

        buttonOne.setOnClickListener(view -> {
            process = editText.getText().toString();
            if (process.endsWith(")")) {
                editText.setText(process + "×1");
            } else {
                editText.setText(process + "1");
            }
        });

        buttonTwo.setOnClickListener(view -> {
            process = editText.getText().toString();
            if (process.endsWith(")")) {
                editText.setText(process + "×2");
            } else {
                editText.setText(process + "2");
            }
        });

        buttonThree.setOnClickListener(view -> {
            process = editText.getText().toString();
            if (process.endsWith(")")) {
                editText.setText(process + "×3");
            } else {
                editText.setText(process + "3");
            }
        });

        buttonFour.setOnClickListener(view -> {
            process = editText.getText().toString();
            if (process.endsWith(")")) {
                editText.setText(process + "×4");
            } else {
                editText.setText(process + "4");
            }
        });

        buttonFive.setOnClickListener(view -> {
            process = editText.getText().toString();
            if (process.endsWith(")")) {
                editText.setText(process + "×5");
            } else {
                editText.setText(process + "5");
            }
        });

        buttonSix.setOnClickListener(view -> {
            process = editText.getText().toString();
            if (process.endsWith(")")) {
                editText.setText(process + "×6");
            } else {
                editText.setText(process + "6");
            }
        });

        buttonSeven.setOnClickListener(view -> {
            process = editText.getText().toString();
            if (process.endsWith(")")) {
                editText.setText(process + "×7");
            } else {
                editText.setText(process + "7");
            }
        });

        buttonEight.setOnClickListener(view -> {
            process = editText.getText().toString();
            if (process.endsWith(")")) {
                editText.setText(process + "×8");
            } else {
                editText.setText(process + "8");
            }
        });

        buttonNine.setOnClickListener(view -> {
            process = editText.getText().toString();
            if (process.endsWith(")")) {
                editText.setText(process + "×9");
            } else {
                editText.setText(process + "9");
            }
        });
        
        buttonEqual.setOnClickListener(new View.OnClickListener() {
            private org.mozilla.javascript.Context context;
            private Scriptable scope;
            private RhinoAndroidHelper rhinoAndroidHelper = new RhinoAndroidHelper();
            @Override
            public void onClick(View v) {
                context = rhinoAndroidHelper.enterContext();
                context.setOptimizationLevel(-1);
                scope = new ImporterTopLevel(context);
                process = editText.getText().toString();
                DecimalFormat df = new DecimalFormat();

                process = process.replaceAll("×","*");
                process = process.replaceAll("%","/100");
                process = process.replaceAll("÷","/");

                String finalResult ="";

                try {
                    Scriptable scriptable = context.initStandardObjects();
                    finalResult = context.evaluateString(scriptable,process,"javascript",1,null).toString();
                }catch (Exception e){
                    textViewResult.setText("Enter a valid expression!!");
                    finalResult = "0";
                }finally {
                    Double res = Double.parseDouble(finalResult);
                    String ans = df.format(res);
                    textViewResult.setText(ans);
                }
            }
        });
    }
}