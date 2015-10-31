package com.peoit.twopointcf.ui.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.jayfang.dropdownmenu.DropDownMenu;
import com.jayfang.dropdownmenu.OnMenuSelectedListener;
import com.peoit.twopointcf.R;
import com.peoit.twopointcf.entity.ProjectBean;
import com.peoit.twopointcf.presenters.impl.SearchProjectPresenter;
import com.peoit.twopointcf.presenters.interfaces.ISearchProject;
import com.peoit.twopointcf.ui.adapter.InvestFindAdapter;
import com.peoit.twopointcf.ui.base.BaseActivity;
import com.peoit.twopointcf.ui.view.pullview.AbPullToRefreshView;
import com.peoit.twopointcf.utils.DataPickDialogUtil;
import com.peoit.twopointcf.utils.DialogTool;
import com.peoit.twopointcf.utils.MyLogger;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends BaseActivity implements SearchProjectPresenter.OnHttpResultListener,
        AbPullToRefreshView.OnFooterLoadListener, AdapterView.OnItemClickListener, DropDownMenu.OnMenuSelectedNoListViewListener {
    private DropDownMenu mMenu;
    private ListView mList;
    private EditText searchText;

    private int arr1_index = -1;
    private int arr2_index = -1;
    private List<String> data;
    final String[] arr1 = new String[]{"所有行业", "农畜", "制造", "建筑", "酒店", "餐饮", "能源", "环保", "交通", "仓储", "零售", "服务", "公益", "购物"};
    final String[] arr2 = new String[]{"全部", "65%", "70%", "75%", "80%", "85%", "90%", "95%"};
    final String[] arr3 = new String[]{"筛选", "金额排序", "结束时间", "项目类型"};
    final String[] moneySort = new String[]{"不排序", "由高到低"};
    final String[] industryTypes = new String[]{"全部", "动产", "不动产"};
    private ISearchProject presenter;
    private List<ProjectBean> projectBeans = new ArrayList<>();
    private InvestFindAdapter adapter;

    private String city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

    }

    public static void startThisActivity(String city, Context context) {
        Intent intent = new Intent(context, SearchActivity.class);
        intent.putExtra("city", city);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
        pullview.setPullRefreshEnable(false);
        pullview.setOnFooterLoadListener(this);
        mMenu = (DropDownMenu) findViewById(R.id.menu);
        mList = (ListView) findViewById(R.id.lv_list);
        mList.setOnItemClickListener(this);
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
                    requestServer();

                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void initData() {
        city = getIntent().getStringExtra("city");
        params.put("city", city);
        if (city.equals("全国")) {
            params.put("city", "");
        }
        presenter = new SearchProjectPresenter(this, projectBeans);
        adapter = new InvestFindAdapter(this, projectBeans);
        mList.setAdapter(adapter);

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
                MyLogger.i(">>>>>>>>>>>>>>>>>>>>>SearchActivity", "select " + ColumnIndex + " column and " + RowIndex + " row");
                if (ColumnIndex == 0) {
                    arr1_index = RowIndex;
                } else if (ColumnIndex == 1) {
                    arr2_index = RowIndex;
                }
                requestServer();
            }
        });

        mMenu.setMenuSelectedNoListViewListener(this);
    }

    @Override
    public void requestServer() {
        super.requestServer();
        String searchContent = searchText.getText().toString().trim();
        if (TextUtils.isEmpty(searchContent)) {
            myToast("搜索内容不能为空");
            return;
        } else {
            if (arr1_index != -1) {
                if(arr1_index==0){
                    params.put("industryType", "");
                }else {
                    params.put("industryType", arr1[arr1_index]);
                }
            }
            if (arr2_index != -1) {
                if(arr2_index==0){
                    params.put("amountPercent", "");
                }else {
                    String amountPercent_str=arr2[arr2_index].substring(0,2);
                    params.put("amountPercent", "0."+amountPercent_str);
                }
            }
            if (textView_enddate != null) {
                String enddate = textView_enddate.getText().toString().trim();
                if (!TextUtils.isEmpty(enddate)&&enddate.contains("-")) {
                    params.put("endDate", enddate);
                }
            }
            params.put("projectName", searchContent);
            presenter.getData(params, false);
        }
    }

    @Override
    protected void updateView() {
        if (titleView != null) {
            titleView.showEditText();
        }
    }


    private void setFilter() {

    }


    @Override
    public void onHttpResultSuccess() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onHttpResult() {
        pullview.onFooterLoadFinish();
    }

    @Override
    public void onFooterLoad(AbPullToRefreshView view) {
        presenter.getData(params, true);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        InvestFindDetailActivity.startThisActivity(projectBeans.get(position), false, this);
    }

    private TextView textView_enddate;

    @Override
    public void onSelected(final TextView textView) {
        int tag = (int) textView.getTag();
        switch (tag) {
            case 1:
                DialogTool.createRadioDialog(this, R.mipmap.ic_launcher, "金额排序", moneySort, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        textView.setText(moneySort[which]);
                        //params.put("industryType", moneySort[which]);
                        dialog.dismiss();
                    }
                });
                break;
            case 2:
                textView_enddate = textView;
                DataPickDialogUtil dataPickDialogUtil = new DataPickDialogUtil(this);
                dataPickDialogUtil.dateTimePicKDialog(textView);
                break;
            case 3:
                DialogTool.createRadioDialog(this, R.mipmap.ic_launcher, "项目类型", industryTypes, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        textView.setText(industryTypes[which]);
                        if (which == 0) {
                            params.put("projectType", "");
                        } else {
                            params.put("projectType", industryTypes[which]);
                        }
                        dialog.dismiss();
                    }
                });
                break;
        }

    }

    @Override
    public void onRequest() {
        requestServer();
    }
}
