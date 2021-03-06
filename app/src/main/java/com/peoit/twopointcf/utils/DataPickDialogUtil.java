package com.peoit.twopointcf.utils;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.peoit.twopointcf.R;

/**
 * 用法：DataPickDialogUtil dataPickDialogUtil = new DataPickDialogUtil(当前类.this);实例化
 dataPickDialogUtil.dateTimePicKDialog(textview);
 * 日期选择器
 * Created by zyz on 2015/9/29.
 */
public class DataPickDialogUtil{
    private DatePicker datePicker;
    private Activity activity;
    private AlertDialog ad;
    /**
     * 日期时间弹出选择框构造函数
     *
     * @param activity
     *            ：调用的父activity
     * @param
     *
     */
    public DataPickDialogUtil(Activity activity) {
        this.activity = activity;
    }
    /**
     * 弹出日期时间选择框方法
     *
     * @param inputDate
     *            :为需要设置的日期时间文本编辑框
     * @return
     */
    public AlertDialog dateTimePicKDialog(final TextView inputDate) {
        LinearLayout dateTimeLayout = (LinearLayout) activity
                .getLayoutInflater().inflate(R.layout.common_datetime, null);
        datePicker = (DatePicker) dateTimeLayout.findViewById(R.id.datepicker);

        ad = new AlertDialog.Builder(activity)
                .setTitle("请设置时间")
                .setView(dateTimeLayout)
                .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        StringBuffer sb = new StringBuffer();
                        sb.append(String.format("%d-%02d-%02d",
                                datePicker.getYear(),
                                datePicker.getMonth() + 1,
                                datePicker.getDayOfMonth()));
                        inputDate.setText(sb.toString());
                        dialog.cancel();

                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
//                        inputDate.setText("");
                    }
                }).show();
        return ad;
    }

}
