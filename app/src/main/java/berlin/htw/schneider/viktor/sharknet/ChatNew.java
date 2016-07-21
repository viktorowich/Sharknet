package berlin.htw.schneider.viktor.sharknet;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import net.sharksystem.sharknet.api.*;


import java.util.ArrayList;
import java.util.List;

public class ChatNew extends AppCompatActivity {

    private List<Contact> contacts;
    private List<Contact> selcted_contacts;
    ConListAdapterNewChat conListAdapter;

    @Override
    protected void onResume() {
        super.onResume();
        selcted_contacts = new ArrayList<Contact>();
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.new_chat_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {
            case R.id.save:
                selcted_contacts = conListAdapter.getSelectedContacts();
                Log.d("WICHTIG ", String.valueOf(selcted_contacts.size()));
                if(selcted_contacts != null)
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
                            +MainActivity.implSharkNet.getMyProfile().getContact().getNickname(),MainActivity.implSharkNet.getMyProfile()));
                    startActivity(new Intent( ChatNew.this, Chat.class ));
                }
                else
                {
                    //TODO: soll den user mit Snackbar angezeigt werden
                    Log.d("NewChat","kein Kontakt ausgewählt");
                    //TODO: Snackbar
                }

                return true;



            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_new);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        selcted_contacts = new ArrayList<>();
        this.contacts = MainActivity.implSharkNet.getContacts();
        // TODO: nimmt Timo noch raus den einen Contact
        contacts.remove(MainActivity.implSharkNet.getMyProfile().getContact());
        conListAdapter = new ConListAdapterNewChat(contacts);
        RecyclerView lv = (RecyclerView)findViewById(R.id.con_list_view);
        if (lv != null)
        {
            LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
            lv.setLayoutManager(llm);
            lv.setItemAnimator(new DefaultItemAnimator());
            lv.setAdapter(conListAdapter);

        }

        // save new Chat
        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
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
        });*/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void setContact(View view)
    {

    }
}
