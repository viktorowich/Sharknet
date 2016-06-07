package berlin.htw.schneider.viktor.sharknet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import berlin.htw.schneider.viktor.sharknet.api.Message;

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
        //TODO: sollte nur passieren wenn der Chat leer ist.
        if(msg.getContent() == null)
        {
            text.setText("Nulli");
        }
        else
        {
            //text.setText(msg.getContent());
            //text.setText("nicht Nulli aber trotzdem hier!");
        }

        return convertView;
    }

}
