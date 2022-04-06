package com.serveme_user.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.serveme_user.R;
import com.serveme_user.constant.VariableConstant;
import com.serveme_user.model.MessageModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder>
{

    private ArrayList<MessageModel> messageModels;

    public MessageAdapter(ArrayList<MessageModel> messageModels)
    {
        this.messageModels = messageModels;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
//        if (viewType == VariableConstant.MESSAGE_LEFT)
//        {
//            return new MessageViewHolder(ItemMessageLeftBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
//        }
//
//        else
//        {
////            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_right_chat, parent, false);
////            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_left_chat, parent, false);
//            return new MessageViewHolder(ItemMessageRightBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
//
//        }
//        return new ChatViewHolder(view);

        View view;
        if (viewType == VariableConstant.MESSAGE_LEFT)
        {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_left, parent, false);
        }

        else
        {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_right, parent, false);
        }
        return new MessageViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position)
    {
        MessageModel model = messageModels.get(position);

        holder.txtMessage.setText(model.getMessage());
//        holder.txtNameEmployee.setText(model.getEmployeeName());
//        Picasso
//                .get()
//                .load(model.getEmployeeImage())
//                .placeholder(R.drawable.ic_no_photo)
//                .into(holder.imageEmployee);
    }

    @Override
    public int getItemCount()
    {
        return messageModels.size();
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder
    {

        private TextView txtMessage;
//        private CircleImageView imageEmployee;

        public MessageViewHolder(@NonNull View itemView)
        {
            super(itemView);

            txtMessage = itemView.findViewById(R.id.txt_item_message);
//            txtNameEmployee = itemView.findViewById(R.id.txt_name_item_message);
//            imageEmployee = itemView.findViewById(R.id.img_item_message);
        }
    }


    @Override
    public int getItemViewType(int position)
    {
        MessageModel messageModel =  messageModels.get(position);

        if (messageModel.getId().equals(VariableConstant.User_ID))
        {
            return VariableConstant.MESSAGE_LEFT;
        }

        else
        {
            return VariableConstant.MESSAGE_RIGHT;
        }
    }
}
