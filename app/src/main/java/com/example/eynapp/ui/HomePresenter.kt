package com.example.eynapp.ui

import com.example.andrey.lastfmapp.api.MessagingService
import com.example.andrey.lastfmapp.utils.IScheduler
import com.example.eynapp.api.MessageDTO
import com.example.eynapp.domain.EynMessage
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import java.util.*
import javax.inject.Inject

class HomePresenter @Inject constructor(
        private val api: MessagingService,
        private val scheduler: IScheduler
) : HomeContract.Presenter {

    private lateinit var view: HomeContract.View
    private val subscriptions = CompositeDisposable()
    var messages = mutableListOf<EynMessage>()

    override fun bindView(view: HomeContract.View) {
        this.view = view
    }

    override fun unbindView() {
        subscriptions.clear()
    }

    override fun testNumber(number: String) {
        val longValue = number.toLongOrNull()
        if (longValue == null) {
            view.showToast("Invalid number")
        } else {
            subscriptions.add(
                    Single.fromCallable {
                        isPrimeNumberJNI(longValue)
                    }
                    .observeOn(scheduler.main())
                    .subscribeOn(scheduler.background())
                    .subscribe({ result ->
                        // TODO: move to Strings.xml, inject StingRes in Presenter
                        val text = String.format(Locale.getDefault(),
                                "%d %s", longValue, if (result) "is PRIME" else "is NOT PRIME")
                        view.showToast(text)
                    }, { error ->
                        view.showToast(error.message ?: "")
                    }))
        }
    }

    // TODO: this should be done in proper peristance layer / DB
    private fun createMessage(message: String): EynMessage {
        return EynMessage(counter++, message, false)
    }

    private fun sendMessage(eynMessage: EynMessage) {
        subscriptions.add(api.sendMessage(MessageDTO().also { it.body = eynMessage.text })
                .map {
                    val index = messages.indexOf(eynMessage)
                    messages[index] = EynMessage(eynMessage.id, eynMessage.text, true)
                }
                .subscribeOn(scheduler.background())
                .observeOn(scheduler.main())
                .subscribe({ combinedResults ->
                    view.showMessages(messages)
                }, { error ->
                    view.showToast(error.message ?: "")
                }))
    }

    override
    fun onSendClicked(message: String) {
        val eynMessage = createMessage(message)
        messages.add(eynMessage)
        view.showMessages(messages)
        sendMessage(eynMessage)
    }

    override
    fun onNetworkConnected() {
        val messagesToSend = messages.filter { !it.isSent }
        messagesToSend.map { eynMessage ->
            sendMessage(eynMessage)
        }
    }

    companion object {
        var counter = 1

        // TODO: move it in separate component/Singleton which can be shared between presenters
        @JvmStatic external fun isPrimeNumberJNI(number: Long): Boolean

        init {
            System.loadLibrary("eyn-jni")
        }
    }
}