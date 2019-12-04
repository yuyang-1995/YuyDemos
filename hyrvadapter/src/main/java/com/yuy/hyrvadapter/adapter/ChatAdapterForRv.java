package com.yuy.hyrvadapter.adapter;

import android.content.Context;

import com.yuy.hyrvadapter.bean.ChatMessage;
import com.yuy.hyrvadapter.delagate.MsgComItemDelegate;
import com.yuy.hyrvadapter.delagate.MsgSendItemDelegate;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.List;

/**
 * from : yuy
 * Date : 2019/12/4
 * Description : Rv多布局适配器 泛型为数据类型
 * Version :
 */
public class ChatAdapterForRv extends MultiItemTypeAdapter<ChatMessage> {

    public ChatAdapterForRv(Context context, List<ChatMessage> datas) {
        //在构造函数中 添加 多布局的 item Delegate
        super(context, datas);

        //添加 多布局的 Delegate
        addItemViewDelegate(new MsgSendItemDelegate(context));
        addItemViewDelegate(new MsgComItemDelegate(context));

    }

}
