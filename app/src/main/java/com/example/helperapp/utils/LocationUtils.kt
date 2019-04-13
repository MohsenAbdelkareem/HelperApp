package com.example.helperapp.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.IntentSender
import android.location.Address
import android.location.Geocoder
import android.location.Location

import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.LocationSettingsStatusCodes

import java.io.IOException
import java.util.Locale

object LocationUtils {

    const val REQUEST_CHECK_SETTINGS = 503

    @SuppressLint("MissingPermission")
    fun displayLocationSettingsRequest(context: Context, locationListener: LocationListener) {

        val mFusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        val locationRequest = LocationRequest.create()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 10000
        locationRequest.fastestInterval = 10000 / 2

        val builder = LocationSettingsRequest.Builder().addLocationRequest(locationRequest)
        builder.setAlwaysShow(true)

        val result = LocationServices.getSettingsClient(context).checkLocationSettings(builder.build())

        val mLocationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                if (locationResult == null) {
                    locationListener.onLocationError()
                    return
                }
                for (location in locationResult.locations) {
                    // Update UI with location data
                    // ...
                    locationListener.onLocationChange(location)
                }
            }
        }


        result.addOnCompleteListener { task ->
            try {
                val response = task.getResult(ApiException::class.java)!!
                response.locationSettingsStates


                mFusedLocationClient.requestLocationUpdates(locationRequest,
                        mLocationCallback, null)

                mFusedLocationClient.lastLocation
                        .addOnSuccessListener(context as Activity) { location ->
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {
                                mFusedLocationClient.removeLocationUpdates(mLocationCallback)
                                locationListener.onLocationChange(location)
                            } else {
                                locationListener.onLocationError()
                            }
                        }

            } catch (exception: ApiException) {
                when (exception.statusCode) {
                    LocationSettingsStatusCodes.RESOLUTION_REQUIRED ->
                        // Location settings are not satisfied. But could be fixed by showing the
                        // user a dialog.
                        try {
                            // Cast to a resolvable exception.
                            val resolvable = exception as ResolvableApiException
                            // Show the dialog by calling startResolutionForResult(),
                            // and check the result in onActivityResult().
                            resolvable.startResolutionForResult(
                                    context as Activity,
                                    REQUEST_CHECK_SETTINGS)
                        } catch (e: IntentSender.SendIntentException) {
                            // Ignore the error.
                            locationListener.onLocationError()
                        } catch (e: ClassCastException) {
                            locationListener.onLocationError()
                        }

                    LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE ->
                        // Location settings are not satisfied. However, we have no way to fix the
                        // settings so we won't show the dialog.
                        locationListener.onLocationError()
                }
            }
        }
    }

    fun getAddressFromLocation(context: Context, location: Location): String? {

        val geoCoder = Geocoder(context, Locale.getDefault())
        val addresses: List<Address>
        var address: String? = null
        try {
            addresses = geoCoder.getFromLocation(location.latitude, location.longitude, 1)
            address = addresses[0].getAddressLine(0)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return address
    }

    interface LocationListener {

        fun onLocationChange(location: Location?)

        fun onLocationError()
    }

}
