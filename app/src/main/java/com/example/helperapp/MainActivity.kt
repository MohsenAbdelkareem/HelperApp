package com.example.helperapp

import com.example.helperapp.base.BaseActivity
import com.example.helperapp.data.network.*
import com.example.helperapp.data.network.RetroClient.CHEF_API_SERVICE
import com.example.helperapp.utils.extentions.loadCircleImageGlide
import com.example.helperapp.utils.extentions.loadImageGlide
import com.example.helperapp.utils.extentions.loadRoundCornerImageGlide
import io.reactivex.Single
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Response

class MainActivity : BaseActivity() , RxCallbacks.SingleListener{
    override fun onSuccess(call: Single<Response<BaseResponse>>, response: BaseResponse, requestType: RequestType) {

    }

    override fun onFailure(call: Single<Response<BaseResponse>>, requestType: RequestType) {
    }

    override fun contentView(): Int {
     return R.layout.activity_main
    }

    override fun setUp() {

        RxRequest.callApi(CHEF_API_SERVICE.chefResquest(), this, RequestType.LOGIN)

        imageView.loadCircleImageGlide("http://35.164.33.13//shefz//storage//web//source//thump_dish//MYYQHq5E9t-Q1KWlm5az9NLDuZO2FPhU.jpg")
        imageView1.loadRoundCornerImageGlide("http://35.164.33.13//shefz//storage//web//source//thump_dish//j5zuX6lAVlVgSR06ztMROSVCAYFrJQ-u.jpg")
        imageView2.loadImageGlide("http://35.164.33.13//shefz//storage//web//source//thump_dish//MYYQHq5E9t-Q1KWlm5az9NLDuZO2FPhU.jpg")
    }

}
