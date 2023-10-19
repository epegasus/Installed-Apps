package dev.pegasus.installedapps.dataClasses

import android.graphics.drawable.Drawable

/**
 * @Author: SOHAIB AHMED
 * @Date: 19,October,2023.
 * @Accounts
 *      -> https://github.com/epegasus
 *      -> https://linkedin.com/in/epegasus
 */

data class AppItem(
    val id: Int,
    val icon: Drawable,
    val appName: String,
    val packageName: String
)
