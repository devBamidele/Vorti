package com.example.vorti.pages


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.vorti.databinding.FragmentBodyPageBinding
import com.example.vorti.model.VortiViewModel


class BodyPage : Fragment() {

    private val sharedViewModel: VortiViewModel by activityViewModels()

    private var binding: FragmentBodyPageBinding? = null

    override fun onCreateView(
        inflate: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBodyPageBinding.inflate(inflate, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            bodyPage = this@BodyPage
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}