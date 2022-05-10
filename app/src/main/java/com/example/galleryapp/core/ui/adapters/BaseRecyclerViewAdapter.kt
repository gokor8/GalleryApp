package com.example.galleryapp.core.ui.adapters

import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.galleryapp.core.diffutils.CommonDiffUtils
import com.example.galleryapp.core.diffutils.CommonDiffUtilsItem
import com.example.galleryapp.core.diffutils.DiffUtilModel

class BaseRecyclerViewAdapter<M : DiffUtilModel<*>, H : BaseViewHolder<M>>(
    itemList: List<M?>,
    private val bindingViewHolder: (parent: ViewGroup) -> H,
) :
    RecyclerView.Adapter<H>() {

    private val itemList: MutableList<M?> = itemList.toMutableList()

    @CallSuper
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): H = bindingViewHolder(parent)

    override fun onBindViewHolder(holderPhotos: H, position: Int) {
        holderPhotos.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    fun onNewData(newDataList: List<M>) {
        if(itemList.any { it == null }) itemList.toMutableList().clear()

        val diffResult: DiffUtil.DiffResult =
            DiffUtil.calculateDiff(CommonDiffUtils(newDataList, itemList.filterNotNull()))
        diffResult.dispatchUpdatesTo(this)
        itemList.clear()
        itemList.addAll(newDataList)
    }
}