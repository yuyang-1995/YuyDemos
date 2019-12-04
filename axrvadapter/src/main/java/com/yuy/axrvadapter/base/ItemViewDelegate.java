package com.yuy.axrvadapter.base;

/**
 * from : yuy
 * Date : 2019/12/4
 * Description :
 * Version :
 */
public interface ItemViewDelegate<T> {
    int getItemViewLayoutId();

    boolean isForViewType(T item, int position);

    void convert(ViewHolder holder, T t, int position);
}
