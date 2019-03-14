package com.example.eynapp.ui

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.eynapp.domain.EynMessage
import com.example.eynapp.R
import kotlinx.android.synthetic.main.message_row.view.*

class MessageRecyclerViewAdapter(context: Context)
: RecyclerView.Adapter<MessageRecyclerViewAdapter.ViewHolder>() {
    private val mInflater: LayoutInflater = LayoutInflater.from(context)
    private var items: List<EynMessage> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.message_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val message = items[position]
        holder.itemView.tvMessage.text = message.text
        val drawableResource = if (message.isSent) R.drawable.ic_check_black_24dp else 0
        holder.itemView.tvMessage.setCompoundDrawablesWithIntrinsicBounds(
                0, 0, drawableResource, 0);
    }

    override fun getItemCount(): Int {
        return items.size
    }

    internal fun getItems() : List<EynMessage> {
        return items
    }

    internal fun setItems(items : List<EynMessage>) {
        this.items = items
    }

    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView)
}
