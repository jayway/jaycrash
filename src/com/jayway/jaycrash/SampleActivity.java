package com.jayway.jaycrash;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zubhium.ZubhiumSDK;
import com.zubhium.ZubhiumSDK.CrashReportingMode;

public class SampleActivity extends Activity {

	ZubhiumSDK sdk;
	TextView test;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		sdk = ZubhiumSDK.getZubhiumSDKInstance(getApplicationContext(), "cd2d023a87dc85d9158d43dffc1599	");

		if (sdk != null) {
			sdk.setCrashReportingMode(CrashReportingMode.SILENT);
		}

		sdk.registerUpdateReceiver(this);

		Button feedbackButton = (Button) findViewById(R.id.feedbackButton);
		feedbackButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				setupFeedback();
			}
		});

		Button crashDemoButton = (Button) findViewById(R.id.crashReporting);
		crashDemoButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				throw new RuntimeException("This is runtime exception");
			}
		});
	}

	protected void setupFeedback() {
		/**
		 * Now lets listen to users, by enabling in app help desk. *
		 */
		if (sdk != null) {
			sdk.openFeedbackDialog(SampleActivity.this);
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		sdk.unRegisterUpdateReceiver();
		super.onDestroy();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}
}