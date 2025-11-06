package com.sxit.campus_link.model

data class User(
    val id: Long? = null,
    val username: String,
    val password: String,
    val Department: String,
    val Major: String,
    val Introduction: String,
    val email: String? = null
)
