package com.alas.mutec;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpNavigation();


       /* final BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home_menu:
                        Toast.makeText(MainActivity.this, "Estas en en menu principal", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.categoria_menu:
                        Toast.makeText(MainActivity.this, "Estas en Categorias", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.usuario_menu:
                        Intent i = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(i);
                        break;
                    case R.id.mensaje_menu:
                        Toast.makeText(MainActivity.this, "Estas en los mensajes", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.buscar_menu:
                        Toast.makeText(MainActivity.this, "Estas en la busqueda", Toast.LENGTH_SHORT).show();
                        break;

                }
                return true;
            }
        });*/
    }

    public void setUpNavigation(){
        bottomNavigationView =findViewById(R.id.bttm_nav);
        NavHostFragment navHostFragment = (NavHostFragment)getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(bottomNavigationView,
                navHostFragment.getNavController());
    }


}
