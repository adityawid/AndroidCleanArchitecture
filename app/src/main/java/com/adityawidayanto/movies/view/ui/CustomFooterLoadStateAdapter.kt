package com.adityawidayanto.movies.view.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.adityawidayanto.common.databinding.LoadStateFooterBinding
import com.adityawidayanto.movies.R

class CustomFooterLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<CustomFooterLoadStateAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: LoadStateFooterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.btnRetry.setOnClickListener {
                retry.invoke()
            }
        }

        fun bind(loadState: LoadState) {
            with(binding) {
                progressBar.isVisible = loadState is LoadState.Loading
                btnRetry.isVisible = loadState !is LoadState.Loading
                tvErrorState.isVisible = loadState !is LoadState.Loading
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: LoadStateFooterBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.load_state_footer,
            parent,
            false
        )
        return ViewHolder(binding)
    }
}