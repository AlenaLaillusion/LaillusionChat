package com.example.adichat.ui.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import com.example.adichat.R
import com.example.adichat.databinding.ActivityNavigationBinding
import com.example.adichat.domain.account.AccountEntity
import com.example.adichat.domain.friends.FriendEntity
import com.example.adichat.domain.type.Failure
import com.example.adichat.domain.type.None
import com.example.adichat.presentation.viewmodel.AccountViewModel
import com.example.adichat.presentation.viewmodel.FriendsViewModel
import com.example.adichat.remote.service.ApiService
import com.example.adichat.ui.App
import com.example.adichat.ui.core.BaseActivity
import com.example.adichat.ui.core.BaseFragment
import com.example.adichat.ui.core.ext.onFailure
import com.example.adichat.ui.core.ext.onSuccess
import com.example.adichat.ui.firebase.NotificationHelper
import com.example.adichat.ui.friends.FriendRequestsFragment
import com.example.adichat.ui.friends.FriendsFragment
import javax.inject.Inject

//для отображения фрагментов чатов и меню активити
class HomeActivity :  BaseActivity() {

        override var fragment: BaseFragment = ChatsFragment()

        override val contentId = R.layout.activity_navigation

        private lateinit var accountViewModel: AccountViewModel

        private lateinit var binding: ActivityNavigationBinding

        @Inject
        lateinit var friendsViewModel: FriendsViewModel


        override fun setupContent() {
            binding = DataBindingUtil.setContentView(this, R.layout.activity_navigation)
        }


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

            App.appComponent.inject(this)

            accountViewModel = viewModel {
                onSuccess(accountData, ::handleAccount)
                onSuccess(logoutData, ::handleLogout)
                onFailure(failureData, ::handleFailure)
            }

            friendsViewModel = viewModel {
                onSuccess(addFriendData, ::handleAddFriend)
                onSuccess(friendRequestsData, ::handleFriendRequests)
                onFailure(failureData, ::handleFailure)
            }

            supportActionBar?.setHomeAsUpIndicator(R.drawable.menu)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            supportFragmentManager.beginTransaction()
                .replace(R.id.requestContainer, FriendRequestsFragment()).commit()

            val type: String? = intent.getStringExtra("type")
            when (type) {
                NotificationHelper.TYPE_ADD_FRIEND -> {
                    openDrawer()
                    friendsViewModel.getFriendRequests()
                    binding.navigation.requestContainer.visibility = View.VISIBLE
                }
                NotificationHelper.TYPE_SEND_MESSAGE -> {
                    val contactId = intent.getLongExtra(ApiService.PARAM_CONTACT_ID, 0L)
                    val contactName = intent.getStringExtra(ApiService.PARAM_NAME)
                    navigator.showChatWithContact(contactId, contactName, this)
                }
            }

            binding.navigation.btnLogout.setOnClickListener {
                accountViewModel.logout()
            }

            binding.navigation.btnChats.setOnClickListener {
                replaceFragment(ChatsFragment())
                closeDrawer()
            }

            binding.navigation.btnAddFriend.setOnClickListener {
                if (binding.navigation.containerAddFriend.visibility == View.VISIBLE) {
                    binding.navigation.containerAddFriend.visibility = View.GONE
                } else {
                    binding.navigation.containerAddFriend.visibility = View.VISIBLE
                }
            }

            binding.navigation.btnAdd.setOnClickListener {
                hideSoftKeyboard()
                showProgress()
                friendsViewModel.addFriend(binding.navigation.etEmail.text.toString())
            }

            binding.navigation.btnFriends.setOnClickListener {
                replaceFragment(FriendsFragment())
                closeDrawer()
            }

            binding.navigation.btnRequests.setOnClickListener {
                friendsViewModel.getFriendRequests(true)

                if (binding.navigation.requestContainer.visibility == View.VISIBLE) {
                    binding.navigation.requestContainer.visibility = View.GONE
                } else {
                    binding.navigation.requestContainer.visibility = View.VISIBLE
                }
            }

            binding.navigation.profileContainer.setOnClickListener {
                navigator.showAccount(this)
                Handler(Looper.getMainLooper()).postDelayed({
                    closeDrawer()
                }, 200)
            }
        }

        override fun onOptionsItemSelected(item: MenuItem?): Boolean {
            when (item?.itemId) {
                android.R.id.home -> {
                    if (binding.drawerLayout.isDrawerOpen(binding.navigation.navigationView)) {
                        binding.drawerLayout.closeDrawer(binding.navigation.navigationView)
                    } else {
                        binding.drawerLayout.openDrawer(binding.navigation.navigationView)
                    }
                }
            }

            return super.onOptionsItemSelected(item)
        }

        override fun onResume() {
            super.onResume()
            accountViewModel.getAccount()
            accountViewModel.updateLastSeen()
        }

        private fun openDrawer() {
            hideSoftKeyboard()
            binding.drawerLayout.openDrawer(binding.navigation.navigationView)
        }

        private fun closeDrawer(animate: Boolean = true) {
            hideSoftKeyboard()
            binding.drawerLayout.closeDrawer(binding.navigation.navigationView, animate)
        }

        private fun handleAccount(accountEntity: AccountEntity?) {
            accountEntity?.let {
                binding.navigation.account = it
            }
        }

        private fun handleLogout(none: None?) {
            navigator.showLogin(this)
            finish()
        }

        private fun handleAddFriend(none: None?) {
            binding.navigation.etEmail.text.clear()
            binding.navigation.containerAddFriend.visibility = View.GONE

            hideProgress()
            showMessage("Запрос отправлен.")
        }

        private fun handleFriendRequests(requests: List<FriendEntity>?) {
            if (requests?.isEmpty() == true) {
                binding.navigation.requestContainer.visibility = View.GONE
                if (binding.drawerLayout.isDrawerOpen(binding.navigation.navigationView)) {
                    showMessage("Нет входящих приглашений.")
                }
            }
        }

        override fun handleFailure(failure: Failure?) {
            hideProgress()
            when (failure) {
                Failure.ContactNotFoundError -> navigator.showEmailNotFoundDialog(
                    this,
                    binding.navigation.etEmail.text.toString()
                )
                else -> super.handleFailure(failure)
            }
        }


        override fun onBackPressed() {
            if (binding.drawerLayout.isDrawerOpen(binding.navigation.navigationView)) {
                hideSoftKeyboard()
                binding.drawerLayout.closeDrawer(binding.navigation.navigationView)
            } else {
                super.onBackPressed()
            }
        }
    }
