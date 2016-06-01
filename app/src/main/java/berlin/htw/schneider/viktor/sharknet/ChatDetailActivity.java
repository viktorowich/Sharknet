package berlin.htw.schneider.viktor.sharknet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import berlin.htw.schneider.viktor.sharknet.api.Message;


import java.util.List;
import java.util.Objects;

public class ChatDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String chatTitle = getIntent().getStringExtra(Chat.CHAT_TITLE);

        List<Message> msgs = null;
        List<berlin.htw.schneider.viktor.sharknet.api.Chat> chats =  MainActivity.implSharkNet.getChats();

        for(berlin.htw.schneider.viktor.sharknet.api.Chat chat : chats)
        {
            if(Objects.equals(chat.getTitle(), chatTitle))
            {
                msgs = chat.getMessages();
            }
        }

        //TODO: muss später nach der ID gehen und nicht nach den Titel

        MsgListAdapter msgListAdapter = new MsgListAdapter(this,R.layout.line_item_msg,msgs);
        ListView lv = (ListView)findViewById(R.id.msg_list_view);
        if (lv != null)
        {
            lv.setAdapter(msgListAdapter);
            /*lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                {
                    Intent intent = new Intent(Chat.this,ChatDetailActivity.class);

                    intent.putExtra(CHAT_TITLE,chats.get(position).getTitle());
                    startActivity(intent);
                }
            });
            */
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
