package com.example.raevskiyv.daggertutorial.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import org.apache.commons.lang3.builder.ToStringBuilder

class Info {

    @SerializedName("seed")
    @Expose
    var seed: String? = null
    @SerializedName("results")
    @Expose
    var results: Int? = null
    @SerializedName("page")
    @Expose
    var page: Int? = null
    @SerializedName("version")
    @Expose
    var version: String? = null

    override fun toString(): String {
        return ToStringBuilder(this).append("seed", seed).append("results", results).append("page", page).append("version", version).toString()
    }

}
