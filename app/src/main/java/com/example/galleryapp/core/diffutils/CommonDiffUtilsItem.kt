package com.example.galleryapp.core.diffutils

import androidx.recyclerview.widget.DiffUtil

class CommonDiffUtilsItem<T : CommonDiffUtilsItem.DiffUtilModel<*>> : DiffUtil.ItemCallback<T>() {

    interface DiffUtilModel<I> {
        val comparedId: I
    }

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
        oldItem.comparedId == newItem.comparedId

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean = oldItem.equals(newItem)
}

/*
class CommonDiffUtilsItem<T : CommonDiffUtilsItem.DiffUtilModel<*>>(
    private val oldList: List<T>,
    private val newList: List<T>
) : DiffUtil.Callback() {

    interface DiffUtilModel<I> {
        val comparedId: I
    }

    //override fun areItemsTheSame(oldItem: T, newItem: T): Boolean = oldItem.comparedId == newItem.comparedId
    //override fun areContentsTheSame(oldItem: T, newItem: T): Boolean = oldItem.equals(newItem)

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].comparedId == newList[newItemPosition].comparedId

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]
}*/
