package berlin.htw.schneider.viktor.sharknet;


import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ListView;
import net.sharksystem.sharknet.api.Contact;


import java.util.List;

public class ChatNew extends AppCompatActivity {

    private List<Contact> contacts;
    private List<Contact> selcted_contacts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_new);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.contacts = MainActivity.implSharkNet.getContacts();

        ConListAdapter chatListAdapter = new ConListAdapter(this,R.layout.line_item_con,contacts);
        final ListView lv = (ListView)findViewById(R.id.con_list_view);
        if (lv != null)
        {
            lv.setAdapter(chatListAdapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                {
                    //TODO: schauen ob es schon ausgewählt wurde
                    //TODO: dann wieder rausnehmen aus der liste und den hintergrund wieder zurück
                    //selcted_contacts.add(contacts.get(position));
                    lv.getChildAt(position).setBackgroundColor(Color.rgb(255,64,124));


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


                Snackbar.make(view, "Not clear how save marked Contacts", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
