package com.quaksire.weatherapp

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView

class CitiesFragment : Fragment() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    private lateinit var layoutView: View
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var sideNavView: NavigationView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        layoutView = inflater.inflate(R.layout.fragment_cities, container, false)

        // Get all views
        drawerLayout = layoutView.findViewById(R.id.drawer)
        sideNavView = layoutView.findViewById(R.id.navigationView)
        val toolbar = layoutView.findViewById<Toolbar>(R.id.toolbar)

        setHasOptionsMenu(true)
        setDrawableActionBar(toolbar)
        return layoutView
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.cities_drawer, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> drawerLayout.openDrawer(GravityCompat.START)
        }
        return item.onNavDestinationSelected((activity as MainActivity).navController)
                || super.onOptionsItemSelected(item)
    }

    private fun setDrawableActionBar(toolbar: Toolbar) {
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        val host: NavHostFragment = activity!!.supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = host.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupNavigation(navController)
        setupActionBar(navController, appBarConfiguration)
    }

    private fun setupActionBar(navController: NavController, appBarConfig: AppBarConfiguration) {
        setupActionBarWithNavController(activity as AppCompatActivity, navController, appBarConfig)
    }

    private fun setupNavigation(navController: NavController) {
        sideNavView.setupWithNavController(navController)

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.citiesFragment, R.id.settingsFragment),
            drawerLayout
        )
    }
}
