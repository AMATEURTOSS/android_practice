package kr.ft_seoul.certifing_number_auto_input;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity
{
	final int REQUEST_RECEIVE_SMS_CODE = 1;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		System.out.println("Main activity start");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		permission();
	}

	public void permission()
	{
		System.out.println("Permission function activate");
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED)
		{
			if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECEIVE_SMS))
			{
				System.out.println("After the user declines");
				set_permission_manually();
			}
			else
			{
				System.out.println("First authorization request");
				ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS}, REQUEST_RECEIVE_SMS_CODE);
			}
		}
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults)
	{
		switch (requestCode)
		{
			case REQUEST_RECEIVE_SMS_CODE:
			{
				if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
				{
					System.out.println("Permission granted");
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
				else
					set_permission_manually();
				return;
			}
		}
	}

	public void set_permission_manually()
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("권한 설정");
		builder.setMessage("권한이 없기때문에 앱이 작동하지 않을것입니다.\n권한을 설정하려면 앱 정보 버튼을 누르세요.");
		builder.setPositiveButton("확인",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
					}
				});
		builder.show();
	}

	public void viewHow_to_use(View view)
	{
		System.out.println("App info activity start");
		Intent intent = new Intent(this, how_to_useActivity.class);
		startActivity(intent);
	}

	public void viewInfo(View view)
	{
		System.out.println("Developer info activity start");
		Intent intent = new Intent(this, infoActivity.class);
		startActivity(intent);
	}

	public void viewSetPermission(View view)
	{
		System.out.println("set permission activity start");
		Intent intent = new Intent(this, setPermissionActivity.class);
		startActivity(intent);
	}
}