package dev.pegasus.installedapps.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.pegasus.installedapps.dataClasses.AppItem
import dev.pegasus.installedapps.databinding.ItemAppBinding

/**
 * @Author: SOHAIB AHMED
 * @Date: 19,October,2023.
 * @Accounts
 *      -> https://github.com/epegasus
 *      -> https://linkedin.com/in/epegasus
 */

class AdapterApps : ListAdapter<AppItem, AdapterApps.CustomViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemAppBinding.inflate(layoutInflater, parent, false)
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.apply {
            binding.mtvAppName.text = currentItem.appName
            binding.mtvPackageName.text = currentItem.packageName
            binding.ifvIcon.setImageDrawable(currentItem.icon)
        }
    }

    inner class CustomViewHolder(val binding: ItemAppBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<AppItem>() {
            override fun areItemsTheSame(oldItem: AppItem, newItem: AppItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: AppItem, newItem: AppItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}