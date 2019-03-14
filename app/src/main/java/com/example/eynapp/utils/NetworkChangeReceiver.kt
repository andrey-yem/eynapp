package com.example.eynapp.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager

class NetworkChangeReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, arg1: Intent) {

        if (networkChangeReceiverListener != null) {
            networkChangeReceiverListener!!.onNetworkConnectionChanged(isConnected(context))
        }
    }

    private fun isConnected(context: Context): Boolean {
        val connMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connMgr.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    interface NetworkChangeReceiverListener {
        fun onNetworkConnectionChanged(isConnected: Boolean)
    }

    companion object {
        var networkChangeReceiverListener: NetworkChangeReceiverListener? = null
    }
}