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
 * Description :
 * Version :
 */
public class MsgComItemDelegate implements ItemViewDelegate<ChatMessage> {

    private Context mContext;
    public MsgComItemDelegate(Context context) {
        this.mContext = context;
    }

    @Override
    public int getItemViewLayoutId() {
        return R.layout.main_chat_from_msg;
    }

    @Override
    public boolean isForViewType(ChatMessage item, int position) {
        return item.isComMeg();
    }

    @Override
    public void convert(ViewHolder holder, final ChatMessage chatMessage, int position) {

        holder.setText(R.id.chat_from_content, chatMessage.getContent());
        holder.setText(R.id.chat_from_name, chatMessage.getName());
        holder.setImageResource(R.id.chat_from_icon, chatMessage.getIcon());

        holder.setOnClickListener(R.id.chat_from_icon, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,chatMessage.getName() + "",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
