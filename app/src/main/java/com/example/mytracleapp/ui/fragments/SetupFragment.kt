package com.example.mytracleapp.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.mytracleapp.R
import com.example.mytracleapp.databinding.FragmentSetupBinding

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 06/09/2022.
 */
class SetupFragment : Fragment(R.layout.fragment_setup) {
    private val binding: FragmentSetupBinding by viewBinding()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvContinue.setOnClickListener {
            findNavController().navigate(R.id.action_setupFragment_to_runFragment)
        }
    }

}