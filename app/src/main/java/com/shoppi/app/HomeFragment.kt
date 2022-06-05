package com.shoppi.app

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import org.json.JSONObject
import org.w3c.dom.Text

class HomeFragment: Fragment() {

    // onCreateView: inflate하기 위한 callback method
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // attachToRoot : 바로 루트뷰에 추가할 것인가?
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    // (parameter)view : onCreateView에서 return된 view를 가져옴
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button = view.findViewById<Button>(R.id.btn_enter_product_detail)

        // abstract의 method가 하나만 존재할 때(Single Abstract Method) => lambda로 변경 가능
        button.setOnClickListener {
//            // transaction : Fragment를 추가, 삭제, 수정을 요청하는 것
//            val transaction = parentFragmentManager.beginTransaction()
//            transaction.add(R.id.container_main, ProductDetailFragment())
//            transaction.commit() // transaction이 실행되기 위해서는 마지막에 commit해주어야함

            // action의 id를 넣어 네비게이션 동작
            findNavController().navigate(R.id.action_home_to_product_detail)
        }

        val assetLoader = AssetLoader()
        // requireContext() : 항상 non-null의 context를 return
        val homeData = assetLoader.getJsonString(requireContext(), "home.json")
        Log.d("homeData", homeData ?: "")

        if(!homeData.isNullOrEmpty()) {
            val jsonObject = JSONObject(homeData)
            val title = jsonObject.getJSONObject("title")
            val text = title.getString("text")
            val iconUrl = title.getString("icon_url")

            val titleValue = Title(text, iconUrl)

            // appbar title
            val appbarTextView = view.findViewById<TextView>(R.id.tv_fragment_home_appbar)
            appbarTextView.text = titleValue.text

            // appbar icon
            val appbarIcon = view.findViewById<ImageView>(R.id.iv_fragment_home_appbar_icon)

            Log.d("icon url", titleValue.iconUrl)

            Glide.with(this)
                .load(titleValue.iconUrl)
                .into(appbarIcon)
        }
    }
}