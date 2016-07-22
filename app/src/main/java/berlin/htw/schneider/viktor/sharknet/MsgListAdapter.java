package berlin.htw.schneider.viktor.sharknet;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import net.sharksystem.sharknet.api.Message;

import java.util.List;

/**
 * Created by viktorowich on 01/06/16.
 */
public class MsgListAdapter extends RecyclerView.Adapter<MsgListAdapter.MyViewHolder>
{
    private List<Message> msgs;
    Drawable check,cross;

    public MsgListAdapter( List<Message> objects)
    {
        msgs = objects;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        public TextView msg;


        public MyViewHolder(View itemView)
        {
            super(itemView);
            msg = (TextView) itemView.findViewById(R.id.msg);
             check = itemView.getResources().getDrawable(R.drawable.ic_check_green_600_18dp);
             cross = itemView.getResources().getDrawable(R.drawable.ic_close_red_300_18dp);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.line_item_msg,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {
        Message message = msgs.get(position);
        SpannableStringBuilder builder = new SpannableStringBuilder();
        String s = new java.text.SimpleDateFormat("HH:mm").format(message.getTimestamp());
        builder.append(message.getContent().getMessage()).append("         ");
        builder.append(s).append("   ");

        assert check != null;
        check.setBounds(0, 0, holder.msg.getLineHeight(),holder.msg.getLineHeight());
        assert cross != null;
        cross.setBounds(0, 0, holder.msg.getLineHeight(),holder.msg.getLineHeight());
        if(message.isMine())
        {
            //holder.msg.setTextAlignment();
            holder.msg.setBackgroundColor(Color.parseColor("#FFC2185B"));
            holder.msg.setTextColor(Color.WHITE);

            Log.d("00000000"," ist meine ");
        }

        if(message.isEncrypted())
        {
            builder.setSpan(new ImageSpan(check),builder.length() - 1,builder.length(),0);
            Log.d("Viktor","isEncrypted");
        }
        else
        {
            builder.setSpan(new ImageSpan(cross),builder.length() - 1,builder.length(),0);
            Log.d("Viktor","not isEncrypted");

        }
        if (message.isSigned())
        {
            builder.setSpan(new ImageSpan(check),builder.length() - 2,builder.length(),0);
            Log.d("Viktor","isSigned");

        }
        else
        {
            builder.setSpan(new ImageSpan(cross),builder.length() - 2,builder.length(),0);
            Log.d("Viktor","not isSigned");

        }

        if (message.isVerified())
        {
            builder.setSpan(new ImageSpan(check),builder.length() - 3,builder.length(),0);
            Log.d("Viktor","isVerified");

        }
        else
        {
            Log.d("Viktor","not isVerified");
            builder.setSpan(new ImageSpan(cross),builder.length() - 3,builder.length(),0);

        }
        holder.msg.setText(builder);
    }

    @Override
    public int getItemCount()
    {
        return msgs.size();
    }
}
