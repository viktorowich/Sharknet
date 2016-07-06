package berlin.htw.schneider.viktor.sharknet;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import net.sharkfw.knowledgeBase.SemanticTag;
import net.sharkfw.knowledgeBase.TXSemanticTag;
import net.sharksystem.sharknet.api.Interest;

import java.util.List;

public class ProfileDetailInterests extends AppCompatActivity
{
    private ExpandableListView expandableListView;
    private List<Interest> interestList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_detail_interests);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        expandableListView = (ExpandableListView) findViewById(R.id.InterestsExpandableListView);
        InterestsListAdapter interestsListAdapter = new InterestsListAdapter(this,
                MainActivity.implSharkNet.getMyProfile().getContact().getInterests().getAllTopics());
        expandableListView.setAdapter(interestsListAdapter);

        //Log.d("SIZE", String.valueOf(MainActivity.implSharkNet.getMyProfile().getContact().getInterests().size()));
        /*for (TXSemanticTag txSemanticTag : MainActivity.implSharkNet.getMyProfile().getContact().getInterests().getAllTopics())
        {
            Log.d("INTERESTS",txSemanticTag.getName());
        }
*/



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MainActivity.implSharkNet.getMyProfile().getContact();
                Snackbar.make(view, "Noch nicht implementiert", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
