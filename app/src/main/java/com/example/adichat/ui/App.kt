package com.example.adichat.ui

import android.app.Application
import com.example.adichat.presentation.injection.AppModule
import com.example.adichat.presentation.injection.CacheModule
import com.example.adichat.presentation.injection.RemoteModule
import com.example.adichat.presentation.injection.ViewModelModule
import com.example.adichat.ui.activity.RegisterActivity
import com.example.adichat.ui.fragment.RegisterFragment
import com.example.adichat.ui.service.FirebaseService
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

    //fragments
    fun inject(fragment: RegisterFragment)

    //services
    fun inject(service: FirebaseService)

}