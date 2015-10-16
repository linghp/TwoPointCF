package com.peoit.twopointcf.ui.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.jayfang.dropdownmenu.DropDownMenu;
import com.jayfang.dropdownmenu.OnMenuSelectedListener;
import com.peoit.twopointcf.R;
import com.peoit.twopointcf.presenters.impl.InvestFindPresenter;
import com.peoit.twopointcf.presenters.interfaces.IInvestFind;
import com.peoit.twopointcf.ui.base.BaseActivity;
import com.peoit.twopointcf.ui.view.LoadingLayout;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends BaseActivity implements InvestFindPresenter.OnHttpResultListener {
    private DropDownMenu mMenu;
    private ListView mList;
    private EditText searchText;

    private int city_index;
    private int sex_index;
    private int age_index;
    private List<String> data;
    final String[] arr1 = new String[]{"所有行业", "农畜", "制造", "建筑", "酒店","餐饮","能源","环保","交通","仓储","零售","服务","公益","购物","其他"};
    final String[] arr2 = new String[]{"众筹进度", "65%", "70%", "75%", "80%", "85%", "90%", "95%"};
    final String[] arr3 = new String[]{"筛选", "金额排序", "结束时间", "项目类型"};
    private IInvestFind mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        testLoadingLayout();
    }

    private void testLoadingLayout() {
        final LoadingLayout loadingLayout = (LoadingLayout) findViewById(R.id.loading_layout);
        findViewById(R.id.btn_show_content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingLayout.showContent();
            }
        });
        findViewById(R.id.btn_show_error).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingLayout.showError();
            }
        });
        findViewById(R.id.btn_show_empty).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingLayout.showEmpty();
            }
        });
        findViewById(R.id.btn_show_loading).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingLayout.showLoading();
            }
        });

        loadingLayout.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingLayout.showLoading();
            }
        });
    }

    @Override
    protected void initView() {
        mMenu = (DropDownMenu) findViewById(R.id.menu);
        mList = (ListView) findViewById(R.id.lv_list);
        searchText = titleView.getEt_search();
        searchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    // 先隐藏键盘'
                    ((InputMethodManager) searchText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(
                                    SearchActivity.this
                                            .getCurrentFocus()
                                            .getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    mPresenter.getData();

                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter = new InvestFindPresenter(this);

        mMenu.setMenuCount(3);
        mMenu.setShowCount(6);
        mMenu.setShowCheck(true);
        mMenu.setMenuTitleTextSize(16);
        mMenu.setMenuTitleTextColor(Color.BLACK);
        mMenu.setMenuListTextSize(16);
        mMenu.setMenuListTextColor(Color.BLACK);
//        mMenu.setMenuBackColor(Color.GRAY);
//        mMenu.setMenuPressedBackColor(Color.CYAN);

        mMenu.setCheckIcon(R.drawable.ico_make);

        mMenu.setUpArrow(R.drawable.arrow_up);
        mMenu.setDownArrow(R.drawable.arrow_down);

        List<String[]> items = new ArrayList<>();
        items.add(arr1);
        items.add(arr2);
        items.add(arr3);

        mMenu.setMenuItems(items);
        mMenu.setMenuSelectedListener(new OnMenuSelectedListener() {
            @Override
            public void onSelected(View listview, int RowIndex, int ColumnIndex) {
                Log.i("SearchActivity", "select " + ColumnIndex + " column and " + RowIndex + " row");
                if (ColumnIndex == 0) {
                    city_index = RowIndex;
                } else if (ColumnIndex == 1) {
                    sex_index = RowIndex;
                } else {
                    age_index = RowIndex;
                }
                //过滤筛选
                setFilter();
            }
        });

        data = getData();
        mList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, data));
    }

    @Override
    protected void updateView() {
        if (titleView != null) {
            titleView.showEditText();
        }
    }


    private void setFilter() {
        List<String> temp = new ArrayList<String>();
        for (int i = 0; i < getData().size(); i++) {
            boolean city = ((city_index == 0) ? true : data.get(i).contains(arr1[city_index]));
            boolean sex = ((sex_index == 0) ? true : data.get(i).contains(arr2[sex_index]));
            boolean age = ((age_index == 0) ? true : data.get(i).contains(arr3[age_index]));
            if (city && sex && age) {
                temp.add(data.get(i));
            }
        }
        mList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, temp));
    }

    private List<String> getData() {
        List<String> data = new ArrayList<String>();
        /*data.add("上海－男－10");
        data.add("上海－男－20");
        data.add("上海－男－30");
        data.add("上海－男－40");
        data.add("上海－男－50");
        data.add("上海－男－60");
        data.add("上海－男－70");
        data.add("广州－男－10");
        data.add("广州－女－10");
        data.add("北京－男－20");
        data.add("北京－女－10");
        data.add("广州－男－10");
        data.add("北京－男－10");
        data.add("广州－男－10");
        data.add("上海－女－60");
        data.add("上海－女－20");*/
        return data;
    }

    @Override
    public void onHttpResultSuccess() {
        myToast("onHttpResultSuccess");
    }
}
