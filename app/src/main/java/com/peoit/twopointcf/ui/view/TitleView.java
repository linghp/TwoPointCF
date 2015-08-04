package com.peoit.twopointcf.ui.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.peoit.twopointcf.R;

/**
 * @author ling
 *	自定义标题栏
 */
public class TitleView extends FrameLayout{
	private Activity mActivity; // 当前activity
	private ImageView btn_left, btn_right;
	private TextView tv_title;


	public TitleView(Context context) {
		this(context, null);
	}

	public TitleView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public TitleView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.title_view, this, true);

		btn_left = (ImageView) findViewById(R.id.left_btn);
		btn_right = (ImageView) findViewById(R.id.right_btn);
		tv_title = (TextView) findViewById(R.id.title_text);
		setBack();
	}

	/**
	 * 设置返回的一系列事件
	 */
	public void setBack(){
		btn_left.setImageResource(R.mipmap.back);
		btn_left.setOnClickListener(mListener);
	}
	/**
	 * 设置左按钮
	 */
	public void setLeftBtn(int resid,OnClickListener listener){
		btn_left.setImageResource(resid);
		btn_left.setOnClickListener(listener);
	}

	/**
	 * 显示左按钮
	 */
	public void showLeftBtn(){
		btn_left.setVisibility(View.VISIBLE);
	}

	/**
	 * 隐藏左按钮
	 */
	public void hideLeftBtn(){
		btn_left.setVisibility(View.INVISIBLE);
	}

	/**
	 * 右按钮点击事件
	 * @param listener
	 */
	public void setRightBtn(int resid,OnClickListener listener){
        btn_right.setVisibility(View.VISIBLE);
		btn_right.setImageResource(resid);
		btn_right.setOnClickListener(listener);
	}

	/**
	 * 显示右按钮
	 */
	public void showRightBtn(){
		btn_right.setVisibility(View.VISIBLE);
	}

	/**
	 * 隐藏右按钮
	 */
	public void hideRightBtn_invisible(){
		btn_right.setVisibility(View.INVISIBLE);
		btn_right.setOnClickListener(null);
	}

	public void setActivity(Activity curActivity){
		this.mActivity = curActivity;
	}

	public void setTitle(int title){
		tv_title.setText(getContext().getText(title));
		tv_title.setVisibility(View.VISIBLE);
	}

	public void showTitle(){
		tv_title.setVisibility(View.VISIBLE);
	}


	public void hideTitle(){
		tv_title.setVisibility(View.INVISIBLE);
	}

	/**
	 * 返回按钮固定点击事件
	 */
	private OnClickListener mListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(mActivity != null){
				mActivity.finish();
				//mActivity.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
			}
		}
	};
}
