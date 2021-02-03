package com.example.adichat.ui.account

import android.os.Bundle
import com.example.adichat.ui.App
import com.example.adichat.ui.core.BaseActivity
import com.example.adichat.ui.core.BaseFragment

class AccountActivity : BaseActivity() {
    override var fragment: BaseFragment = AccountFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
    }
}