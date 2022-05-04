package com.example.galleryapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.galleryapp.R
import com.example.galleryapp.core.diffutils.CommonDiffUtilsItem
import com.example.galleryapp.databinding.RecyclerViewItemBinding
import com.example.galleryapp.ui.models.photo.PictureInfoUiModel

class CustomRecyclerViewAdapter() :
    PagingDataAdapter<PictureInfoUiModel, CustomRecyclerViewAdapter.CustomViewHolder>(
        CommonDiffUtilsItem()
    ) {

    private var _binding: RecyclerViewItemBinding? = null
    private val binding
        get() = _binding!!

    class CustomViewHolder(private val binding: RecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val image: ImageView = binding.image

        fun bind(pictureInfoUiModel: PictureInfoUiModel) {
            /*if (pictureUiModelList.any { it == null }) {
                holder.image.setImageResource(R.drawable.lens_flare)
                return
            }*/

            Glide.with(binding.root)
                .load("http://gallery.dev.webant.ru/media/${pictureInfoUiModel.pictureUiModel.urlName}")
                .into(image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        _binding =
            RecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val imageName = getItem(position)?.pictureUiModel?.urlName

        /*if (pictureUiModelList.any { it == null }) {
            holder.image.setImageResource(R.drawable.lens_flare)
            return
        }*/

        getItem(position)?.let { holder.bind(it) }
    }

    //override fun getItemCount(): Int = pictureUiModelList.size

    /*fun setData(newPicturesUiModelList: List<PictureInfoUiModel>) {
        if(pictureUiModelList.any { it == null }) return

        val commonDiffUtils = CommonDiffUtilsItem(pictureUiModelList.filterNotNull(), newPicturesUiModelList)
        val diffResult = DiffUtil.calculateDiff(commonDiffUtils)
        pictureUiModelList = newPicturesUiModelList
        diffResult.dispatchUpdatesTo(this)
    }*/
}