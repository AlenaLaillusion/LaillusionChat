package com.example.adichat.ui.friends

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.example.adichat.R
import com.example.adichat.domain.friends.FriendEntity
import com.example.adichat.domain.type.None
import com.example.adichat.presentation.viewmodel.FriendsViewModel
import com.example.adichat.ui.App
import com.example.adichat.ui.core.BaseListFragment
import com.example.adichat.ui.core.ext.onFailure
import com.example.adichat.ui.core.ext.onSuccess

class FriendsFragment : BaseListFragment() {
    override val viewAdapter = FriendsAdapter()

    lateinit var friendsViewModel: FriendsViewModel

    override val titleToolbar = R.string.screen_friends

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        friendsViewModel = viewModel {
            onSuccess(friendsData, ::handleFriends)
            onSuccess(deleteFriendData, ::handleDeleteFriend)
            onSuccess(progressData, ::updateProgress)
            onFailure(failureData, ::handleFailure)
        }

        setOnItemClickListener { it, v ->
            (it as? FriendEntity)?.let {
                when (v.id) {
                    R.id.btnRemove -> showDeleteFriendDialog(it)
                    else -> {
                        navigator.showUser(requireActivity(), it)
                    }
                }
            }
        }

        friendsViewModel.getFriends()
    }


    private fun showDeleteFriendDialog(entity: FriendEntity) {
        activity?.let {
            AlertDialog.Builder(it)
                .setMessage(getString(R.string.delete_friend))

                .setPositiveButton(android.R.string.yes) { dialog, which ->
                    friendsViewModel.deleteFriend(entity)
                }

                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show()
        }
    }


    private fun handleFriends(friends: List<FriendEntity>?) {
        hideProgress()
        if (friends != null && friends.isNotEmpty()) {
            viewAdapter.submitList(friends)
        }
    }

    private fun handleDeleteFriend(none: None?) {
        friendsViewModel.getFriends()
    }
}