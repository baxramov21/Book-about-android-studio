package com.example.projectkarina.presentation.rc_view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.projectkarina.R
import com.example.projectkarina.domain.entities.Note

class NoteAdapter : ListAdapter<Note, NoteAdapter.MyViewHolder>(NoteItemDiffCallback()) {

    private lateinit var context: Context

    //    private lateinit var mClickListener: onItemClickListener
    var onShopItemLongClickListener: ((Note) -> Unit)? = null
    var onShopItemClickListener: ((Note) -> Unit)? = null

//    interface onItemClickListener {
//        fun onItemClick(position: Int)
//    }
//
//    fun setOnItemClickListener(listener: onItemClickListener) {
//        mClickListener = listener
//    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.note_item, parent, false)
        return MyViewHolder(
            view
//            , mClickListener
        )
    }


    override fun getItemCount(): Int {
        return currentList.size;
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        with(holder) {
            webPageName.text = item.title

            holder.itemView.setOnLongClickListener {
                onShopItemLongClickListener?.invoke(item)
                true
            }

            holder.itemView.setOnClickListener {
                onShopItemClickListener?.invoke(item)
            }
        }
    }

    inner class MyViewHolder(
        itemView: View
//        , listener: onItemClickListener
    ) :
        RecyclerView.ViewHolder(itemView) {
        val webPageName: TextView = itemView.findViewById(R.id.textViewNoteName)

//        init {
//            itemView.setOnClickListener {
//                listener.onItemClick(adapterPosition)
//            }
//        }
    }
}