package com.example.vorti.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.vorti.R
import com.example.vorti.databinding.FragmentWelcomePageBinding
import com.example.vorti.model.VortiViewModel


class WelcomePage : Fragment() {

    private val sharedViewModel: VortiViewModel by activityViewModels()

    private var binding: FragmentWelcomePageBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWelcomePageBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            welcomePage = this@WelcomePage
        }
    }

    /**
     * When the "LOGIN" button is clicked
     */
    fun login(){
        findNavController().navigate(R.id.action_welcomePage_to_loginPage)
    }

    /**
     * When the "SIGN UP" button is clicked
     */
    fun signup(){
        findNavController().navigate(R.id.action_welcomePage_to_signinPage)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}