package com.example.galleryapp.core.ui.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<M>(view : View) : RecyclerView.ViewHolder(view) {

    abstract fun bind(model: M?)
}