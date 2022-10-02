package com.example.imageapplication

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.imageapplication.databinding.FragmentImageDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageDetailFragment : Fragment() {

    private var _binding : FragmentImageDetailBinding? = null
    private val binding: FragmentImageDetailBinding get() = _binding!!

    private val args: ImageDetailFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImageDetailBinding.inflate(inflater,container,false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val defaultLikes = args.likes
        binding.ivImageLarge.load(args.image)

        binding.tvAmountOfLikes.text = "This has had " + defaultLikes + " likes! :D"
        binding.ivHeart.setOnClickListener {
            var addedLike = 1
            var newLikeAmount = defaultLikes.plus(addedLike)
            binding.tvAmountOfLikes.text = "Now this has $newLikeAmount likes! :D"

        }

    }

}