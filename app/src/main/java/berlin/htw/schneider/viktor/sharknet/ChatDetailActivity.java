package berlin.htw.schneider.viktor.sharknet;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import net.sharksystem.sharknet.api.ImplContent;
import net.sharksystem.sharknet.api.Message;

import java.util.List;


public class ChatDetailActivity extends AppCompatActivity {

    private net.sharksystem.sharknet.api.Chat chat ;
    private MsgListAdapter msgListAdapter;
    private int chatID ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_detail);

       this.chatID = getIntent().getIntExtra(Chat.CHAT_ID,0);

        List<Message> msgs = null;
        List<net.sharksystem.sharknet.api.Chat> chats =  MainActivity.implSharkNet.getChats();

        Toolbar t = (Toolbar) findViewById(R.id.toolbar_chatdetail);
        setSupportActionBar(t);

        for(net.sharksystem.sharknet.api.Chat chat : chats)
        {
            if(chat.getID() == chatID)
            {
                msgs = chat.getMessages();
                this.chat = chat;
                getSupportActionBar().setTitle(this.chat.getTitle());
            }
        }

        this.msgListAdapter = new MsgListAdapter(this,R.layout.line_item_msg,msgs);
        ListView lv = (ListView)findViewById(R.id.msg_list_view);
        if (lv != null)
        {
            lv.setAdapter(msgListAdapter);
        }

        EditText msg_text = (EditText) findViewById(R.id.write_msg_edit_text);
        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/RockSalt.TTF");
        Button b = (Button) findViewById(R.id.send_button);
        assert b != null;
        b.setTypeface(type);
        assert msg_text != null;
        msg_text.setTypeface(type);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    public void sendMessage(View view)
    {
        EditText msg_text = (EditText) findViewById(R.id.write_msg_edit_text);
        String msg_string = msg_text.getText().toString();
        if(msg_string.isEmpty())
        {
            msg_string = "No message entered!";
            Snackbar.make(view, msg_string, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        else
        {
            chat.sendMessage(new ImplContent(msg_string));
            this.msgListAdapter.notifyDataSetChanged();
            msg_text.getText().clear();

            List<Message> msgs = null;
            List<net.sharksystem.sharknet.api.Chat> chats =  MainActivity.implSharkNet.getChats();

            //TODO: not the best way // would be better to use getChatbyID()
            for(net.sharksystem.sharknet.api.Chat chat : chats)
            {
                if(chat.getID() == this.chatID)
                {
                    msgs = chat.getMessages();

                }
            }
            this.msgListAdapter = new MsgListAdapter(this,R.layout.line_item_msg,msgs);
            ListView lv = (ListView)findViewById(R.id.msg_list_view);
            if (lv != null)
            {
                lv.setAdapter(msgListAdapter);
            }
            this.chat.update();
        }


    }
}
