package ibrahim.db.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ibrahim.db.DetailsActivity
import ibrahim.db.data.Ad
import ibrahim.db.databinding.ListItemAdBinding


class AdsAdapter(val context: Context) : ListAdapter<Ad, RecyclerView.ViewHolder>(AdDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AdViewHolder(
            ListItemAdBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val ad = getItem(position)
        (holder as AdViewHolder).bind(ad)
    }

    inner class AdViewHolder(
        private val binding: ListItemAdBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.ad?.let { ad ->
                    openDetails(ad)
                }
            }
        }

        private fun openDetails(ad: Ad) {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(DetailsActivity.AD_ID, ad.uid)
            context.startActivity(intent)
        }


        fun bind(item: Ad) {
            binding.apply {
                ad = item
                executePendingBindings()
            }
        }
    }
}

private class AdDiffCallback : DiffUtil.ItemCallback<Ad>() {

    override fun areItemsTheSame(oldItem: Ad, newItem: Ad): Boolean {
        return oldItem.uid == newItem.uid
    }

    override fun areContentsTheSame(oldItem: Ad, newItem: Ad): Boolean {
        return oldItem == newItem
    }
}
