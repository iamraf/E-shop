package com.github.h01d.eshop.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.github.h01d.eshop.R;
import com.github.h01d.eshop.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity
{
    private ActivityMainBinding mDataBinding;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mDataBinding.aMainToolbar.setTitle("E-shop");
        setSupportActionBar(mDataBinding.aMainToolbar);

        navController = Navigation.findNavController(this, R.id.a_main_host);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.productsFragment, R.id.customersFragment, R.id.salesFragment).setDrawerLayout(mDataBinding.aMainDrawer).build();
        NavigationUI.setupWithNavController(mDataBinding.aMainToolbar, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(mDataBinding.aMainNavigation, navController);
    }

    @Override
    public boolean onSupportNavigateUp()
    {
        return NavigationUI.navigateUp(navController, mDataBinding.aMainDrawer) || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed()
    {
        if(mDataBinding.aMainDrawer.isDrawerOpen(GravityCompat.START))
        {
            mDataBinding.aMainDrawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }
}
