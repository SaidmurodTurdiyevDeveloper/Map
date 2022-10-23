package com.example.mytracleapp.ui.viewModels

import androidx.lifecycle.ViewModel
import com.example.mytracleapp.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 06/09/2022.
 */
@HiltViewModel
class StatisticsViewModel @Inject constructor(
    val mainRepository: MainRepository
) : ViewModel() {
}