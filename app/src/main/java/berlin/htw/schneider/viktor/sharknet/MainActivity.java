package berlin.htw.schneider.viktor.sharknet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import net.sharksystem.sharknet.api.ImplSharkNet;

public class MainActivity extends AppCompatActivity {

    public static ImplSharkNet implSharkNet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        implSharkNet = new ImplSharkNet();
        implSharkNet.fillWithDummyData();
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
