package com.example.adichat.ui.home

import android.os.Bundle
import com.example.adichat.ui.App
import com.example.adichat.ui.core.BaseActivity
import com.example.adichat.ui.core.BaseFragment

class MessagesActivity : BaseActivity() {
    override var fragment: BaseFragment = MessagesFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
    }
}