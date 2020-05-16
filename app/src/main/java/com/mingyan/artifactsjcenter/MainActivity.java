package com.mingyan.artifactsjcenter;
/**20200515AndroidStudio3.5.3，Try publish jcenter。*/
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.mingyan.toasttalk.ToastTalk;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickShort(View view){
        ToastTalk.vShort(view.getContext(), "ShorShort HelloWorld!");
    }

    public void clickLong(View view){
        ToastTalk.vLong(this, "LongLong HelloWorld!");
    }

}
