package com.example.tugas1week1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import Model.Hewan
import com.example.tugas1week1.databinding.ActivityInputBinding

class input : AppCompatActivity() {
    private lateinit var viewBind: ActivityInputBinding
    private lateinit var hewan: Hewan
    var position = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind = ActivityInputBinding.inflate(layoutInflater)
        setContentView(viewBind.root)
        supportActionBar?.hide()
        getintent()
        listener()
    }
    private fun getintent(){
        position = intent.getIntExtra("position", -1)
        if(position != -1){
            val Hewan = Database.Array.listHewan[position]
            viewBind.navback.title = "Edit Hewan"
            viewBind.Save.text = "Edit"
            viewBind.Nama.editText?.setText(Hewan.nama.toString())
            viewBind.type.editText?.setText(Hewan.jenis)
            viewBind.Age.editText?.setText(Hewan.Usia.toString())
        }
    }
    private fun listener(){

        viewBind.Save.setOnClickListener{
            var Name= viewBind.Nama.editText?.text.toString().trim()
            var type = viewBind.type.editText?.text.toString().trim()
            var Usia = 0
            hewan = Hewan (Name, type, Usia)
            check()
        }

        viewBind.navback.getChildAt(1).setOnClickListener {
            finish()
        }
    }

    private fun check()
    {
        var isCompleted:Boolean = true

        if(hewan.nama!!.isEmpty()){
            viewBind.Name.error = "Hewan harus diberi nama"
            isCompleted = false
        }else{
            viewBind.Name.error = ""
        }

        if(hewan.jenis!!.isEmpty()){
            viewBind.type.error = "Type Hewan harus ada"
            isCompleted = false
        }else{
            viewBind.type.error = ""
        }

        if(viewBind.Age.editText?.text.toString().isEmpty() || viewBind.Age.editText?.text.toString().toInt() < 0)
        {
            viewBind.Age.error = "rating cannot be empty or 0"
            isCompleted = false
        }else{
            viewBind.Age.error = ""
        }

        if(isCompleted == true)
        {
            if(position == -1)
            {
                hewan.Usia = viewBind.Age.editText?.text.toString().toInt()
                Database.Array.listHewan.add(hewan)

            }else
            {
                hewan.Usia = viewBind.Age.editText?.text.toString().toInt()
                Database.Array.listHewan[position] = hewan
            }
            finish()
        }
    }
}