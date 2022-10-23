package com.example.mytracleapp.ui.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.mytracleapp.R
import com.example.mytracleapp.ui.viewModels.StatisticsViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 06/09/2022.
 */

@AndroidEntryPoint
class StatisticsFragment : Fragment(R.layout.fragment_statistics) {

    private val viewModel: StatisticsViewModel by viewModels()
}