package id.chirikualii.base_clean_arch_example.abstraction.presentation

import androidx.recyclerview.widget.DiffUtil

abstract class BaseDiffCallback<in T>(
    private val oldList: List<T>,
    private val newList: List<T>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition]?.equals( newList[newItemPosition]) == true
    }
}