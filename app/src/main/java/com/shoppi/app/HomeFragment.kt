package com.shoppi.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

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
    }
}