package com.example.dog_4221

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dog_4221.databinding.ImageItemBinding
import com.example.dog_4221.model.local.BreedEntity
import com.example.dog_4221.model.local.ImagesBreed
import com.example.dog_4221.model.remote.WrapperBreed

class ImageAdapter: RecyclerView.Adapter<ImageAdapter.ImageVH>() {

    private var listImageAdapterItem = listOf<ImagesBreed>()

    private var selectedItem = MutableLiveData<ImagesBreed>()
    fun selectedItem(): MutableLiveData<ImagesBreed> {
        return selectedItem
    }

    fun update(list: List<ImagesBreed>) {
        listImageAdapterItem = list
        notifyDataSetChanged()
    }

    inner class ImageVH(private val binding: ImageItemBinding):
        RecyclerView.ViewHolder(binding.root), View.OnLongClickListener {
        fun bind(imagesBreed: ImagesBreed) {
            Glide.with(binding.imageView)
                .load(imagesBreed.imgURL)
                .into(binding.imageView)
            if(imagesBreed.fav){
                binding.ivFav.setColorFilter(Color.BLUE)
            } else {
                binding.ivFav.setColorFilter(Color.YELLOW)
            }
            itemView.setOnLongClickListener(this)
        }

        override fun onLongClick(v: View?): Boolean {
            selectedItem.value = listImageAdapterItem[adapterPosition]
            return true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageVH {
        return ImageVH(ImageItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ImageAdapter.ImageVH, position: Int) {
        val wrapperImages = listImageAdapterItem[position]
        holder.bind(wrapperImages)

    }
    override fun getItemCount(): Int = listImageAdapterItem.size


}