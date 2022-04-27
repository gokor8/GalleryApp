package com.example.galleryapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.galleryapp.R
import com.example.galleryapp.databinding.RecyclerViewItemBinding

class CustomRecyclerViewAdapter<I>(private val listData: List<I>) :
    RecyclerView.Adapter<CustomRecyclerViewAdapter<I>.CustomViewHolder>() {

    private var _binding: RecyclerViewItemBinding? = null
    private val binding
        get() = _binding!!

    inner class CustomViewHolder(binding: RecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val image: ImageView = binding.image
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        _binding = RecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.image.setImageResource(R.drawable.makaka)
    }

    override fun getItemCount(): Int = listData.size
}