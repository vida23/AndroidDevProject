package com.example.dimpguide

class LoggedInManager {
    companion object {
        var isLoggedIn : Boolean = false
        fun changeLoginState(state:Boolean){
            isLoggedIn = state
        }
    }

}