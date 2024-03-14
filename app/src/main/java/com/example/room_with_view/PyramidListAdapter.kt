package com.example.room_with_view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView


class PyramidListAdapter : ListAdapter<Pyramid,PyramidListAdapter.PyramidViewHolder>(PyramidComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PyramidViewHolder {
        return PyramidViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: PyramidViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.key)
    }

    class PyramidViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val pyramidItemView: TextView = itemView.findViewById(R.id.textView)

        fun bind(text: String?) {
            pyramidItemView.text = text
        }

        companion object {

            fun create(parent: ViewGroup): PyramidViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                return PyramidViewHolder(view)
            }
        }
    }

    companion object{
        private val PyramidComparator = object : DiffUtil.ItemCallback<Pyramid>() {
            override fun areItemsTheSame(oldItem: Pyramid, newItem: Pyramid): Boolean {
                return oldItem === newItem
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Pyramid, newItem: Pyramid): Boolean {
                return oldItem.key == newItem.key
            }
        }
    }
}