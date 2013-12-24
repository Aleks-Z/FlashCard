package com.example.FlashCard;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.*;
import org.xmlpull.v1.XmlPullParser;

/**
 * Created by Alex on 22.12.13.
 */
public class CardTestActivity extends Activity {

	private TextView txtPages;
	public static View page;
	public static LayoutInflater inflater;
	private ViewFlipper viewFlipper;
	private float fromPosition;
	private int count,count_correct_answer;
	private int len,icon_id,de_id,loc_id,id;
	private String [] loc_strings, translate_strings;
	private int[] resIds;
	private long time;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.card_test);
		Intent intent = getIntent();
		icon_id = intent.getIntExtra("id_icon",0);
		de_id = intent.getIntExtra("id_de",0);
		loc_id =  intent.getIntExtra("id_loc",0);
		id =  intent.getIntExtra("id",0);
		TypedArray ar = getResources().obtainTypedArray(icon_id);
		len = ar.length();
		resIds = new int[len];
		for (int i = 0; i < len; i++)
			resIds[i] = ar.getResourceId(i, 0);
		ar.recycle();
		loc_strings = getResources().getStringArray(loc_id);
		translate_strings = getResources().getStringArray(de_id);

		inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		viewFlipper =  (ViewFlipper) findViewById(R.id.viewFlipper);
		txtPages = (TextView)findViewById(R.id.pagetextView);

		count = 0;

		time = System.currentTimeMillis();
		View view = inflater.inflate(R.layout.card_path1, null);
		ImageView icon = (ImageView) view.findViewById(R.id.imageView);
		icon.setImageResource(resIds[0]);
		TextView loc_text = (TextView) view.findViewById(R.id.locWordTextView);
		loc_text.setText(loc_strings[0]);
		viewFlipper.addView(view);
		updateTextView();
		view = inflater.inflate(R.layout.card_path2,null);
		TextView translate_text = (TextView) view.findViewById(R.id.translateTextView);
		translate_text.setText(translate_strings[0]);
		viewFlipper.addView(view);
		updateTextView();
		count_correct_answer = 0;

		viewFlipper.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				/*switch (event.getAction())
				{
					case MotionEvent.ACTION_DOWN:
						fromPosition = event.getX();
						break;
					case MotionEvent.ACTION_UP:
						float toPosition = event.getX();
						if (fromPosition > toPosition + 20)
						{
							next();
							return true;
						}
						else if (fromPosition < toPosition - 20)
						{
							previous();
							return true;
						}
					default:
						break;
				}
				return true;*/
				switch (event.getAction()) {
					case MotionEvent.ACTION_UP:
						count++;

						if (count % 2 == 0 ) {
							count_correct_answer++;
							if (count / 2 == len) {
								setContentView(R.layout.cardtest_end);
								TextView text =  (TextView) findViewById(R.id.textView);
								text.setText(getResources().getText(R.string.time) + " : " + (float)((System.currentTimeMillis() - time)/10)/100 + " сек \n"
										+ getResources().getText(R.string.correct_answer) +  " : " + count_correct_answer );
								return true;
							}
							next_page();
							updateTextView();
							return true;
						} else {
							viewFlipper.showNext();
							updateTextView();
							return true;
						}

				}

				return true;
			}
		});

		/*		for (int index = 0; index <len ; index++) {
					View page1 = inflater.inflate(R.layout.card_path1, null);
					ImageView icon = (ImageView) page1.findViewById(R.id.imageView);
					icon.setImageResource(resIds[index]);
					TextView loc_text = (TextView) page1.findViewById(R.id.locWordTextView);
					TextView num_page_text = (TextView) page1.findViewById(R.id.textView);
					loc_text.setText(loc_strings[index]);
					num_page_text.setText((index +1) + " / " + (len+1));
					viewFlipper.addView(page1);

					View page2 = inflater.inflate(R.layout.card_path2, null);
					TextView translate_text = (TextView) page2.findViewById(R.id.translateTextView);
					translate_text.setText(translate_strings[index]);
					viewFlipper.addView(page2);
					*//*page = inflater.inflate(R.layout.card_path1, null);
					ImageView icon = (ImageView) page.findViewById(R.id.imageView);
					TextView loc_text = (TextView) page.findViewById(R.id.locWordTextView);
					TextView num_page_text = (TextView) page.findViewById(R.id.textView);
					icon.setImageResource(resIds[index]);
					loc_text.setText(loc_strings[index]);
					num_page_text.setText((index +1) + " / " + (len+1));
					viewFlipper.addView(inflater.inflate((XmlPullParser) page, null));
					page = inflater.inflate(R.layout.card_path2, null);
					num_page_text = (TextView) page.findViewById(R.id.textView);
					num_page_text.setText((index +1) + " / " + (len+1));

					loc_text.setText(translate_strings[index]);
					viewFlipper.addView(inflater.inflate((XmlPullParser) page, null));*//*
				}*/





				/*int index = 3;



				ImageView logo = (ImageView) findViewById(R.id.imageView);
				TextView text = (TextView) findViewById(R.id.locWordTextView);
				TextView text1 = (TextView) findViewById(R.id.textView);
				logo.setImageResource(resIds[index]);
				text.setText(loc_strings[index]);
				text1.setText(translate_strings[index]);
		*/
	}


	public void onClickButtonMenu(View v){
		onBackPressed();
	}

	public void onClickButtonRepeat(View v){
		Intent intent = new Intent(CardTestActivity.this, CardTestActivity.class);
		intent.putExtra("id_icon",icon_id);
		intent.putExtra("id_de",de_id);
		intent.putExtra("id_loc",loc_id);
		intent.putExtra("id",id);
		startActivity(intent);
	}

	/*public void onClickButtonNextTest(View v){
		Intent intent = new Intent(CardTestActivity.this, CardTestActivity.class);
		TypedArray ar = getResources().obtainTypedArray(R.array.ids);
		int len = ar.length();
		int[] resIds = new int[len];
		for (int i = 0; i < len; i++)
			resIds[i] = ar.getResourceId(i, 0);
		ar.recycle();


		id = id != 17 ? id++ : 0;
		intent.putExtra("id_icon",resIds[3* (int)id]);
		intent.putExtra("id_de",resIds[3*(int)id + 1]);
		intent.putExtra("id_loc",resIds[3*(int)id + 2]);
		intent.putExtra("id",(int)id);
		intent.putExtra("id",id);
		startActivity(intent);
	}*/
	private void next_page() {
		if (viewFlipper.getChildCount() > 2)
		{
			viewFlipper.removeViewAt(0);
			viewFlipper.removeViewAt(0);
			System.gc();
		}
		View view = inflater.inflate(R.layout.card_path1, null);
		ImageView icon = (ImageView) view.findViewById(R.id.imageView);
		icon.setImageResource(resIds[count / 2]);
		TextView loc_text = (TextView) view.findViewById(R.id.locWordTextView);
		loc_text.setText(loc_strings[count / 2]);
		viewFlipper.addView(view);

		view = inflater.inflate(R.layout.card_path2,null);
		TextView translate_text = (TextView) view.findViewById(R.id.translateTextView);
		translate_text.setText(translate_strings[count / 2]);
		viewFlipper.addView(view);
		viewFlipper.showNext();
	}

	private void updateTextView()
	{
		String pages = String.format("%1$s/%2$s", 1 + count / 2, len);
		txtPages.setText(pages);
	}
}
