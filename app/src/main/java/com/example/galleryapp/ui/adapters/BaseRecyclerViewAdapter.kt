package com.example.galleryapp.ui.adapters

import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.recyclerview.widget.RecyclerView
import com.example.galleryapp.core.ui.adapters.BaseViewHolder

class BaseRecyclerViewAdapter<L : Any, H : BaseViewHolder<L>>(
    private val itemList: List<L?>,
    private val bindingViewHolder: (parent: ViewGroup) -> H
) :
    RecyclerView.Adapter<H>() {

    @CallSuper
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): H {
        //_binding = bindingViewHolder(parent)
            //RecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return bindingViewHolder(parent)
    }

    override fun onBindViewHolder(holderPhotos: H, position: Int) {
        holderPhotos.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size
}