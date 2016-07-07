package berlin.htw.schneider.viktor.sharknet;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import net.sharksystem.sharknet.api.Message;

import java.util.List;

/**
 * Created by viktorowich on 01/06/16.
 */
public class MsgListAdapter extends ArrayAdapter<Message>
{
    private List<Message> msgs;

    public MsgListAdapter(Context context, int resource, List<Message> objects)
    {
        super(context, resource, objects);
        msgs = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.line_item_msg,parent,false);
        }

        Message msg = msgs.get(position);

        TextView text = (TextView) convertView.findViewById(R.id.msg);

        //Typeface type = Typeface.createFromAsset(getContext().getAssets(),"fonts/RockSalt.TTF");
        //text.setTypeface(type);
        SpannableStringBuilder builder = new SpannableStringBuilder();
        String s = new java.text.SimpleDateFormat("HH:mm").format(msg.getTimestamp());
        builder.append(msg.getContent().getMessage()).append("         ");
        builder.append(s).append("   ");
        Drawable check = convertView.getResources().getDrawable(R.drawable.check);
        Drawable cross = convertView.getResources().getDrawable(R.drawable.cross);
        check.setBounds(0, 0, text.getLineHeight(),text.getLineHeight());
        cross.setBounds(0, 0, text.getLineHeight(),text.getLineHeight());
        if(msg.isMine())
        {
            text.setGravity(Gravity.END);
            Log.d("00000000"," ist meiner ");
        }

        if(msg.isEncrypted())
        {
            builder.setSpan(new ImageSpan(check),builder.length() - 1,builder.length(),0);
            Log.d("Viktor","isEncrypted");
        }
        else
        {
            builder.setSpan(new ImageSpan(cross),builder.length() - 1,builder.length(),0);
            Log.d("Viktor","not isEncrypted");

        }
        if (msg.isSigned())
        {
            builder.setSpan(new ImageSpan(check),builder.length() - 2,builder.length(),0);
            Log.d("Viktor","isSigned");

        }
        else
        {
            builder.setSpan(new ImageSpan(cross),builder.length() - 2,builder.length(),0);
            Log.d("Viktor","not isSigned");

        }

        if (msg.isVerified())
        {
            builder.setSpan(new ImageSpan(check),builder.length() - 3,builder.length(),0);
            Log.d("Viktor","isVerified");

        }
        else
        {
            Log.d("Viktor","not isVerified");
            builder.setSpan(new ImageSpan(cross),builder.length() - 3,builder.length(),0);

        }
Log.d("ENDE","************************************");
        text.setText(builder);

        return convertView;
    }
}
