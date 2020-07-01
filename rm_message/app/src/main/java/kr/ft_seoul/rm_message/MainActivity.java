package kr.ft_seoul.rm_message;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity
{
	@Override

	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void info_Button (View view)
	{
		Intent intent = new Intent(MainActivity.this, info_view.class);
		startActivity(intent);
	}

	public void delete_Button (View view)
	{
		int count = 0;
		Uri allMessage = Uri.parse("content://sms");
		ContentResolver cr = getContentResolver();
		Cursor c = cr.query(allMessage, new String[] { "_id", "thread_id", "address", "person", "date", "body" },
				null, null, "date DESC");
		while (c.moveToNext())
		{
			count++;
			String body = c.getString(5);
			Long id = c.getLong(0);
			if (body.contains("(광고)"))
			{
				System.out.println(body);
				System.out.println("------------");
				cr.delete(Uri.parse("content://sms/" + id), null, null);
				continue ;
			}
		}
		c.close();
		if (count > 0)
			Snackbar.make(view, count + " message has been deleted", Snackbar.LENGTH_LONG).show();
		else
			Snackbar.make(view, "No message to delete", Snackbar.LENGTH_LONG).show();
	}
}