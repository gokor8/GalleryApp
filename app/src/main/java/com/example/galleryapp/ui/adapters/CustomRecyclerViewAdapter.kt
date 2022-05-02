package com.example.galleryapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.galleryapp.R
import com.example.galleryapp.core.diffutils.CommonDiffUtilsItem
import com.example.galleryapp.databinding.RecyclerViewItemBinding
import com.example.galleryapp.ui.models.photo.PictureUiModel

class CustomRecyclerViewAdapter(
    private var pictureUiModelList: List<PictureUiModel?>,
) : PagingDataAdapter<PictureUiModel, CustomRecyclerViewAdapter.CustomViewHolder>(CommonDiffUtilsItem()) {

    private var _binding: RecyclerViewItemBinding? = null
    private val binding
        get() = _binding!!

    class CustomViewHolder(binding: RecyclerViewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val image: ImageView = binding.image
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        _binding =
            RecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val imageName = pictureUiModelList[position]?.pictureModel?.name

        if (pictureUiModelList.any { it == null }) {
            holder.image.setImageResource(R.drawable.lens_flare)
            return
        }

        Glide.with(binding.root).load("http://gallery.dev.webant.ru/media/${imageName}")
            .into(holder.image)
    }

    override fun getItemCount(): Int = pictureUiModelList.size

    /*fun setData(newPicturesUiModelList: List<PictureUiModel>) {
        if(pictureUiModelList.any { it == null }) return

        val commonDiffUtils = CommonDiffUtilsItem(pictureUiModelList.filterNotNull(), newPicturesUiModelList)
        val diffResult = DiffUtil.calculateDiff(commonDiffUtils)
        pictureUiModelList = newPicturesUiModelList
        diffResult.dispatchUpdatesTo(this)
    }*/
}