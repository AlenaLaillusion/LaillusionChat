package com.example.adichat.ui

import android.app.Application
import com.example.adichat.presentation.injection.AppModule
import com.example.adichat.presentation.injection.CacheModule
import com.example.adichat.presentation.injection.RemoteModule
import com.example.adichat.presentation.injection.ViewModelModule
import com.example.adichat.ui.account.AccountActivity
import com.example.adichat.ui.account.AccountFragment
import com.example.adichat.ui.core.navigation.RouteActivity
import com.example.adichat.ui.firebase.FirebaseService
import com.example.adichat.ui.friends.FriendRequestsFragment
import com.example.adichat.ui.friends.FriendsFragment
import com.example.adichat.ui.home.ChatsFragment
import com.example.adichat.ui.home.HomeActivity
import com.example.adichat.ui.home.MessagesActivity
import com.example.adichat.ui.home.MessagesFragment
import com.example.adichat.ui.login.ForgetPasswordFragment
import com.example.adichat.ui.login.LoginFragment
import com.example.adichat.ui.register.RegisterActivity
import com.example.adichat.ui.register.RegisterFragment
import com.example.adichat.ui.user.UserActivity
import com.example.adichat.ui.user.UserFragment
import dagger.Component
import javax.inject.Singleton

class App : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        initAppComponent()
    }

    private fun initAppComponent() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this)).build()
    }
}

@Singleton
@Component(modules = [AppModule::class, CacheModule::class, RemoteModule::class, ViewModelModule::class])
interface AppComponent {

    //activities
    fun inject(activity: RegisterActivity)
    fun inject(activity: RouteActivity)
    fun inject(activity: HomeActivity)
    fun inject(activity: AccountActivity)
    fun inject(activity: MessagesActivity)
    fun inject(activity: UserActivity)

    //fragments
    fun inject(fragment: RegisterFragment)
    fun inject(fragment: LoginFragment)
    fun inject(fragment: ChatsFragment)
    fun inject(fragment: FriendsFragment)
    fun inject(fragment: FriendRequestsFragment)
    fun inject(fragment: AccountFragment)
    fun inject(fragment: MessagesFragment)
    fun inject(fragment: UserFragment)
    fun inject(fragment: ForgetPasswordFragment)

    fun inject(service: FirebaseService)


}