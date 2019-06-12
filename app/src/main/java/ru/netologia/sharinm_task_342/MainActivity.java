package ru.netologia.sharinm_task_342;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public static Spinner spinnerLang;
    public static Spinner spinnerColor;
    public static Spinner spinnerIndent;

    private static final String APP_PREFERENCES = "mysettings";
    private static final String APP_PREFERENCES_LANG = "Lang";
    private static final String APP_PREFERENCES_COLOR = "Color";
    private static final String APP_PREFERENCES_INDENT = "Indent";

    public SharedPreferences.Editor editor;

    SharedPreferences mSettings;

    @Override
    public void onSaveInstanceState(Bundle saveInstanceState){

        saveInstanceState.putInt("valueSpinnerLang", spinnerLang.getSelectedItemPosition());
        saveInstanceState.putInt("valueSpinnerColor", spinnerColor.getSelectedItemPosition());

        super.onSaveInstanceState(saveInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle saveInstanceState){
        super.onRestoreInstanceState(saveInstanceState);

        spinnerLang.setSelection(saveInstanceState.getInt("valueSpinnerLang"));
        spinnerColor.setSelection(saveInstanceState.getInt("valueSpinnerColor"));
//        Toast.makeText(MainActivity.this, saveInstanceState.getInt("valueSpinnerLang") + " " + saveInstanceState.getInt("valueSpinnerColor"), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.onActivityCreateSetTheme(this);
        Utils.onActivityCreateSetIndent(this);
        setContentView(R.layout.activity_main);

        spinnerLang = findViewById(R.id.langSpinner);
        spinnerColor = findViewById(R.id.colorSpinner);
        spinnerIndent = findViewById(R.id.indentSpinner);

        Button btnLang = findViewById(R.id.langButton);
        Button btnColor = findViewById(R.id.colorButton);
        Button btnIndent = findViewById(R.id.indentButton);

        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        editor = mSettings.edit();
        if(mSettings.contains(APP_PREFERENCES_COLOR)) {
            spinnerColor.setSelection(mSettings.getInt(APP_PREFERENCES_COLOR, 0));
        }
        if(mSettings.contains(APP_PREFERENCES_LANG)) {
            spinnerLang.setSelection(mSettings.getInt(APP_PREFERENCES_LANG, 0));
        }
        if(mSettings.contains(APP_PREFERENCES_INDENT)) {
            spinnerIndent.setSelection(mSettings.getInt(APP_PREFERENCES_INDENT, 0));
        }

        btnIndent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selected = (int) spinnerIndent.getSelectedItemPosition();

                switch (selected){
                    case 0:
                        Utils.changeToIndent(MainActivity.this, Utils.INDENT_MARGIN1);
                        break;
                    case 1:
                        Utils.changeToIndent(MainActivity.this, Utils.INDENT_MARGIN3);
                        break;
                    case 2:
                        Utils.changeToIndent(MainActivity.this, Utils.INDENT_MARGIN10);
                        break;
                }

                editor.putInt(APP_PREFERENCES_LANG, (int) spinnerLang.getSelectedItemId());
                editor.putInt(APP_PREFERENCES_COLOR, (int) spinnerColor.getSelectedItemId());
                editor.putInt(APP_PREFERENCES_INDENT, (int) spinnerIndent.getSelectedItemId());
                editor.apply();
            }
        });
        btnLang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lang;

                int selected = (int) spinnerLang.getSelectedItemId();

                switch (selected) {
                    default:
                    case 0:
                        lang = "ru";
                        break;
                    case 1:
                        lang = "en";
                        break;
                    case 2:
                        lang = "fr";
                        break;
                    case 3:
                        lang = "de";
                        break;
                }

                Locale locale = new Locale(lang);
                Configuration config = new Configuration();
                config.setLocale(locale);
                getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                recreate();

                editor.putInt(APP_PREFERENCES_LANG, (int) spinnerLang.getSelectedItemId());
                editor.putInt(APP_PREFERENCES_COLOR, (int) spinnerColor.getSelectedItemId());
                editor.putInt(APP_PREFERENCES_INDENT, (int) spinnerIndent.getSelectedItemId());
                editor.apply();

                Toast.makeText(MainActivity.this, getString(R.string.textTextView), Toast.LENGTH_LONG).show();
            }
        });

        btnColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selected = (int) spinnerColor.getSelectedItemId();

                editor.putInt(APP_PREFERENCES_LANG, (int) spinnerLang.getSelectedItemId());
                editor.putInt(APP_PREFERENCES_COLOR, (int) spinnerColor.getSelectedItemId());
                editor.putInt(APP_PREFERENCES_INDENT, (int) spinnerIndent.getSelectedItemId());
                editor.apply();

                switch (selected) {

                    default:
                        Utils.changeToTheme(MainActivity.this, Utils.THEME_DEFAULT);
                        break;
                    case 0:
                        Utils.changeToTheme(MainActivity.this, Utils.THEME_BLACK);
                        break;
                    case 1:
                        Utils.changeToTheme(MainActivity.this, Utils.THEME_GREEN);
                        break;
                    case 2:
                        Utils.changeToTheme(MainActivity.this, Utils.THEME_BLUE);
                        break;
                }
            }
        });
    }
}

