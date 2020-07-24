package com.gibsoncodes.movieapp.ui.main

import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.Window
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.gibsoncodes.movieapp.R
import com.gibsoncodes.movieapp.base.BaseDataBindingActivity
import com.gibsoncodes.movieapp.common.NetworkChecker.checkNetwork
import com.gibsoncodes.movieapp.databinding.ActivityMainScreenBinding
import com.gibsoncodes.movieapp.extensions.showSnack
import com.gibsoncodes.movieapp.ui.main.fragments.BottomNavigationDrawerFragment
import com.gibsoncodes.movieapp.ui.main.fragments.MainFragment
import com.gibsoncodes.movieapp.ui.main.fragments.MoviesFragment
import com.gibsoncodes.movieapp.ui.main.fragments.ShowsFragment
import com.skydoves.whatif.whatIfNotNull
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainScreen : BaseDataBindingActivity(),
    BottomNavigationDrawerFragment.OnCategorySelectedListener {
    private val fm = supportFragmentManager
    private val mainFragment = MainFragment()
    private val moviesFragment = MoviesFragment()
    private val showsFragment = ShowsFragment()
    private var activeFragment: Fragment = mainFragment
    private var isConnected: Boolean? = null
    private val binding: ActivityMainScreenBinding by binding(R.layout.activity_main_screen)

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        window.sharedElementsUseOverlay = false
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
        super.onCreate(savedInstanceState)
        binding.apply {
            lifecycleOwner = this@MainScreen
            fab = mainScreenFab
        }


        fm.beginTransaction().add(R.id.mainContainerView, moviesFragment, "Movies fragment")
            .hide(moviesFragment).commit()
        fm.beginTransaction().add(R.id.mainContainerView, showsFragment, "Shows fragment")
            .hide(showsFragment).commit()
        fm.beginTransaction()
            .add(R.id.mainContainerView, mainFragment, "Main Fragment Tag")
            .commit()
        setSupportActionBar(binding.bottomAppBar)

        isConnected = checkNetwork()
        isConnected.whatIfNotNull {
            networkChecker(it)
        }

    }

    private fun networkChecker(connected: Boolean) {
        if (!connected) showSnack(
            this@MainScreen,
            binding.cordMainScreen,
            binding.mainScreenFab,
            "No internet connection please try again"
        )
        else showSnack(this@MainScreen, binding.cordMainScreen, binding.mainScreenFab, "Connected")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.home_nav_bar -> {
                val bottomSheetDialogFragment =
                    BottomNavigationDrawerFragment()
                bottomSheetDialogFragment.show(
                    supportFragmentManager,
                    BottomNavigationDrawerFragment.bottomNavigationDrawerFragmentTag
                )
                true
            }
            else -> false
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.movies_main_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onCategorySelected(categoryName: String) {
       when(categoryName){
           BottomNavigationDrawerFragment.mainFragment->{
               fm.beginTransaction()
                   .hide(activeFragment)
                   .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                   .show(mainFragment)
                   .commit()

               activeFragment= mainFragment
           }
           BottomNavigationDrawerFragment.moviesFragment->{
               fm.beginTransaction()
                   .hide(activeFragment)
                   .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                   .show(moviesFragment)
                   .commit()

               activeFragment= moviesFragment
           }
           BottomNavigationDrawerFragment.showsFragment->{
               fm.beginTransaction()
                   .hide(activeFragment)
                   .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                   .show(showsFragment)
                   .commit()

               activeFragment=showsFragment
           }
       }
    }

    override fun onBackPressed() {
        if(mainFragment.isHidden){
            fm.beginTransaction().hide(activeFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .show(mainFragment)
                .commit()
            activeFragment=mainFragment
        }else super.onBackPressed()
    }
}