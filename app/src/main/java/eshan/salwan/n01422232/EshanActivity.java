//Eshan Salwan, N01422232, RNB
package eshan.salwan.n01422232;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class EshanActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    //FOR DESIGN
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Fragment eshan_home;
    private Fragment salwan_download;
    private Fragment n01422232_weather;
    private Fragment eshan_file;
    private Fragment settings_screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 6 - Configure all views
        configureToolBar();
        configureDrawerLayout();
        configureNavigationView();

        if (this.eshan_home == null) this.eshan_home = EshanHome.newInstance();
        startTransactionFragment(this.eshan_home);
    }

    @Override
    public void onBackPressed() {
        // 5 - Handle back click to close menu
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            new AlertDialog.Builder(this)
                    .setTitle("Eshan Salwan, N01422232")
                    .setIcon(R.drawable.yellow_tri)
                    .setMessage("Are you sure you want to exit?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            finish();
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
        }
    }

    private void configureNavigationView() {
        this.navigationView = (NavigationView) findViewById(R.id.eshan_main_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void configureDrawerLayout() {
        this.drawerLayout = (DrawerLayout) findViewById(R.id.eshan_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close){
            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                Snackbar snackbar = Snackbar.make(drawerView, R.string.snackbar, Snackbar.LENGTH_SHORT);
                snackbar.show();
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                Toast toast = Toast.makeText(getApplicationContext(), R.string.toast, Toast.LENGTH_SHORT);
                toast.show();
            }
        };
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void configureToolBar() {
        this.toolbar = (Toolbar) findViewById(R.id.eshan_main_toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.home:
                if (this.eshan_home == null) this.eshan_home = EshanHome.newInstance();
                startTransactionFragment(this.eshan_home);
                break;
            case R.id.download:
                if (this.salwan_download == null)
                    this.salwan_download = SalwanDownload.newInstance();
                startTransactionFragment(this.salwan_download);
                break;
            case R.id.weather:
                if (this.n01422232_weather == null)
                    this.n01422232_weather = N01422232Weather.newInstance();
                startTransactionFragment(this.n01422232_weather);
                break;
            case R.id.fileContent:
                if (this.eshan_file == null) this.eshan_file = EshanFileContent.newInstance();
                startTransactionFragment(this.eshan_file);
                break;
            case R.id.settings:
                if (this.settings_screen == null)
                    this.settings_screen = SettingsScreen.newInstance();
                startTransactionFragment(this.settings_screen);
                break;
            default:
                break;
        }
        this.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void startTransactionFragment(Fragment fragment) {
        if (!fragment.isVisible()) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.eshan_main_frame_layout, fragment).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.eshanHelp:
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://humber.ca/"));
                startActivity(i);
                break;
            case R.id.eshanSettings:
                startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
                break;
            case R.id.eshanHome:
                if (this.eshan_home == null) this.eshan_home = EshanHome.newInstance();
                startTransactionFragment(this.eshan_home);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}