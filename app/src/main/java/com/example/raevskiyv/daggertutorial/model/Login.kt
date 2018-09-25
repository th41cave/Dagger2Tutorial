package com.example.raevskiyv.daggertutorial.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import org.apache.commons.lang3.builder.ToStringBuilder

class Login {

    @SerializedName("username")
    @Expose
    var username: String? = null
    @SerializedName("password")
    @Expose
    var password: String? = null
    @SerializedName("salt")
    @Expose
    var salt: String? = null
    @SerializedName("md5")
    @Expose
    var md5: String? = null
    @SerializedName("sha1")
    @Expose
    var sha1: String? = null
    @SerializedName("sha256")
    @Expose
    var sha256: String? = null

    override fun toString(): String {
        return ToStringBuilder(this).append("username", username).append("password", password).append("salt", salt).append("md5", md5).append("sha1", sha1).append("sha256", sha256).toString()
    }

}
