package com.example.raevskiyv.daggertutorial.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import org.apache.commons.lang3.builder.ToStringBuilder

class Name {

    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("first")
    @Expose
    var first: String? = null
    @SerializedName("last")
    @Expose
    var last: String? = null

    override fun toString(): String {
        return ToStringBuilder(this).append("title", title).append("first", first).append("last", last).toString()
    }

}
