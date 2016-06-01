package berlin.htw.schneider.viktor.sharknet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import berlin.htw.schneider.viktor.sharknet.api.ImplSharkNet;

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

        startActivity(inbox);
    }
}
