package com.example.galleryapp.core.ui.adapters

import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.recyclerview.widget.RecyclerView

class BaseRecyclerViewAdapter<M : Any, H : BaseViewHolder<M>>(
    private val itemList: List<M?>,
    private val bindingViewHolder: (parent: ViewGroup) -> H
) :
    RecyclerView.Adapter<H>() {

    @CallSuper
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): H = bindingViewHolder(parent)

    override fun onBindViewHolder(holderPhotos: H, position: Int) {
        holderPhotos.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size
}