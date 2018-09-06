package com.prueba.julianlopez.appintergrupo.Login.Prospects.View

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.prueba.julianlopez.appintergrupo.Login.LogActivity
import com.prueba.julianlopez.appintergrupo.Login.Login.View.LoginActivity
import com.prueba.julianlopez.appintergrupo.Login.Prospects.Presenter.Prospects
import com.prueba.julianlopez.appintergrupo.Login.Prospects.ProspectsMVP
import com.prueba.julianlopez.appintergrupo.Login.RoomDatabase.Entitys.LogEntity
import com.prueba.julianlopez.appintergrupo.Login.RoomDatabase.Entitys.ProspectEntity
import com.prueba.julianlopez.appintergrupo.Login.Util.DataCache
import com.prueba.julianlopez.appintergrupo.R
import kotlinx.android.synthetic.main.activity_dash_board.*
import kotlinx.android.synthetic.main.app_bar_dash_board.*
import kotlinx.android.synthetic.main.content_dash_board.*

class DashBoardActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, ProspectsMVP.View{

    var presenter:ProspectsMVP.Presenter = Prospects(this)
    val ACCESS_TOKEN = "accesToken"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)
        setTitle("DashBoard y Prospectos")
        setSupportActionBar(toolbar)

        presenter.Prospects(DataCache.readData(this,ACCESS_TOKEN),this)


        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun loadProspects(prospectsResponse: List<ProspectEntity>) {




        val layoutManager: RecyclerView.LayoutManager? = LinearLayoutManager(this)
//

        prospectRecycler.layoutManager = layoutManager
        prospectRecycler.setHasFixedSize(true)
        val ServicesAdapter = ListProspectAdapter(this,prospectsResponse)
        prospectRecycler.adapter = ServicesAdapter

    }

    override fun notificationError(error: String) {

        Toast.makeText(this,error,Toast.LENGTH_LONG).show()
        Log.e("error",error )

    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.dash_board, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_list_prospect -> {
                // Handle the camera action
            }
            R.id.nav_list_logs -> {
                val intent = Intent(this,LogActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_close_acount -> {

                val intent = Intent(this,LoginActivity::class.java)
                startActivity(intent)
                finish()

                DataCache.writeData(this, "EMAIL", "")
                DataCache.writeData(this, "PASSWORD", "")
                DataCache.writeData(this, "REMENBER_CREDENTIALS", "false")
                DataCache.writeData(this, "accesToken", "")
            }

        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
