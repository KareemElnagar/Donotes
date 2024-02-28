package com.kareem.donotes.adapter

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kareem.donotes.R
import com.kareem.donotes.databinding.ItemNoteBinding
import com.kareem.donotes.entities.Notes

class NotesAdapter(private val arrList: List<Notes>) :
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {


    class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemNoteBinding.bind(itemView)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NotesViewHolder {
        return NotesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.binding.tvNoteItemTitle.text = arrList[position].title
        holder.binding.tvNoteItemDesc.text = arrList[position].noteText
        if (arrList[position].imgPath != null) {
            holder.binding.ImgNoteView.setImageBitmap(BitmapFactory.decodeFile(arrList[position].imgPath))
            holder.binding.ImgNoteView.visibility = View.VISIBLE
        } else {
            holder.binding.ImgNoteView.visibility = View.GONE

        }
    }

    override fun getItemCount(): Int = arrList.size
}