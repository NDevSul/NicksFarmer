package Adapter

import Interface.cardListener
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import Model.Hewan
import com.example.tugas1week1.R
import com.example.tugas1week1.databinding.KartuhewansBinding

class ListdataRVadapter (val listHewan : ArrayList<Hewan>, val cardListener: cardListener) :
    RecyclerView.Adapter<ListdataRVadapter.viewHolder>() {



    class viewHolder(val itemView: View, val cardListener: cardListener) :
        RecyclerView.ViewHolder(itemView) {

        val binding = KartuhewansBinding.bind(itemView)

        fun setData(data: Hewan) {
                binding.textView2.text = data.nama
            if (data.imageUri.isNotEmpty()) {
                binding.imageView.setImageURI(Uri.parse(data.imageUri))
            }
            itemView.setOnClickListener {
                cardListener.onCardClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.kartuhewans, parent, false)
        return viewHolder(view, cardListener)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.setData(listHewan[position])
    }

    override fun getItemCount(): Int {
        return listHewan.size
    }
}