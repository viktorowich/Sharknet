package berlin.htw.schneider.viktor.sharknet;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import net.sharksystem.sharknet.api.*;


import java.util.ArrayList;
import java.util.List;

public class ChatNew extends AppCompatActivity {

    private List<Contact> contacts;
    private List<Contact> selcted_contacts;

    @Override
    protected void onResume() {
        super.onResume();
        selcted_contacts = new ArrayList<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_new);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        selcted_contacts = new ArrayList<>();
        this.contacts = MainActivity.implSharkNet.getContacts();

        contacts.remove(MainActivity.implSharkNet.getMyProfile().getContact());
        ConListAdapter chatListAdapter = new ConListAdapter(this,R.layout.line_item_con,contacts);
        final ListView lv = (ListView)findViewById(R.id.con_list_view);
        if (lv != null)
        {
            lv.setAdapter(chatListAdapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                {
                    if(selcted_contacts != null)
                    {
                        if(!selcted_contacts.contains(contacts.get(position)))
                        {
                            selcted_contacts.add(contacts.get(position));
                            lv.getChildAt(position).setBackgroundColor(Color.rgb(255,64,124));
                        }
                        else
                        {
                            selcted_contacts.remove(contacts.get(position));
                            lv.getChildAt(position).setBackgroundColor(Color.WHITE);

                        }
                    }
                    else
                    {
                        selcted_contacts.add(contacts.get(position));
                        lv.getChildAt(position).setBackgroundColor(Color.rgb(255,64,124));
                    }
                }
            });
        }

        // save new Chat
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(!selcted_contacts.isEmpty())
                {
                    EditText title = (EditText) findViewById(R.id.chat_new_title);
                    net.sharksystem.sharknet.api.Chat c = MainActivity.implSharkNet.newChat(selcted_contacts);

                    assert title != null;
                    if(!title.getText().toString().trim().isEmpty())
                    {
                        c.setTitle(title.getText().toString());
                    }
                    Log.d("ChatNewID", String.valueOf(c.getID()));
                    // TODO: geht leider wegen der api noch nicht so richtig
                    c.sendMessage(new ImplContent("Chat was created by "
                            +MainActivity.implSharkNet.getMyProfile().getContact().getNickname()));
                    startActivity(new Intent( ChatNew.this, Chat.class ));
                }
                else
                {
                    //TODO: soll den user mit Snackbar angezeigt werden
                    Log.d("NewChat","kein Kontakt ausgewählt");
                }

            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
