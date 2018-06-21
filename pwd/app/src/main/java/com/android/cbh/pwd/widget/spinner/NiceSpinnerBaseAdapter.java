package com.android.cbh.pwd.widget.spinner;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.cbh.pwd.R;

/**
 * @author angelo.marchesin，Spinner就是一个弹出的下拉选择菜单
 */

@SuppressWarnings("unused")
public abstract class NiceSpinnerBaseAdapter<T> extends BaseAdapter {
    protected Context mContext;
    protected int mSelectedIndex;

    public NiceSpinnerBaseAdapter(Context context) {
        mContext = context;
    }

    @Override
    @SuppressWarnings("unchecked")
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;

        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.spinner_list_item, null);
            textView = (TextView) convertView.findViewById(R.id.tv_tinted_spinner);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//                textView.setBackground(ContextCompat.getDrawable(mContext, R.drawable.selector));
            }

            convertView.setTag(new ViewHolder(textView));
        } else {
            textView = ((ViewHolder) convertView.getTag()).textView;
        }

        textView.setText(getItem(position).toString());

        return convertView;
    }

    public int getSelectedIndex() {
        return mSelectedIndex;
    }

    public void notifyItemSelected(int index) {
        mSelectedIndex = index;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public abstract T getItem(int position);
//    1、可以当注释用,方便阅读
//2、编译器可以给你验证@Override下面的方法名是否是你父类中所有的,如果没有则报错
    @Override
    public abstract int getCount();

    public abstract T getItemInDataset(int position);

    protected static class ViewHolder {

        public TextView textView;

        public ViewHolder(TextView textView) {
            this.textView = textView;
        }
    }
}
