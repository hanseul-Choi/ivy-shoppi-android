package com.shoppi.app

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson
import org.json.JSONObject

class HomeFragment : Fragment() {

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

//        val button = view.findViewById<Button>(R.id.btn_enter_product_detail)
//
//        // abstract의 method가 하나만 존재할 때(Single Abstract Method) => lambda로 변경 가능
//        button.setOnClickListener {
////            // transaction : Fragment를 추가, 삭제, 수정을 요청하는 것
////            val transaction = parentFragmentManager.beginTransaction()
////            transaction.add(R.id.container_main, ProductDetailFragment())
////            transaction.commit() // transaction이 실행되기 위해서는 마지막에 commit해주어야함
//
//            // action의 id를 넣어 네비게이션 동작
//            findNavController().navigate(R.id.action_home_to_product_detail)
//        }


        val toolbarTitle = view.findViewById<TextView>(R.id.toolbar_home_title)
        val toolbarIcon = view.findViewById<ImageView>(R.id.toolbar_home_icon)
        val viewpager = view.findViewById<ViewPager2>(R.id.viewpager_home_banner)
        val viewpagerIndicator = view.findViewById<TabLayout>(R.id.viewpager_home_banner_indicator)

        val assetLoader = AssetLoader()
        // requireContext() : 항상 non-null의 context를 return
        val homeJsonString = assetLoader.getJsonString(requireContext(), "home.json")
        Log.d("homeData", homeJsonString ?: "")

        if (!homeJsonString.isNullOrEmpty()) {
            // use gson
            val gson = Gson()
            val homeData = gson.fromJson(homeJsonString, HomeData::class.java)
            homeData.title

            toolbarTitle.text = homeData.title.text
            GlideApp.with(this)
                .load(homeData.title.iconUrl)
                .into(toolbarIcon)

//            // gson을 사용하기 전에는 수많은 보일러 플레이트 코드가 존재

//            val jsonObject = JSONObject(homeJsonString)
//            val title = jsonObject.getJSONObject("title")
//            val text = title.getString("text")
//            val iconUrl = title.getString("icon_url")
//            val topBanners = jsonObject.getJSONArray("top_banners")
//            val size = topBanners.length()

//            for (index in 0 until size) {
//                val bannerObject = topBanners.getJSONObject(index)
//                val backgroundImageUrl = bannerObject.getString("background_image_url")
//                val badgeObject = bannerObject.getJSONObject("badge")
//                val badgeLabel = badgeObject.getString("label")
//                val badgeBackgroundColor = badgeObject.getString("background_color")
//                val bannerBadge = BannerBadge(badgeLabel, badgeBackgroundColor)
//
//                val banner = Banner(
//                    backgroundImageUrl,
//                    bannerBadge,
//                    bannerlabel,
//                    bannerProductDetail
//                )
//            }

            // ListAdapter에 data를 전달하기 위해 submitList 메소드를 이용
            viewpager.adapter = HomeBannerAdapter().apply {
                submitList(homeData.topBanners)
            }

            // dp를 pixel로 바꾸기위해서 사용, res/dimens 로 전달
            val pageWidth = resources.getDimension(R.dimen.viewpager_item_width)
            val pageMargin = resources.getDimension(R.dimen.viewpager_item_margin)
            val screenWidth = resources.displayMetrics.widthPixels
            val offset = screenWidth - pageWidth - pageMargin

            viewpager.offscreenPageLimit = 3
            viewpager.setPageTransformer { page, position ->
                page.translationX = position * -offset
            }

            // lambda : 특정 위치에서 tab의 style을 변경할 때, 사용
            TabLayoutMediator(viewpagerIndicator, viewpager) { tab, position ->

            }.attach()
        }
    }
}