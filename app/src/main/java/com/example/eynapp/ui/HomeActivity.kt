package com.example.eynapp.ui

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.andrey.lastfmapp.di.DaggerApplicationComponent
import com.example.eynapp.R
import com.example.eynapp.domain.EynMessage
import com.example.eynapp.utils.NetworkChangeReceiver
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class HomeActivity : AppCompatActivity(), HomeContract.View, NetworkChangeReceiver.NetworkChangeReceiverListener {

    @Inject
    lateinit var presenter : HomePresenter

    private lateinit var listAdapter: MessageRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setupListeners()
        initListAdapter()
        injectDependency()
        registerReceiver(NetworkChangeReceiver(),
                IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        presenter.bindView(this)
    }

    private fun injectDependency() {
        val activityComponent = DaggerApplicationComponent.builder()
                .build()
        activityComponent.inject(this)
    }

    private fun initListAdapter() {
        rvMessages.layoutManager = LinearLayoutManager(this)
        listAdapter = MessageRecyclerViewAdapter(this)
        rvMessages.adapter = listAdapter
    }

    private fun setupListeners() {
        btnTest.setOnClickListener { _ ->
            presenter.testNumber(editNumber.text.toString())
        }

        btnSend.setOnClickListener { _ ->
            val message = editMessage.text.toString()
            if (message.isNotEmpty()) {
                presenter.onSendClicked(message)
                editMessage.setText("")
            }
        }
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        if (isConnected) {
            presenter.onNetworkConnected()
        }
    }

    override fun onResume() {
        super.onResume()
        NetworkChangeReceiver.networkChangeReceiverListener = this
    }

    override fun onDestroy() {
        presenter.unbindView()
        super.onDestroy()
    }

    override fun showMessages(messages: List<EynMessage>) {
        listAdapter.setItems(messages)
        listAdapter.notifyDataSetChanged()
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
