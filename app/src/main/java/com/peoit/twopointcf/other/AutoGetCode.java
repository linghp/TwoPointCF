package com.peoit.twopointcf.other;

import android.app.Activity;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ling on 2015/11/13.
 * description:建立一个监听数据库的类 短信
 */
public class AutoGetCode extends ContentObserver {
    private Cursor cursor = null;
    private Activity activity;

    private String smsContent = "";
    private EditText editText = null;

    public AutoGetCode(Activity activity, Handler handler, EditText edittext) {
        super(handler);
        this.activity = activity;
        this.editText = edittext;
    }

    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
        // 读取收件箱中指定号码的短信
        cursor = activity.managedQuery(Uri.parse("content://sms/inbox"),
                new String[]{"_id", "address", "read", "body"}, "address=? and read=?",
                new String[]{"你要截获的电话号码", "0"}, "_id desc");
        // 按短信id排序，如果按date排序的话，修改手机时间后，读取的短信就不准了
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            if (cursor.moveToFirst()) {
                String smsbody = cursor
                        .getString(cursor.getColumnIndex("body"));
                String regEx = "(?<![0-9])([0-9]{" + 6 + "})(?![0-9])";
                Pattern p = Pattern.compile(regEx);
                Matcher m = p.matcher(smsbody.toString());
                while (m.find()) {
                    smsContent = m.group();
                    editText.setText(smsContent);
                }
            }
        }
    }
}