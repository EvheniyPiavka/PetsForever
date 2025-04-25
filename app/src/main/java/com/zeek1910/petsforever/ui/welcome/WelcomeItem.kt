package com.zeek1910.petsforever.ui.welcome

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class WelcomeItem(
    @StringRes val title: Int,
    @StringRes val description: Int,
    @DrawableRes val image: Int,
)