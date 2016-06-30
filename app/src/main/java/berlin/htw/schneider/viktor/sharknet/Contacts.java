package berlin.htw.schneider.viktor.sharknet;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import net.sharksystem.sharknet.api.Contact;

import java.util.List;

public class Contacts extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
{
    private static final String CONTACT_NICKNAME = "CONTACT_NICKNAME";
    private List<Contact> contacts;

    @Override
    protected void onResume()
    {
        super.onResume();
        contacts = MainActivity.implSharkNet.getContacts();

        ConListAdapter conListAdapter = new ConListAdapter(this,R.layout.line_item_con,contacts);
        ListView lv = (ListView)findViewById(R.id.con_list_view);
        if (lv != null)
        {
            lv.setAdapter(conListAdapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                {
                    Intent intent = new Intent(Contacts.this,ConDetailView.class);
                    intent.putExtra(CONTACT_NICKNAME,contacts.get(position).getNickname());
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(Contacts.this,ConNew.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        contacts = MainActivity.implSharkNet.getContacts();

        ConListAdapter conListAdapter = new ConListAdapter(this,R.layout.line_item_con,contacts);
        ListView lv = (ListView)findViewById(R.id.con_list_view);
        if (lv != null)
        {
            lv.setAdapter(conListAdapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                {
                    Intent intent = new Intent(Contacts.this,ConDetailView.class);
                    intent.putExtra(CONTACT_NICKNAME,contacts.get(position).getNickname());
                    startActivity(intent);
                }
            });
        }


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.contacts, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id)
        {
            case R.id.chat:
                startActivity(new Intent(this, Chat.class));
                return true;
            case R.id.timeline:
                startActivity(new Intent(this, Timeline.class));
                return true;
            case R.id.contact:
                startActivity(new Intent(this, Contacts.class));
                return true;
            case R.id.profile:
                startActivity(new Intent(this, Profile.class));
                return true;
            case R.id.inbox:
                startActivity(new Intent(this, Inbox.class));
                return true;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
