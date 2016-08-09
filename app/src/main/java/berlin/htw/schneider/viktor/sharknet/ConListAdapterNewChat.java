package berlin.htw.schneider.viktor.sharknet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import net.sharksystem.sharknet.api.Contact;

import java.util.List;

/**
 * Created by viktorowich on 08/06/16.
 */
public class ConListAdapterNewChat extends ArrayAdapter<Contact>
{

    private List<Contact> contacts;

    public ConListAdapterNewChat(Context context, int resource, List<Contact> objects)
    {
        super(context, resource, objects);
        this.contacts = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.line_item_con_new_chat,parent,false);
        }

        Contact contact = contacts.get(position);

        //Name
        TextView title = (TextView) convertView.findViewById(R.id.contact_nickname);
        title.setText(contact.getNickname());

        //key
        TextView name = (TextView) convertView.findViewById(R.id.contact_name);
        name.setText(contact.getPublicKey());


        //Image
        ImageView image = (ImageView) convertView.findViewById(R.id.contact_image);
        image.setImageResource(R.drawable.ic_person_pink_700_48dp);
        //TODO: != change to ==  then load image works
        /*if(contact.getPicture() != null)
        {
            image.setImageResource(R.drawable.ic_person_black_24dp);
        }
        else
        {
            //TODO: image.setImageResource(contact.getPicture().);
        }*/

        return convertView;
    }
}
