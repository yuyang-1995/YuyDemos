package com.yuy.hyrvadapter.delagate;


import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.yuy.hyrvadapter.R;
import com.yuy.hyrvadapter.bean.ChatMessage;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

/**
 * from : yuy
 * Date : 2019/12/4
 * Description : Rv 多布局 item Delegate
 * Version :
 */
public class MsgSendItemDelegate implements ItemViewDelegate<ChatMessage> {

    private Context mContext;
    public MsgSendItemDelegate(Context context) {
        this.mContext = context;
    }

    @Override
    public int getItemViewLayoutId() {
        //返回 布局 id
        return R.layout.main_chat_send_msg;
    }

    @Override
    public boolean isForViewType(ChatMessage item, int position) {
        //确认item 类型
        return !item.isComMeg();
    }

    @Override
    public void convert(ViewHolder holder, final ChatMessage chatMessage, int position) {
       //数据、事件绑定

        //数据填充
        holder.setText(R.id.chat_send_content, chatMessage.getContent());
        holder.setText(R.id.chat_send_name, chatMessage.getName());
        holder.setImageResource(R.id.chat_send_icon, chatMessage.getIcon());

        //事件绑定
        holder.setOnClickListener(R.id.chat_send_icon, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,chatMessage.getName() + "",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
