package com.example.imageapplication.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.imageapplication.common.Constants
import com.example.imageapplication.ImageFragmentDirections
import com.example.imageapplication.databinding.ImageItemBinding
import com.example.imageapplication.domain.model.Results

class ImageAdapter(
): RecyclerView.Adapter<ImageAdapter.ImageViewHolder> () {

    private var searchedImages = mutableListOf<Results>()


    fun setSearchedImageList(searchImages: List<Results>) {
        searchedImages = searchImages.toMutableList()
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.onBind(searchedImages[position])
    }

    override fun getItemCount(): Int {
        return searchedImages.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            ImageItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    class ImageViewHolder(
        private val binding: ImageItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {


        fun onBind(
            searchImages: Results
        ) {

            binding.ivImage.load(searchImages.urls.regular){
                crossfade(true)
                crossfade(1000)

            }

            binding.tvDescription.text =
                searchImages.description
                    ?: ("This is an awesome "
                            + Constants.searchExample
                            +
                            " image :D!")



            val action = ImageFragmentDirections.actionImageFragmentToImageDetailFragment(searchImages.likes?:0, searchImages.urls.regular)
            binding.cvImageCard.setOnClickListener { imageCard ->
                imageCard.findNavController().navigate(action)
            }

        }

        }

    }
