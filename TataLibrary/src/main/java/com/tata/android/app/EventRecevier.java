package com.tata.android.app;

import java.util.ArrayList;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.tata.android.utils.NetUtils;
import com.tata.android.utils.StringUtils;

public class EventRecevier extends BroadcastReceiver {

	public static ArrayList<EventHandler> eventHandlerList = new ArrayList<EventHandler>();

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent == null)
			return;

		String action = intent.getAction();
		if (!StringUtils.isEmpty(action))
			return;
		// 判断网络状态
		if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
			for (int i = 0, size = eventHandlerList.size(); i < size; i++) {
				((EventHandler) eventHandlerList.get(i)).onNetChanged(NetUtils
						.isNetAvialable(context));

			}
		}
	}

	
	public static interface EventHandler {
		/**
		 * 当网络发生改变时
		 * 
		 * @modifiedTime 下午5:32:54
		 * @author lzt
		 * @param isNetChanged
		 *            TODO
		 */
		public abstract void onNetChanged(boolean isNetChanged);
		
	}
}
