package com.peoit.twopointcf.ui.activity;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.citylistdemo.CharacterParser;
import com.example.citylistdemo.MyLetterAlistView;
import com.example.citylistdemo.MyLetterAlistView.OnTouchingLetterChangedListener;
import com.example.citylistdemo.PinyinComparator;
import com.example.citylistdemo.SortAdapter;
import com.example.citylistdemo.SortModel;
import com.peoit.twopointcf.R;
import com.peoit.twopointcf.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CityActivity extends BaseActivity {
    // 搜索内容
    private EditText editText;
    // 城市列表
    private ListView sortListView;
    // 右侧A-Z字母列表
    private MyLetterAlistView letterListView;
    // dialog text
    private TextView overlay;
    // 估计是弹出dialog线程
    private OverlayThread overlayThread;
    // 城市Adapter
    private SortAdapter adapter;
    private Handler handler;
    /**
     * 汉字转换成拼音的类
     */
    private CharacterParser characterParser;
    private List<SortModel> SourceDateList;
    /**
     * 根据拼音来排列ListView里面的数据类
     */
    private PinyinComparator pinyinComparator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);

    }

    @Override
    protected void initView() {
        handler = new Handler();
        overlayThread = new OverlayThread();
        // 实例化汉字转拼音类
        characterParser = CharacterParser.getInstance();
        // 根据拼音排序
        pinyinComparator = new PinyinComparator();

        editText = (EditText) findViewById(R.id.editText);
        sortListView = (ListView) findViewById(R.id.country_lvcountry);
        letterListView = (MyLetterAlistView) findViewById(R.id.cityLetterListView);
        SourceDateList = filledData(getResources().getStringArray(
                R.array.province));

        View headerview=getLayoutInflater().inflate(R.layout.citylistheader,null);
        sortListView.addHeaderView(headerview);

        // 根据a-z进行排序源数据
        Collections.sort(SourceDateList, pinyinComparator);
        adapter = new SortAdapter(this, SourceDateList);
        sortListView.setAdapter(adapter);
        initOverlay();
        letterListView
                .setOnTouchingLetterChangedListener(new LetterListViewListener());
        sortListView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // 这里要利用adapter.getItem(position)来获取当前position所对应的对象
                Toast.makeText(getApplication(),
                        ((SortModel) adapter.getItem(position)).getName(),
                        Toast.LENGTH_SHORT).show();
                finish();


            }
        });
        // 根据输入框输入值的改变来过滤搜索
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                // 当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
                filterData(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void updateView() {
        if (titleView != null) {
            titleView.setTitle(R.string.citylist);
        }
    }

    // 右侧A-Z字母监听
    private class LetterListViewListener implements
            OnTouchingLetterChangedListener {

        @Override
        public void onTouchingLetterChanged(final String s) {
            // 该字母首次出现的位置
            int position = adapter.getPositionForSection(s.charAt(0));
            if (position != -1) {
                sortListView.setSelection(position);
                overlay.setText(SourceDateList.get(position).getSortLetters());
                overlay.setVisibility(View.VISIBLE);
                handler.removeCallbacks(overlayThread);
                // 延迟一秒后执行，让overlay为不可见
                handler.postDelayed(overlayThread, 1500);
            }
        }
    }

    // 初始化汉语拼音首字母弹出提示框
    private void initOverlay() {
        LayoutInflater inflater = LayoutInflater.from(this);
        overlay = (TextView) inflater.inflate(R.layout.overlay, null);
        overlay.setVisibility(View.INVISIBLE);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                PixelFormat.TRANSLUCENT);
        WindowManager windowManager = (WindowManager) this
                .getSystemService(Context.WINDOW_SERVICE);
        windowManager.addView(overlay, lp);
    }

    // 设置overlay不可见
    private class OverlayThread implements Runnable {

        @Override
        public void run() {
            overlay.setVisibility(View.GONE);
        }

    }

    /**
     * 为ListView填充数据
     *
     * @param date
     * @return
     */
    private List<SortModel> filledData(String[] date) {
        List<SortModel> mSortList = new ArrayList<SortModel>();

        for (int i = 0; i < date.length; i++) {
            SortModel sortModel = new SortModel();
            sortModel.setName(date[i]);
            // 汉字转换成拼音
            String pinyin = characterParser.getSelling(date[i]);
            String sortString = pinyin.substring(0, 1).toUpperCase();

            // 正则表达式，判断首字母是否是英文字母
            if (sortString.matches("[A-Z]")) {
                sortModel.setSortLetters(sortString.toUpperCase());
            } else {
                sortModel.setSortLetters("#");
            }

            mSortList.add(sortModel);
        }
        return mSortList;

    }

    /**
     * 根据输入框中的值来过滤数据并更新ListView
     *
     * @param filterStr
     */
    private void filterData(String filterStr) {
        List<SortModel> filterDateList = new ArrayList<SortModel>();

        if (TextUtils.isEmpty(filterStr)) {
            filterDateList = SourceDateList;
        } else {
            filterDateList.clear();
            for (SortModel sortModel : SourceDateList) {
                String name = sortModel.getName();
                if (name.indexOf(filterStr.toString()) != -1
                        || characterParser.getSelling(name).startsWith(
                        filterStr.toString())) {
                    filterDateList.add(sortModel);
                }
            }
        }

        // 根据a-z进行排序
        Collections.sort(filterDateList, pinyinComparator);
        adapter.updateListView(filterDateList);
    }
}
