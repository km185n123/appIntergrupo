package com.prueba.julianlopez.appintergrupo.Login

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.prueba.julianlopez.appintergrupo.Login.Prospects.View.LogProspectAdapter
import com.prueba.julianlopez.appintergrupo.Login.RoomDatabase.AppDatabase
import com.prueba.julianlopez.appintergrupo.R
import kotlinx.android.synthetic.main.content_dash_board.*

class LogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_dash_board)
        setTitle("Log")


        val db: AppDatabase = AppDatabase.getAppDatabase(this)
        var  listLogs = db.LogDao().getAll()

        val layoutManager: RecyclerView.LayoutManager? = LinearLayoutManager(this)
//

        prospectRecycler.layoutManager = layoutManager
        prospectRecycler.setHasFixedSize(true)
        val ServicesAdapter = LogProspectAdapter(this,listLogs  )
        prospectRecycler.adapter = ServicesAdapter
    }
}
