package com.vunke.sharehome2.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.vunke.sharehome2.Config;
import com.vunke.sharehome2.service.NetConnectService;
import com.vunke.sharehome2.utils.NetUtils;

/**
 * C_only：Administrator on 2016/2/2 18:10
 */
public class NetChangeReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		boolean netConnected = NetUtils.isNetConnected(context);

		if (netConnected) {
			if (Config.net_connect_true == 0) {
//				WorkLog.e("NetChangeReceiver", "------------------"+ netConnected + "------------------");
				Config.net_connect_true = 1;
				Config.intent = new Intent(context,NetConnectService.class);
				context.startService(Config.intent);
			}

		} else {
			Config.net_connect_true = 0;
			
		}

	}
}
