package luvnhi.luvbap.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import luvnhi.luvbap.R
import luvnhi.luvbap.data.model.Ringtone
import luvnhi.luvbap.databinding.ItemHomeViewHolderBinding
import java.util.concurrent.Executors

class HomeAdapter : ListAdapter<Any, RecyclerView.ViewHolder>(
    AsyncDifferConfig.Builder<Any>(ItemCallback())
        .setBackgroundThreadExecutor(Executors.newSingleThreadExecutor())
        .build()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_home_view_holder, parent, false) as ItemHomeViewHolderBinding
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(getItem(position) as Ringtone)
    }

    override fun submitList(list: List<Any>?) {
        super.submitList(ArrayList<Any>(list ?: listOf()))
    }
}

class ViewHolder(val binding: ItemHomeViewHolderBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(data: Ringtone) {
        binding.tvName.text = data.name
        Log.i("observerLiveData", "bind: ${data.name}")
    }
}

class ItemCallback: DiffUtil.ItemCallback<Any>() {
    override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
        return oldItem is Ringtone && newItem is Ringtone
    }

    override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
        return if (oldItem is Ringtone && newItem is Ringtone) oldItem.id == newItem.id
        else false
    }
}