package berlin.htw.schneider.viktor.sharknet;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import net.sharksystem.sharknet.api.Message;

import java.util.List;

/**
 * Reads the Information from the Chat-List and
 * fills the List-Items-Layout.
 */
public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.MyViewHolder>
{

    private List<net.sharksystem.sharknet.api.Chat> chats;
    Context context;
    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        public TextView title,text;
        ImageView image;
        public MyViewHolder(View itemView)
        {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.name);
            text = (TextView) itemView.findViewById(R.id.msg_text);
            image = (ImageView) itemView.findViewById(R.id.chat_image);
        }
    }
    public ChatListAdapter(List<net.sharksystem.sharknet.api.Chat> objects)
    {
        this.chats = objects;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.line_item_chat,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position)
    {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //v.getContext().startActivity(new Intent(v.getContext(),ChatDetailActivity.class));
                Intent intent = new Intent(v.getContext(),ChatDetailActivity.class);
                //context.startActivity(new Intent(context.this, ChatDetailActivity.class));
                intent.putExtra(Chat.CHAT_ID,chats.get(holder.getAdapterPosition()).getID());
                v.getContext().startActivity(intent);
            }
        });

        net.sharksystem.sharknet.api.Chat chat = chats.get(position);
        holder.title.setText(chat.getTitle());
        List<Message> msgs      = chat.getMessages(false);
        Log.d("adapter", String.valueOf(msgs.size()));
        Message last_msg        = msgs.get(msgs.size()-1);
        String content         = last_msg.getContent().getMessage();
        String sender = last_msg.getSender().getNickname();

        String last_msg_content = sender+":"+content;
        holder.text.setText(last_msg_content);

        if(chat.getPicture() != null) {
            if (chat.getContacts().size() > 1) {
                holder.image.setImageResource(R.drawable.ic_group_pink_600_24dp);
            } else {
                holder.image.setImageResource(R.drawable.ic_person_pink_600_24dp);
            }
        }
    }

    @Override
    public int getItemCount() {
        return chats.size();
    }
/*
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.line_item_chat,parent,false);
        }
        Typeface type = Typeface.createFromAsset(getContext().getAssets(),"fonts/RockSalt.TTF");
        net.sharksystem.sharknet.api.Chat chat = chats.get(position);
        //Title
        TextView title = (TextView) convertView.findViewById(R.id.name);
        title.setTypeface(type);


     //   title.setText(chat.getTitle());

        //Sender + Lastmessage-Text
        TextView text = (TextView) convertView.findViewById(R.id.msg_text);
        text.setTypeface(type);

        List<Message> msgs      = chat.getMessages(false);
        Log.d("adapter", String.valueOf(msgs.size()));
        Message last_msg        = msgs.get(msgs.size()-1);
        String content         = last_msg.getContent().getMessage();
        String sender = last_msg.getSender().getNickname();

        String last_msg_content = sender+":"+content;
     //   text.setText(last_msg_content);

        //Image
        ImageView image = (ImageView) convertView.findViewById(R.id.chat_image);
        //TODO: != change to ==  then load image works
        //if(chat.getPicture() != null)
        //{
            if(chat.getContacts().size()>1)
            {
                image.setImageResource(R.drawable.ic_group_pink_600_24dp);
            }
            else
            {
                image.setImageResource(R.drawable.ic_person_pink_600_24dp);
            }
        //}
       // else
        //{
           //TODO: image.setImageResource(chat.getPicture().);
        //‚}

        return convertView;
    }

    @Override
    public Chat onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(Chat holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }*/


}
