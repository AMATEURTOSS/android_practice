package kr.ft_seoul.certifing_number_auto_input;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class infoActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("개발자 정보");
        setContentView(R.layout.activity_info);
    }

    public void github(View view)
    {
        System.out.println("Github button onClick");
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/AMATEURTOSS"));
        startActivity(intent);
    }
}