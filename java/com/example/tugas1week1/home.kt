package com.example.tugas1week1


import Adapter.ListdataRVadapter
import Interface.cardListener
import android.content.Intent
import  androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tugas1week1.databinding.ActivityHomeBinding

class home : AppCompatActivity(), cardListener{
    private lateinit var viewBind:ActivityHomeBinding
    private val adapter = ListdataRVadapter(Database.Array.listHewan, this)
    private var total: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(viewBind.root)
        supportActionBar?.hide()
        setupRecyclerView()
        listener()
    }
    override fun onResume() {
        super.onResume()
        total = Database.Array.listHewan.size
        if(total == 0)
        {
            viewBind.textaddhewan.alpha = 1f
        }else
        {
            viewBind.textaddhewan.alpha = 0f
        }
        adapter.notifyDataSetChanged()
    }

    private fun listener(){
        viewBind.floatingActionButton2.setOnClickListener {
            val myIntent = Intent(this, input::class.java)
            startActivity(myIntent)
        }


    }

    private fun setupRecyclerView(){
        val layoutManager = GridLayoutManager(this,2)
        viewBind.recyclerView.layoutManager = layoutManager   // Set layout
        viewBind.recyclerView.adapter = adapter   // Set adapter
    }

    override fun onCardClick(position: Int) {
        val myIntent = Intent(this, home::class.java).apply {
            putExtra("position", position)
        }
        startActivity(myIntent)
    }

}