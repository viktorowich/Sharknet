package berlin.htw.schneider.viktor.sharknet;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
public class ConListAdapter extends RecyclerView.Adapter<ConListAdapter.MyViewHolder>
{

    private List<Contact> contacts;

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        public TextView name,nickname;
        ImageView image;
        public MyViewHolder(View itemView)
        {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.contact_image);
            name = (TextView) itemView.findViewById(R.id.contact_name);
            nickname = (TextView) itemView.findViewById(R.id.contact_nickname);
        }
    }

    public ConListAdapter(List<Contact> objects)
    {
        this.contacts = objects;
    }


    /*@Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.line_item_con,parent,false);
        }

        Contact contact = contacts.get(position);

        Typeface type = Typeface.createFromAsset(getContext().getAssets(),"fonts/RockSalt.TTF");
        //Name
        TextView title = (TextView) convertView.findViewById(R.id.contact_nickname);
        title.setTypeface(type);
        title.setText(contact.getNickname());

        //key
        TextView name = (TextView) convertView.findViewById(R.id.contact_name);
        name.setTypeface(type);
        name.setText(contact.getPublicKey());


        //Image
        ImageView image = (ImageView) convertView.findViewById(R.id.contact_image);
        image.setImageResource(R.drawable.ic_person_black_24dp);
        //TODO: != change to ==  then load image works
        /*if(contact.getPicture() != null)
        {
            image.setImageResource(R.drawable.ic_person_black_24dp);
        }
        else
        {
            //TODO: image.setImageResource(contact.getPicture().);
        }

        return convertView;
    }*/

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.line_item_con,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position)
    {
        Contact contact = contacts.get(position);
        holder.name.setText(contact.getNickname());
        holder.nickname.setText(contact.getPublicKey());
        holder.image.setImageResource(R.drawable.ic_person_pink_600_24dp);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //v.getContext().startActivity(new Intent(v.getContext(),ChatDetailActivity.class));
                Intent intent = new Intent(v.getContext(),ConDetailView.class);
                //context.startActivity(new Intent(context.this, ChatDetailActivity.class));
                intent.putExtra(Contacts.CONTACT_NICKNAME,contacts.get(holder.getAdapterPosition()).getNickname());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return contacts.size();
    }
}
