package com.mk8.screen.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mk8.screen.R
import kotlinx.android.synthetic.main.toolbar_view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.base_layout)
    }

    fun setToolbarTitle(name: String?) {
        toolbar_title.text = name
    }
}
