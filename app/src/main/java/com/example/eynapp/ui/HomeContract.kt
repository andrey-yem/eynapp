package com.example.eynapp.ui

import android.os.Bundle
import com.example.eynapp.domain.EynMessage


class HomeContract {

    interface View: BaseContract.View {
        fun showMessages(messages: List<EynMessage>)
        fun showToast(message: String)
        /*
        fun goToAlbumDetailsScreen(album: Album, transitionName: String, optionsBundle : Bundle?)

        fun showProgressBar(showBar: Boolean)
        */
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun onSendClicked(message: String)
        fun testNumber(number: String)
        fun onNetworkConnected()
    }
}
