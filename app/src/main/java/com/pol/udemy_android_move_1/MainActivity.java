package com.pol.udemy_android_move_1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.pol.udemy_android_move_1.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    boolean dayMode = true;

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        // Carga del fragmaneto por defecto
        getSupportFragmentManager()
                .beginTransaction().add(R.id.contenedor, new SecondFragment()).commit();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment f = null;
                if(dayMode){
                    f = new SecondFragment();
                } else {
                    f = new FirstFragment();
                }
                dayMode = !dayMode;

                getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, f). commit();
            }
        });
    }
    
    
    //Override ciclos vida:


    @Override
    protected void onStart() {
        super.onStart();
        Log.i("LOGCICLO", "Starting onStart method");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("LOGCICLO", "Starting onResume method");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("LOGCICLO", "Starting onPause method");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("LOGCICLO", "Starting onStop method");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("LOGCICLO", "Starting onDestroy method");
    }
    
    //Metodo CheckBox
    
    public void ClickOnConditions(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.ConditionscheckBox:
                if (checked) {
                    Toast.makeText(this, "You accepts Conditions", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "You need to accepts de conditions", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    //Metodo RadioButton
    public void SelectGender(View view){
        boolean checked = ((RadioButton) view).isChecked();
        
        //Check witch radio button was selected
        switch (view.getId()){
            case R.id.MaleButton:
                if(checked)
                    Toast.makeText(this, "Selected Male", Toast.LENGTH_SHORT).show();
                break;
            case R.id.FemaleButton:
                if(checked)
                    Toast.makeText(this, "Selected Female", Toast.LENGTH_SHORT).show();
        }       
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}