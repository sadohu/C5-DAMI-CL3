package com.example.project_cl3

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.utils.widget.ImageFilterView
import androidx.recyclerview.widget.RecyclerView
import com.example.project_cl3.domain.Restaurante
import com.bumptech.glide.Glide

class MainAdapter(var items : MutableList<Restaurante>, var iCard : ICard) : RecyclerView.Adapter<MainAdapter.ViewHolder>(){

    interface ICard{
        fun onCardClick(item: Restaurante)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), OnClickListener{
        val titulo : TextView = itemView.findViewById(R.id.rvRestauranteTitulo)
        val descripcion : TextView = itemView.findViewById(R.id.rvRestauranteDescripcion)
        val img : ImageFilterView = itemView.findViewById(R.id.rvRestauranteImg)
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            iCard.onCardClick(items[adapterPosition])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_restaurante, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        println(items.size)
        val item = items[position]
        holder.titulo.text = item.nombre
        holder.descripcion.text = item.descripcion
        Glide.with(holder.itemView.context)
            .load(item.img)
            .into(holder.img)
    }
    fun update(newItems : List<Restaurante>){
        this.items.clear()
        this.items.addAll(newItems)
        notifyDataSetChanged()
    }
}