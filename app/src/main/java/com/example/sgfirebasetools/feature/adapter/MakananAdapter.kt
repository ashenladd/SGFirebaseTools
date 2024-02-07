package com.example.sgfirebasetools.feature.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sgfirebasetools.databinding.ItemMakananBinding

class MakananAdapter(
    private val makanan: List<MakananModel>,
    private val listener: MakananListener,
) : RecyclerView.Adapter<MakananAdapter.MakananViewHolder>() {
    class MakananViewHolder(val binding: ItemMakananBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            model: MakananModel,
            listener: MakananListener,
        ) {
            binding.apply {
                tvName.text = model.name
                Glide.with(binding.root.context)
                    .load(model.imageUrl)
                    .into(ivImage)
                cvMakanan.setOnClickListener {
                    listener.onMakananClicked(model.id)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MakananViewHolder {
        return MakananViewHolder(
            ItemMakananBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return makanan.size
    }

    override fun onBindViewHolder(holder: MakananViewHolder, position: Int) {
        holder.bind(makanan[position], listener)
    }
}
