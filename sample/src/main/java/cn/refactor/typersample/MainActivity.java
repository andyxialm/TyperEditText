package cn.refactor.typersample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.refactor.typer.TyperEditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();
    }

    private void setupViews() {
        TyperEditText typerEditText = (TyperEditText) findViewById(R.id.typer_edit_text);
        typerEditText.start();
    }
}
