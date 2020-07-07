package kr.ft_seoul.certifing_number_auto_input;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;

public class setPermissionActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("권한 수동 설정");
        setContentView(R.layout.activity_set_permission);
    }

    public void permissionOnClick(View view)
    {
        System.out.println("Permission manual setting button");
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 1);
    }

    public void restart(View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("앱 재시작");
        builder.setMessage("자동인증기를 사용하려면 앱을 재시작해야합니다.\n확인 버튼을 누르면 앱이 종료됩니다.\n앱을 다시 시작해주세요.");
        builder.setPositiveButton("확인",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finishAffinity();
                        System.runFinalization();
                        System.exit(0);
                    }
                });
        builder.show();
    }
}