package com.example.frontendfreelan.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Task(
    val name: String,
    val details: String,
    val date: String,
    val time: String,
    val value: String
) : Parcelable
