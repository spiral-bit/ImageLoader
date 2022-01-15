package spiral.bit.dev.imageloader.ui.features.feed.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import spiral.bit.dev.imageloader.R
import spiral.bit.dev.imageloader.databinding.ItemPictureBinding
import spiral.bit.dev.imageloader.ui.features.favouritePictures.models.UnsplashPictureItemUI

class FeedAdapter(private val onPictureClick: (UnsplashPictureItemUI) -> Unit) : PagingDataAdapter<UnsplashPictureItemUI, FeedAdapter.UnsplashViewHolder>(DiffCallback()) {

    private var context: Context? = null

    class DiffCallback : DiffUtil.ItemCallback<UnsplashPictureItemUI>() {
        override fun areItemsTheSame(oldItem: UnsplashPictureItemUI, newItem: UnsplashPictureItemUI) =
            oldItem::class == newItem::class

        override fun areContentsTheSame(oldItem: UnsplashPictureItemUI, newItem: UnsplashPictureItemUI) =
            oldItem.hashCode() == newItem.hashCode()
    }

    override fun onBindViewHolder(holder: UnsplashViewHolder, position: Int) {
        holder.bind(unsplashPicture = getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UnsplashViewHolder {
        context = parent.context
        return UnsplashViewHolder(ItemPictureBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    inner class UnsplashViewHolder(private val itemBinding: ItemPictureBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        init {
            itemBinding.moreDetailsButton.setOnClickListener { 
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    getItem(position)?.let(onPictureClick)
                }
            }
        }
        
        fun bind(unsplashPicture: UnsplashPictureItemUI?) = with(itemBinding) {
            pictureImageView.load(unsplashPicture!!.urls.full)  { transformations(RoundedCornersTransformation(32f)) }
            pictureDescriptionTextView.text = unsplashPicture.description ?: context?.getString(R.string.no_description_label)
            pictureAuthorTextView.text = unsplashPicture.user.username
            moreDetailsButton.text = context?.getString(R.string.save_label)
        }
    }
}