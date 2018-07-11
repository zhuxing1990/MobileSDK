package com.vunke.sharehome2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.vunke.sharehome2.Call.CallOut_Activity;
import com.vunke.sharehome2.base.BaseActivity;
import com.vunke.sharehome2.model.LoginSDK;
import com.vunke.sharehome2.service.LoginService;
import com.vunke.sharehome2.utils.CommonUtil;
import com.vunke.sharehome2.utils.MD5Util;
import com.vunke.sharehome2.utils.UiUtils;
import com.vunke.sharehome2.utils.WorkLog;

public class MainActivity extends BaseActivity {
	private TextView main_textview;
	private Button main_button;
	@Override
	public void OnCreate() {
		setContentView(R.layout.activity_main);
		main_textview = (TextView) findViewById(R.id.main_textview);
		main_button = (Button) findViewById(R.id.main_button);
		main_button.setOnClickListener(this);
		Config.intent = new Intent(MainActivity.this, LoginService.class);
		// 测试Action
		// intent.setAction(LoginSDK.LOGIN_TEST_ACTION);
		// 正式Action
		Config.intent.setAction(LoginSDK.LOGIN_ACTION);
		String mobile_no = "13278875981";
		long channel_Id = 20180701;
		long timestamp = System.currentTimeMillis();
		String str = LoginSDK.key + channel_Id + mobile_no + timestamp;
		String sign = MD5Util.encryptByMD5(str, LoginSDK.key);
		Config.intent.putExtra("mobile_no", mobile_no);
		Config.intent.putExtra("channel_Id", channel_Id);
		Config.intent.putExtra("timestamp", timestamp);
		Config.intent.putExtra("sign", sign);
		Config.intent.putExtra("calledParty", "13278875981");
		 Config.intent.putExtra("calledType", Config.EIGHT);// TV客户端
//		Config.intent.putExtra("calledType", Config.NINE);// 手机客户端
		startService(Config.intent);
		initBroadcast();
	}

	@Override
	public void OnClick(View v) {
		if(v.getId()== R.id.main_button){
			initCall();
		}
	}

	private void startCalling() {
		if (!TextUtils.isEmpty(calledParty)) {
            Config.intent = new Intent(getApplicationContext(),
                    CallOut_Activity.class);
            Config.intent.putExtra("is_video_call", true);
            UiUtils.isCallCode(calledParty);
            UiUtils.isCallNumber(calledParty);
            if (!TextUtils.isEmpty(calledType)) {
                Config.intent.putExtra("PhoneNumber", Config.CALL_BEFORE
                        + calledType + calledParty);
            } else {
                Config.intent.putExtra("PhoneNumber", Config.CALL_BEFORE
                        + Config.EIGHT + calledParty);
            }
            startActivity(Config.intent);
//            finish();
        } else {
            UiUtils.showToast("被叫号码为空,无法进行呼叫", getApplicationContext());
//            finish();
        }
	}

	private void initBroadcast() {
		registerReceiver(LoginStatusChanged, new IntentFilter(
				LoginSDK.LOGIN_STATUS_CHAGED));
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(LoginStatusChanged);
	}

	public BroadcastReceiver LoginStatusChanged = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (intent.hasExtra("login_status")) {
				int login_status = intent.getIntExtra("login_status", -1);
				switch (login_status) {
				case LoginSDK.LOGIN_SUCCESS:
					WorkLog.e("TestActivity", "登录成功");
					if (intent.hasExtra("calledParty")) {
						calledParty = intent.getStringExtra("calledParty");
					}
					if (intent.hasExtra("calledType")) {
						calledType = intent.getStringExtra("calledType");
					}
					main_textview.setText("登录成功...");
					main_button.setVisibility(View.VISIBLE);
					main_button.requestFocus();
					if (CommonUtil.isServiceRunning(getApplicationContext(),
							LoginService.class)) {
						intent = new Intent(MainActivity.this, LoginService.class);
						stopService(intent);
					} else {
						System.out.println();
					}
					break;
				case LoginSDK.LOGIN_ERROR:
					WorkLog.e("TestActivity", "登录失败");
					if (intent.hasExtra("login_reson")) {
						String login_reson = intent
								.getStringExtra("login_reson");
						WorkLog.e("TestActivity", login_reson);
						main_textview.setText("登录失败：" + login_reson);
					}
					break;
				case LoginSDK.LOGIN_LOADING:
					WorkLog.e("TestActivity", "正在登录中...");
					main_textview.setText("正在登录中...");
					break;

				default:
					WorkLog.e("TestActivity", "获取登录状态失败");
					break;
				}
			}
		}
	};
	private String calledParty = "";
	private String calledType = "";

	private void initCall() {
		if (ContextCompat.checkSelfPermission(this,
				android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
			UiUtils.showToast("无法打开摄像头,请允许打开摄像头权限",getApplicationContext());
			//先判断有没有权限 ，没有就在这里进行权限的申请
			ActivityCompat.requestPermissions(this,
					new String[]{android.Manifest.permission.CAMERA}, 1);
		} else {
			startCalling();
		}
	}
}
