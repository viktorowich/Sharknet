package berlin.htw.schneider.viktor.sharknet;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import berlin.htw.schneider.viktor.sharknet.api.Chat;
import berlin.htw.schneider.viktor.sharknet.api.Contact;
import berlin.htw.schneider.viktor.sharknet.api.Message;



import java.util.List;

/**
 * Reads the Information from the Chat-List and
 * fills the List-Items-Layout.
 */
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
        title.setText(chat.getTitle());

        TextView text = (TextView) convertView.findViewById(R.id.msg_text);
        List<Message> msgs      = chat.getMessages();
        Message last_msg        = msgs.get(msgs.size()-1);
        String content          = last_msg.getContent();
        String sender = "Nulli";

        // TODO: Nickname vom Sender lässt sich noch nicht abrufen
        /*if(last_msg.getSender().getNickname() != null)
        {
            sender = last_msg.getSender().getNickname();
        }*/

        String last_msg_content = sender+":"+content;
        text.setText(last_msg_content);

        ImageView image = (ImageView) convertView.findViewById(R.id.chat_image);

        if(chat.getPicture() != null)
        {
            // TODO: setPicture
        }


        return convertView;
    }
}
