package com.example.FlashCard;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.*;
import android.widget.*;
import com.googlecode.flickrjandroid.Flickr;
import com.yandex.metrica.Counter;

import java.io.*;


public class MainActivity extends Activity {
	/**
	 * Called when the activity is first created.
	 */


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
					/*setContentView(R.layout.main);
			ImageView logo = (ImageView) findViewById(R.id.logoImageView);

			logo.setImageResource(R.drawable.ball);

			try {


				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		*/
		setContentView(R.layout.menu);
		ListView menu_list = (ListView) findViewById(R.id.listView);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.my_sinple_list_item, getResources().getStringArray(R.array.category_menu));
		menu_list.setAdapter(adapter);

						/*
				ImageView logo = (ImageView) findViewById(R.id.logoImageView);


				TypedArray ar = getResources().obtainTypedArray(R.array.icon_drinks);
				int len = ar.length();
				int[] resIds = new int[len];
				for (int i = 0; i < len; i++)
					resIds[i] = ar.getResourceId(i, 0);
				ar.recycle();

				for (int i = 0; i < resIds.length; i++) {
					Log.i("aaa",String.valueOf(resIds[i]));
				}
				Log.i("f",String.valueOf(R.string.icon));
				logo.setImageResource(resIds[1]);*/

				/*setContentView(R.layout.card_path1);
				TypedArray ar = getResources().obtainTypedArray(R.array.icon_drinks);
				int len = ar.length();
				int[] resIds = new int[len];
				for (int i = 0; i < len; i++)
					resIds[i] = ar.getResourceId(i, 0);
				ar.recycle();
				int index = 3;
				String [] strings = getResources().getStringArray(R.array.drinks);
				String [] strings1 = getResources().getStringArray(R.array.de_drinks);
				ImageView logo = (ImageView) findViewById(R.id.imageView);
				TextView text = (TextView) findViewById(R.id.locWordTextView);
				TextView text1 = (TextView) findViewById(R.id.textView);
				logo.setImageResource(resIds[index]);
				text.setText(strings[index]);
				text1.setText(strings1[index]);*/
		menu_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id) {

				/*Toast.makeText(getApplicationContext(), ((TextView) itemClicked).getText(),
						Toast.LENGTH_SHORT).show();*/
				Intent intent = new Intent(MainActivity.this, CardTestActivity.class);
				TypedArray ar = getResources().obtainTypedArray(R.array.ids);
				int len = ar.length();
				int[] resIds = new int[len];
				for (int i = 0; i < len; i++)
					resIds[i] = ar.getResourceId(i, 0);
				ar.recycle();
				intent.putExtra("id_icon",resIds[3* (int)id]);
				intent.putExtra("id_de",resIds[3*(int)id + 1]);
				intent.putExtra("id_loc",resIds[3*(int)id + 2]);
				intent.putExtra("id",(int)id);
				MainActivity.this.startActivity(intent);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub



		// Операции для выбранного пункта меню

				Toast toast3 = Toast.makeText(getApplicationContext(),
						"", Toast.LENGTH_LONG);
				toast3.setGravity(Gravity.CENTER, 0, 0);
				LinearLayout toastView = (LinearLayout) toast3.getView();
				ImageView imageCat = new ImageView(getApplicationContext());
				imageCat.setImageResource(R.drawable.krauser_san);
				toastView.addView(imageCat, 0);
				toast3.show();
				return true;



	}
}
