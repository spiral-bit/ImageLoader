package spiral.bit.dev.imageloader.ui.common.adapters

import androidx.recyclerview.widget.DiffUtil

val diffCallback = object : DiffUtil.ItemCallback<ListItem>() {
    override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem) =
        oldItem::class == newItem::class

    override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem) =
        oldItem.hashCode() == newItem.hashCode()
}

