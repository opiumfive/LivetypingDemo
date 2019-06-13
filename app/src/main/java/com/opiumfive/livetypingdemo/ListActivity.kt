package com.opiumfive.livetypingdemo

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import com.iterika.marvel.api.enqueue
import com.opiumfive.livetypingdemo.api.IApi
import kotlinx.android.synthetic.main.activity_list.*
import org.koin.android.ext.android.inject

class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_filter, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.onNavDestinationSelected(findNavController(R.id.hostFragment)) == true) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}