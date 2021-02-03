package com.example.adichat.ui.user

import android.os.Bundle
import com.example.adichat.ui.App
import com.example.adichat.ui.core.BaseActivity
import com.example.adichat.ui.core.BaseFragment

class UserActivity : BaseActivity() {
    override var fragment: BaseFragment = UserFragment()

    //инициализация компонентов
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
    }
}
