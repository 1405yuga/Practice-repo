package com.example.brewview.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.example.brewview.paging.BeersPagingSource
import com.example.brewview.paging.ProjectConstants

class BeerViewModel : ViewModel() {

    val beersList = Pager(
        config = PagingConfig(pageSize = ProjectConstants.PER_PAGE, maxSize = ProjectConstants.PER_PAGE*8),
        pagingSourceFactory = {BeersPagingSource()}
    ).liveData.cachedIn(viewModelScope)
}