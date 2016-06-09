package berlin.htw.schneider.viktor.sharknet;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import berlin.htw.schneider.viktor.sharknet.api.Contact;

import java.util.List;
import java.util.Objects;

public class ConDetailView extends AppCompatActivity {

    private String con_nickname;
    private List<Contact> contacts;
    private String name,email,phone,note;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_con_detail_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_con_detail);
        setSupportActionBar(toolbar);
        this.con_nickname = getIntent().getStringExtra("CONTACT_NICKNAME");
        getSupportActionBar().setTitle(con_nickname);

        this.contacts = MainActivity.implSharkNet.getContacts();
        for(Contact contact : contacts)
        {
            if(Objects.equals(contact.getNickname(), con_nickname))
            {
                EditText nickname = (EditText)findViewById(R.id.con_nickname_edit);
                assert nickname != null;
                nickname.setText(this.con_nickname);
                this.name = "no Name";
                EditText name = (EditText)findViewById(R.id.con_name_edit);
                assert name != null;
                name.setText(this.name);
                this.email= "no email";
                EditText email = (EditText)findViewById(R.id.con_email_edit);
                assert email != null;
                email.setText(this.email);
                this.phone= "no phone";
                EditText phone = (EditText)findViewById(R.id.con_phone_edit);
                assert phone != null;
                phone.setText(this.phone);
                this.note= "no note";
                EditText note = (EditText)findViewById(R.id.con_not_edit);
                assert note != null;
                note.setText(this.note);
            }
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Snackbar.make(view, "Update Contact not imlemented", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
