package com.example.dog_4221

import android.renderscript.ScriptGroup
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.example.dog_4221.databinding.BreedItemBinding
import com.example.dog_4221.model.local.BreedEntity
import com.example.dog_4221.model.remote.WrapperBreed

class BreedDogAdapter : RecyclerView.Adapter<BreedDogAdapter.BreedVH>() {

    private var listBreedDogAdapterItem = listOf<BreedEntity>()

    private val selectedBreed = MutableLiveData<BreedEntity>()
    fun selectedItem() = selectedBreed

    fun update(list: List<BreedEntity>){
        listBreedDogAdapterItem = list
        notifyDataSetChanged()
    }
    inner class BreedVH(private val binding: BreedItemBinding) : RecyclerView.ViewHolder
        (binding.root), View.OnClickListener {
        fun bind(breedEntity: BreedEntity) {
            binding.tvBreed.text = breedEntity.breed
           itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            selectedBreed.value = listBreedDogAdapterItem [adapterPosition]
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedDogAdapter.BreedVH {
        return BreedVH(BreedItemBinding.inflate(LayoutInflater.from(parent.context)))
    }
    override fun onBindViewHolder(holder: BreedDogAdapter.BreedVH, position: Int) {
        val breedEntity = listBreedDogAdapterItem[position]
        holder.bind(breedEntity)
    }
    override fun getItemCount(): Int =listBreedDogAdapterItem.size

}

private fun <TranscodeType> RequestBuilder<TranscodeType>.into(tvBreed: TextView) {

}
