package com.assignment.diagnal.adapters

import android.content.Context
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.assignment.diagnal.R
import com.assignment.diagnal.core.CommonUtils
import com.assignment.diagnal.databinding.RvMovieItemBinding
import com.assignment.diagnal.models.ContentItem
import com.bumptech.glide.Glide

class MoviesListAdapter(
    private val context: Context, var arrayList: ArrayList<ContentItem?> = arrayListOf()
) : RecyclerView.Adapter<MoviesListAdapter.ViewHolder>() {

    var searchedText = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RvMovieItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(arrayList[position])
    }

    private fun add(item: ContentItem?) {
        arrayList.add(item)
        notifyItemInserted(arrayList.lastIndex)
    }

    fun addAll(itemList: ArrayList<ContentItem?>) {
        itemList.forEach {
            add(it)
        }
    }

    fun updateList(list: ArrayList<ContentItem?>) {
        arrayList = list
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val bindingItem: RvMovieItemBinding) :
        RecyclerView.ViewHolder(bindingItem.root) {
        fun bind(item: ContentItem?) {

            Glide.with(context).load(CommonUtils.getDrawable(context, item?.posterImage))
                .placeholder(R.drawable.placeholder_for_missing_posters)
                .error(R.drawable.placeholder_for_missing_posters).into(bindingItem.imgProduct)

            bindingItem.txtName.isSelected = true

            highlightText(searchedText, item?.name!!, bindingItem.txtName)
        }
    }

    private fun highlightText(
        searchText: String,
        name: String,
        textView: TextView
    ) {
        if (searchText.isNotEmpty() && name.lowercase().contains(searchText.lowercase())) {
            val sb = SpannableStringBuilder(name)
            var index: Int = name.lowercase().indexOf(searchText.lowercase())
            while (index >= 0 && index < name.length) {
                val fcs = ForegroundColorSpan(ContextCompat.getColor(context, R.color.colorHighlight))
                sb.setSpan(
                    fcs,
                    index,
                    index + searchText.length,
                    Spannable.SPAN_INCLUSIVE_INCLUSIVE
                )
                index = name.indexOf(searchText, index + 1)
            }
            textView.text = sb
        } else {
            textView.text = name
        }
    }
}