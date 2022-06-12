package com.shoppi.app.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.shoppi.app.*
import com.shoppi.app.common.KEY_PRODUCT_ID
import com.shoppi.app.databinding.FragmentHomeBinding
import com.shoppi.app.ui.common.*

class HomeFragment : Fragment(), ProductClickListener {

    private val viewModel: HomeViewModel by viewModels {
        ViewModelFactory(requireContext())
    }
    private lateinit var binding: FragmentHomeBinding

    // onCreateView: inflate하기 위한 callback method
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        // attachToRoot : 바로 루트뷰에 추가할 것인가?
        return binding.root
    }

    // (parameter)view : onCreateView에서 return된 view를 가져옴
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // binding 작업
        binding.lifecycleOwner = viewLifecycleOwner

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

//        val assetLoader = AssetLoader()
//        // requireContext() : 항상 non-null의 context를 return
//        val homeJsonString = assetLoader.getJsonString(requireContext(), "home.json")
//        Log.d("homeData", homeJsonString ?: "")

//
//        // use gson
//        val gson = Gson()
//        val homeData = gson.fromJson(homeJsonString, HomeData::class.java)

        // viewLifecycleOwner : lifecycle이 변경됨에 따라 현재 객체 상태를 알고 있는 것
        setToolbar()

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

        setTopBanners()

        setPromotions()
        setNavigation()
    }

    private fun setNavigation() {
        viewModel.openProductEvent.observe(viewLifecycleOwner, EventObserver {
            openProductDetail(it)
        })
    }

    private fun openProductDetail(productId: String) {
        findNavController().navigate(
            R.id.action_home_to_product_detail,
            bundleOf(
                KEY_PRODUCT_ID to productId
            )
        )
    }

    private fun setToolbar() {
        viewModel.title.observe(viewLifecycleOwner) { title ->
            binding.title = title
        }
    }

    private fun setPromotions() {
        val titleAdapter = SectionTitleAdapter()
        val promotionAdapter = ProductPromotionAdapter(this)
        binding.rvSpecialItems.adapter = ConcatAdapter(titleAdapter, promotionAdapter)
        viewModel.promotions.observe(viewLifecycleOwner) { promotions ->
            titleAdapter.submitList(listOf(promotions.title))
            promotionAdapter.submitList(promotions.items)
        }
    }

    private fun setTopBanners() {

        // viewpager가 반복되므로 with 블럭을 이용하여 코드 개선
        with(binding.viewpagerHomeBanner) {
            adapter = HomeBannerAdapter(viewModel).apply {
                viewModel.topBanners.observe(viewLifecycleOwner) { banners ->
                    submitList(banners)
                }
            }
            // dp를 pixel로 바꾸기위해서 사용, res/dimens 로 전달
            val pageWidth = resources.getDimension(R.dimen.viewpager_item_width)
            val pageMargin = resources.getDimension(R.dimen.viewpager_item_margin)
            val screenWidth = resources.displayMetrics.widthPixels
            val offset = screenWidth - pageWidth - pageMargin

            offscreenPageLimit = 3
            setPageTransformer { page, position ->
                page.translationX = position * -offset
            }

            // lambda : 특정 위치에서 tab의 style을 변경할 때, 사용
            TabLayoutMediator(binding.viewpagerHomeBannerIndicator, this) { tab, position ->

            }.attach()
        }
    }

    // ProductClickListener
    override fun onProductClick(productId: String) {
        findNavController().navigate(
            R.id.action_home_to_product_detail,
            bundleOf(
                KEY_PRODUCT_ID to "desk-1"
            )
        )
    }
}