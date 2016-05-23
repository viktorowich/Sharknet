package berlin.htw.schneider.viktor.sharknet;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;



import java.util.LinkedList;

public class ChatListAdapter extends ArrayAdapter<Chat>
{
    private LinkedList<Chat> chats;

    public ChatListAdapter(Context context, int resource, LinkedList<Chat> objects) {
        super(context, resource, objects);
        chats = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.line_item_chat,parent,false);
        }

        //Chat chat = chats.get(position);
        TextView name = (TextView) convertView.findViewById(R.id.name);
        //name.setText(chat.getMes);

        return super.getView(position, convertView, parent);
    }
}
