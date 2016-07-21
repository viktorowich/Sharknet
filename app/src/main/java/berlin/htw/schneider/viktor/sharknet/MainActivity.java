package berlin.htw.schneider.viktor.sharknet;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import net.sharkfw.knowledgeBase.TXSemanticTag;
import net.sharksystem.sharknet.api.*;
import net.sharksystem.sharknet.api.Profile;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static ImplSharkNet implSharkNet;
    private List<Profile> profiles = null;
    int index ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        implSharkNet = new ImplSharkNet();
        implSharkNet.fillWithDummyData();
        index = 0;
        this.profiles = implSharkNet.getProfiles();

        EditText userid = (EditText) findViewById(R.id.userid);
        assert userid != null;
        userid.setText(this.profiles.get(index).getContact().getNickname());
        String[][] dummyInterests = {
                {"Sport", "https://de.wikipedia.org/wiki/Sport"},
                {"Musik", "https://de.wikipedia.org/wiki/Musik"},
                {"Literatur", "https://de.wikipedia.org/wiki/Literatur"},
        };

        String[][] sportsTopics = {
                {"Fußball", "https://de.wikipedia.org/wiki/Fußball"},
                {"Handball", "https://de.wikipedia.org/wiki/Handball"},
                {"Turmspringen", "https://de.wikipedia.org/wiki/Turmspringen"},
        };

        for (int i = 0; i < dummyInterests.length; i++) {
            TXSemanticTag parentTag = implSharkNet.getMyProfile().getContact().getInterests().addInterest(dummyInterests[i][0], dummyInterests[i][0]);
            if (i == 0) {
                for(String[] child : sportsTopics) {
                    implSharkNet.getMyProfile().getContact().getInterests().addInterest(child[0], child[1]).move(parentTag);
                }
            }
        }


    }

    public void openChat(View view)
    {
        Intent inbox = new Intent(this, Inbox.class);

        implSharkNet.setProfile(this.profiles.get(index),"");

        /*

        Typeface face= Typeface.createFromAsset(getAssets(),"fonts/RockSalt.ttf");
        NavigationView nv = (NavigationView) findViewById(R.id.nav_view);
        Menu m = nv.getMenu();
        txtV.setTypeface(face);
*/
        startActivity(inbox);
    }

    public void backProfile(View view)
    {
        if(index != 0)
        {
            index--;
            EditText userid = (EditText) findViewById(R.id.userid);
            assert userid != null;
            userid.setText(this.profiles.get(index).getContact().getNickname());
            Toast.makeText(this,"back",Toast.LENGTH_SHORT).show();
        }
    }


    public void nextProfile(View view)
    {
        if(index != this.profiles.size()-1)
        {
            index++;
            Toast.makeText(this,"next",Toast.LENGTH_SHORT).show();
            EditText userid = (EditText) findViewById(R.id.userid);
            assert userid != null;
            userid.setText(this.profiles.get(index).getContact().getNickname());
        }
    }
}
