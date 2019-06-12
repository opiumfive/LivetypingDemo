package com.opiumfive.livetypingdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.iterika.marvel.api.enqueue
import com.opiumfive.livetypingdemo.api.IApi
import org.koin.android.ext.android.inject

class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
    }
}