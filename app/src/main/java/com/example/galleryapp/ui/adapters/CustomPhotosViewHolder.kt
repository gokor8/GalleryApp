package com.example.galleryapp.ui.adapters

import com.bumptech.glide.Glide
import com.example.galleryapp.R
import com.example.galleryapp.core.ui.adapters.BaseViewHolder
import com.example.galleryapp.databinding.RecyclerViewItemBinding
import com.example.galleryapp.ui.models.photo.PictureInfoUiModel

class CustomPhotosViewHolder(private val binding: RecyclerViewItemBinding) :
    BaseViewHolder<PictureInfoUiModel>(binding.root) {

    override fun bind(model: PictureInfoUiModel?) {
        val imageUrlName = model?.pictureUiModel?.urlName

        if (imageUrlName == null) {
            binding.image.setImageResource(R.drawable.lens_flare)
            return
        } else {
            Glide.with(binding.root)
                .load("http://gallery.dev.webant.ru/media/${model.pictureUiModel.urlName}")
                .into(binding.image)
        }
    }
}