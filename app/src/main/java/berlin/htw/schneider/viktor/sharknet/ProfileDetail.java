package berlin.htw.schneider.viktor.sharknet;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import net.sharksystem.sharknet.api.*;

import java.io.IOException;

public class ProfileDetail extends AppCompatActivity
{
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.profile_detail_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {
            case R.id.profile_save:
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

                return true;



            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

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
/*
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
*/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    public void loadImage(View view)
    {
         int PICK_IMAGE_REQUEST = 1;

        Intent intent = new Intent();
// Show only images, no videos or anything else
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
// Always show the chooser (if there are multiple options available)
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        int PICK_IMAGE_REQUEST =1;
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));

                ImageView imageView = (ImageView) findViewById(R.id.con_profile_image);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
