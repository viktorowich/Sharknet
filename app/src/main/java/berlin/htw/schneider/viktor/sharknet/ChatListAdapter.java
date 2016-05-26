package berlin.htw.schneider.viktor.sharknet;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import berlin.htw.schneider.viktor.sharknet.api.Chat;
import berlin.htw.schneider.viktor.sharknet.api.Contact;
import berlin.htw.schneider.viktor.sharknet.api.Message;



import java.util.List;

public class ChatListAdapter extends ArrayAdapter<berlin.htw.schneider.viktor.sharknet.api.Chat>
{
    private List<Chat> chats;

    public ChatListAdapter(Context context, int resource, List<Chat> objects)
    {
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

        Chat chat = chats.get(position);
        TextView title = (TextView) convertView.findViewById(R.id.name);

        //TODO: warten auf dei API für Chat.getTitle()
        //name.setText(chat.getTitle());

        TextView text = (TextView) convertView.findViewById(R.id.msg_text);

        List<Message> msg       = chat.getMessages();
        Contact contact         = msg.get(msg.size() - 1).getSender();
        String contact_name     = contact.getNickname();
        String chat_description = contact_name+msg.get(msg.size()-1).getContent();
        text.setText(chat_description);

        return convertView;
    }
}
