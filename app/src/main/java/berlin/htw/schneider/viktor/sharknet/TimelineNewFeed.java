package berlin.htw.schneider.viktor.sharknet;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import net.sharksystem.sharknet.api.ImplInterest;
import net.sharksystem.sharknet.api.ImplContent;

public class TimelineNewFeed extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline_detail_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                EditText message = (EditText) findViewById(R.id.feed_new_message);
                String text = message.getText().toString();
                //TODO:
                /*MainActivity.implSharkNet.newFeed(new ImplContent(text),
                        new ImplInterest("Sport",null,null,null),
                        MainActivity.implSharkNet.getMyProfile().getContact());
                        */
                finish();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
