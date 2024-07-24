package pl.mrmatiasz.hotelapp.presentation.room_list_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import pl.mrmatiasz.hotelapp.domain.repository.AuthRepository
import pl.mrmatiasz.hotelapp.util.Resource
import javax.inject.Inject

@HiltViewModel
class RoomListScreenViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel() {

    private val _roomListState = Channel<RoomListState>()
    val roomListState = _roomListState.receiveAsFlow()

    fun onEvent(event: RoomListEvent) {
        when(event) {
            is RoomListEvent.LogOutButton -> logOut()
        }
    }

    private fun logOut() {
        viewModelScope.launch {
            authRepository.logOut().collect { result ->
                when(result) {
                    is Resource.Success -> _roomListState.send(RoomListState(logOutSuccess = "Logout success"))

                    is Resource.Error -> {
                        _roomListState.send(RoomListState(logOutError = result.message))
                        Log.d("SUPABASE_AUTH_ERROR", "${result.message}")
                    }

                    else -> {
                        _roomListState.send(RoomListState(logOutError = "Unexpected error"))
                        Log.d("SUPABASE_AUTH_ERROR", "${result.message}")
                    }
                }
            }
        }
    }
}