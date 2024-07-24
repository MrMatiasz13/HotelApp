package pl.mrmatiasz.hotelapp.presentation.room_list_screen

sealed class RoomListEvent {

    data object LogOutButton: RoomListEvent()
}