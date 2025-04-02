package views.customer;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.cinemate.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import views.customer.fragments.CustomerAccountFragment;
import views.customer.fragments.MyBookingsFragment;
import views.customer.fragments.NearbyCinemasFragment;

public class CustomerHomePage extends AppCompatActivity {
    BottomNavigationView bottomNavCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_home_page);

        bottomNavCustomer = findViewById(R.id.bottomNavCustomer);
        loadFragment(new NearbyCinemasFragment());

        bottomNavCustomer.setOnItemSelectedListener(item -> {
            Fragment fragment = null;
            int id = item.getItemId();

            if (id == R.id.nav_nearby) {
                fragment = new NearbyCinemasFragment();
            } else if (id == R.id.nav_mybookings) {
                fragment = new MyBookingsFragment();
            } else if (id == R.id.nav_account) {
                fragment = new CustomerAccountFragment();
            }
            return loadFragment(fragment);
        });
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.customer_fragment_container, fragment).commit();
            return true;
        }
        return false;
    }
}

