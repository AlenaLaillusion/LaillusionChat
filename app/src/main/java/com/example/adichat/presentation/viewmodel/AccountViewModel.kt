package com.example.adichat.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.adichat.domain.account.*
import com.example.adichat.domain.type.None
import javax.inject.Inject

class AccountViewModel @Inject constructor(
    val registerUseCase: Register,
    val loginUseCase: Login,  //для авторизации
    val getAccountUseCase: GetAccount, //для получения акк
    val logoutUseCase: Logout, //для выполнения входа в акк
    val editAccountUseCase: EditAccount,
    val updateLastSeenUseCase: UpdateLastSeen,
    val forgetPasswordUseCase: ForgetPassword
) : BaseViewModel() {
    var registerData : MutableLiveData<None> = MutableLiveData()
    var accountData : MutableLiveData<AccountEntity> = MutableLiveData() //хранит данные акк
    var editAccountData: MutableLiveData<AccountEntity> = MutableLiveData()
    var logoutData : MutableLiveData<None> = MutableLiveData() //хранит состояние
    var forgetPasswordData: MutableLiveData<None> = MutableLiveData()

    fun register(email: String, name: String, password: String) {
        registerUseCase(Register.Params(email, name, password)) { it.either(::handleFailure, ::handleRegister)}
    }
    fun login(email: String, password: String){ //выполняет авторизацию
        loginUseCase(Login.Params(email,password)) {
            it.either(::handleFailure, ::handleAccount)
        }
    }

    fun forgetPassword(email: String) {
        forgetPasswordUseCase(ForgetPassword.Params(email)) { it.either(::handleFailure, ::handleForgetPassword)}
    }

    fun getAccount() { //получает тек. акк
        getAccountUseCase(None()) { it.either(::handleFailure, ::handleAccount) }
    }

    fun logout() {  //выполняет выход из акк
        logoutUseCase(None()) { it.either(::handleFailure, ::handleLogout) }
    }

    fun editAccount(entity: AccountEntity) {
        editAccountUseCase(entity) { it.either(::handleFailure, ::handleEditAccount) }
    }

    fun updateLastSeen() {
        updateLastSeenUseCase(None()) { it.either(::handleFailure) {} }
    }

    private fun handleRegister(none: None) {
        this.registerData.value = none
    }

    private fun handleAccount(account: AccountEntity) { //сеттер присваивает данные акк
        this.accountData.value = account
    }

    private fun handleEditAccount(account: AccountEntity) {
        this.editAccountData.value = account
    }

    private fun handleLogout(none: None) { //сеттер присваивает состояние логаута
        this.logoutData.value = none
    }

    private fun handleForgetPassword(none: None) {
        this.forgetPasswordData.value = none
    }

    override fun onCleared() {
        super.onCleared()
        registerUseCase.unsubscribe()
        loginUseCase.unsubscribe()
        getAccountUseCase.unsubscribe()
        logoutUseCase.unsubscribe()
        updateLastSeenUseCase.unsubscribe()
        forgetPasswordUseCase.unsubscribe()
    }
}