package berlin.htw.schneider.viktor.sharknet;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import net.sharkfw.knowledgeBase.TXSemanticTag;
import net.sharksystem.sharknet.api.ImplSharkNet;

public class MainActivity extends AppCompatActivity {

    public static ImplSharkNet implSharkNet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        implSharkNet = new ImplSharkNet();
        implSharkNet.fillWithDummyData();

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
/*
        Typeface face= Typeface.createFromAsset(getAssets(),"fonts/RockSalt.ttf");
        NavigationView nv = (NavigationView) findViewById(R.id.nav_view);
        Menu m = nv.getMenu();




        txtV.setTypeface(face);
*/








        startActivity(inbox);
    }

}
