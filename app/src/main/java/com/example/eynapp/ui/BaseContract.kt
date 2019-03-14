package com.example.eynapp.ui

class BaseContract {

    interface Presenter<in T> {
        fun bindView(view: T)
        fun unbindView()
    }

    interface View
}