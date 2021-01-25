package com.suraj.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    LinearLayout linearLayout;
    String pattern;
    List<QuestionType> questionTypeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = findViewById(R.id.linear_layout);

        pattern = getIntent().getStringExtra("question_pattern");

        addTextView();
        readJson();

    }

    private void readJson() {
        InputStream inputStream = getResources().openRawResource(R.raw.sample);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int ctr;
        try {
            ctr = inputStream.read();
            while (ctr != -1) {
                byteArrayOutputStream.write(ctr);
                ctr = inputStream.read();
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.v("Text Data", byteArrayOutputStream.toString());
        try {
            JSONObject jsonObject = new JSONObject(byteArrayOutputStream.toString());
            String success = jsonObject.getString("total_question");
            if (!success.equals("0")){
                if (pattern.equals("1")){
                    addRadios();
                } else if (pattern.equals("0")){
                    addCheckBoxes();
                } else if (pattern.equals("3")){
                    for (int i = 1; i <= 4; i++){
                        addCheckBox();
                    }
                } else {

                }
            } else {
                Log.e("Result Data", "No Data");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void addRowsCol() {


    }



    private void addTextView() {
        LinearLayout textLinearLayout = new LinearLayout(this);
        textLinearLayout.setOrientation(LinearLayout.VERTICAL);

        linearLayout.addView(textLinearLayout);

        TextView textView = new TextView(this);
        textView.setText("Dynamic TextView for Questions");
        setTextViewAttributes(textView);
        textLinearLayout.addView(textView);
    }

    private void setTextViewAttributes(TextView textView) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        params.setMargins(convertDpToPixel(16),
                convertDpToPixel(16),
                0, 0
        );

        textView.setGravity(Gravity.CENTER);
        textView.setLayoutParams(params);
    }

    private void addCheckBoxes() {
        LinearLayout checkBoxLayout = new LinearLayout(this);
        checkBoxLayout.setOrientation(LinearLayout.VERTICAL);

        linearLayout.addView(checkBoxLayout);

        for (int i = 1; i <= 4; i++) {
            CheckBox checkBox = new CheckBox(this);
            checkBox.setText("Dynamic CheckBox " + String.valueOf(i));
            setCheckBoxAttributes(checkBox);
            checkBoxLayout.addView(checkBox);
            checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked){
                    Toast.makeText(this, "Value "+checkBox.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    private void addCheckBox() {
        LinearLayout checkBoxLayout = new LinearLayout(this);
        checkBoxLayout.setOrientation(LinearLayout.HORIZONTAL);

        linearLayout.addView(checkBoxLayout);

        for (int i = 1; i <= 4; i++) {
            CheckBox checkBox = new CheckBox(this);
            checkBox.setText("Opt " + String.valueOf(i));
            setCheckBoxAttributes(checkBox);
            checkBoxLayout.addView(checkBox);
            checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked){
                    Toast.makeText(this, "Value "+checkBox.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }


    }

    private void setCheckBoxAttributes(CheckBox checkBox) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        params.setMargins(convertDpToPixel(16),
                convertDpToPixel(16),
                convertDpToPixel(16),
                0
        );

        checkBox.setLayoutParams(params);
    }


    private void addRadios() {
        //RadioButtons are always added inside a RadioGroup
        RadioGroup radioGroup = new RadioGroup(this);
        radioGroup.setOrientation(LinearLayout.VERTICAL);
        linearLayout.addView(radioGroup);


        for (int i = 1; i <= 2; i++) {

            RadioButton radioButton = new RadioButton(this);
            radioButton.setText("Option " + String.valueOf(i));
            radioGroup.addView(radioButton);
            setRadioButtonAttributes(radioButton);
        }

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {

            RadioButton radioButton = findViewById(checkedId);
            if (null != radioButton && checkedId > -1){
                Toast.makeText(this, "Value "+radioButton.getText(),Toast.LENGTH_SHORT).show();
            }

        });

    }

    private void setRadioButtonAttributes(RadioButton radioButton) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        params.setMargins(convertDpToPixel(16),
                convertDpToPixel(16),
                0, 0
        );

        radioButton.setLayoutParams(params);
    }

    private int convertDpToPixel(float dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return Math.round(px);
    }

}
