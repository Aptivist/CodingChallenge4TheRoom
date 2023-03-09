package com.aptivist.roomchallengeone.ui.meme

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aptivist.roomchallengeone.domain.IMemeRepository
import com.aptivist.roomchallengeone.domain.models.MemeItem
import com.aptivist.roomchallengeone.domain.models.RepositoryResponse
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class MemeViewModel(private val memeRepository: IMemeRepository) : ViewModel() {

    private var _memeViewState = mutableStateOf<MemeViewState>(MemeViewState.Loading)
    val memeViewState : State<MemeViewState>
        get() = _memeViewState

    private var _memeViewActions = Channel<MemeViewActions>()
    val memeViewActions = _memeViewActions.consumeAsFlow()

    private var _memeList = mutableStateListOf<MemeItem>()
    val memeList : SnapshotStateList<MemeItem>
        get() = _memeList

    fun getMemes(){
        viewModelScope.launch {
            memeRepository.getMemes().collect {
                when(it){
                    is RepositoryResponse.Failed -> {
                        _memeViewActions.trySend(MemeViewActions.ShowError(it.errorMessage) {
                            getMemes()
                        })
                    }
                    is RepositoryResponse.Success -> {
                        _memeList.clear()
                        _memeList.addAll(it.data)
                    }
                }
                _memeViewState.value = MemeViewState.Idle
            }
        }
    }

}

sealed class MemeViewActions {
    data class ShowError(val message: String, val action: () -> Unit): MemeViewActions()
}