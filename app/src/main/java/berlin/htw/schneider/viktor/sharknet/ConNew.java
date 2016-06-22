package berlin.htw.schneider.viktor.sharknet;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import net.sharksystem.sharknet.api.Contact;
import net.sharksystem.sharknet.api.ImplContact;

public class ConNew extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_con_new);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //TODO: image muss zu ImageButton geändert werden
                // damit beim click menu mit der auswahl der fotos kommt
                ImageView imageView = (ImageView) findViewById(R.id.con_profile_image);
                EditText nickname = (EditText) findViewById(R.id.con_nickname_edit);
                EditText name = (EditText) findViewById(R.id.con_name_edit);
                EditText email = (EditText) findViewById(R.id.con_email_edit);
                EditText phone = (EditText) findViewById(R.id.con_phone_edit);
                EditText note = (EditText) findViewById(R.id.con_not_edit);

                assert nickname != null;
                MainActivity.implSharkNet.newContact(nickname.getText().toString(),"234234234","public key lkajljk234234");
                finish();

                //MainActivity.implSharkNet.newContact(nickname.getText().toString(),)


            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
