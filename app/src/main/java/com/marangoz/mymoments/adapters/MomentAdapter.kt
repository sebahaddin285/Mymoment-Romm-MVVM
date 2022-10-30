package com.marangoz.mymoments.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.marangoz.mymoments.R
import com.marangoz.mymoments.models.Momets
import com.marangoz.mymoments.ui.main.MainViewModel

class MomentAdapter(val mContext: Context?,val viewModel: MainViewModel)
    : RecyclerView.Adapter<MomentAdapter.ViewHolderClass>(){

    var momentList: List<Momets> = listOf()

    inner class ViewHolderClass(view: View) : RecyclerView.ViewHolder(view) {
        var momentText : TextView
        var deletIcon : ImageView

        init {
            momentText = view.findViewById(R.id.momentText)
            deletIcon = view.findViewById(R.id.imageView)
        }

    }
    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val moment = momentList[position]


        holder.momentText.text = moment.mText

        holder.deletIcon.setOnClickListener(){
            viewModel.deleteNotes(moment)
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        var design = LayoutInflater.from(mContext).inflate(R.layout.adapter_design, parent, false)
        return ViewHolderClass(design)
    }

    override fun getItemCount(): Int {
       return momentList.size
    }

    fun setList(list: List<Momets>){
        momentList = list
        notifyDataSetChanged()
    }


}