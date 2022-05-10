package com.example.galleryapp.core.diffutils

import androidx.recyclerview.widget.DiffUtil

class CommonDiffUtilsItem<T : DiffUtilModel<*>> : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
        oldItem.comparedId == newItem.comparedId

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean = oldItem.equals(newItem)
}
