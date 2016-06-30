package berlin.htw.schneider.viktor.sharknet;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import net.sharksystem.sharknet.api.*;

public class ProfileDetail extends AppCompatActivity
{
    private net.sharksystem.sharknet.api.Profile profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        profile = MainActivity.implSharkNet.getMyProfile();

        EditText nickname = (EditText) findViewById(R.id.con_nickname_edit);
        nickname.setText(profile.getContact().getNickname());

        EditText name = (EditText) findViewById(R.id.con_name_edit);
        name.setText("leer");

        EditText email = (EditText) findViewById(R.id.con_email_edit);
        email.setText("leer");

        EditText phone = (EditText) findViewById(R.id.con_phone_edit);
        phone.setText("leer");

        EditText note = (EditText) findViewById(R.id.con_not_edit);
        note.setText("leer");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                ImageView imageView = (ImageView) findViewById(R.id.con_profile_image);
                EditText nickname = (EditText) findViewById(R.id.con_nickname_edit);
                EditText name = (EditText) findViewById(R.id.con_name_edit);
                EditText email = (EditText) findViewById(R.id.con_email_edit);
                EditText phone = (EditText) findViewById(R.id.con_phone_edit);
                EditText note = (EditText) findViewById(R.id.con_not_edit);

                net.sharksystem.sharknet.api.Profile myprofile =  MainActivity.implSharkNet.getMyProfile();
                assert nickname != null;
                //MainActivity.implSharkNet.newContact(nickname.getText().toString(),"234234234","public key lkajljk234234");
                Contact mycontact = MainActivity.implSharkNet.getMyProfile().getContact();
                mycontact.setNickname(nickname.getText().toString());
                //TODO: mycontact.setPicture();
                //TODO: mycontact.setUID();
                //TODO: mycontact.setPublicKey();
                myprofile.setContact(mycontact);
                finish();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }



}
