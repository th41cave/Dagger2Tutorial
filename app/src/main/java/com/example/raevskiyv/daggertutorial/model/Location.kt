package com.example.raevskiyv.daggertutorial.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import org.apache.commons.lang3.builder.ToStringBuilder

class Location {

    @SerializedName("street")
    @Expose
    var street: String? = null
    @SerializedName("city")
    @Expose
    var city: String? = null
    @SerializedName("state")
    @Expose
    var state: String? = null
    @SerializedName("postcode")
    @Expose
    var postcode: Int? = null

    override fun toString(): String {
        return ToStringBuilder(this).append("street", street).append("city", city).append("state", state).append("postcode", postcode).toString()
    }

}
