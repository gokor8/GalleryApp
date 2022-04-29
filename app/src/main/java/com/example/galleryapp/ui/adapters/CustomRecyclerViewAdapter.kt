package com.example.galleryapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.galleryapp.databinding.RecyclerViewItemBinding
import com.example.galleryapp.ui.models.ImageHandler

class CustomRecyclerViewAdapter(private val listData: List<String?>, private val imageHandler: ImageHandler) :
    RecyclerView.Adapter<CustomRecyclerViewAdapter.CustomViewHolder>() {

    private var _binding: RecyclerViewItemBinding? = null
    private val binding
        get() = _binding!!

    inner class CustomViewHolder(binding: RecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val image: ImageView = binding.image
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        _binding =
            RecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        Glide.with(binding.root).load("http://gallery.dev.webant.ru/media/${listData[position]}").into(holder.image)
    }

    override fun getItemCount(): Int = listData.size
}