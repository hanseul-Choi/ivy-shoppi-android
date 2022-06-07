package com.shoppi.app.ui.category

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.shoppi.app.R
import com.shoppi.app.common.KEY_CATEGORY_ID
import com.shoppi.app.common.KEY_CATEGORY_LABEL
import com.shoppi.app.databinding.FragmentCategoryBinding
import com.shoppi.app.repository.category.CategoryAdapter
import com.shoppi.app.ui.common.EventObserver
import com.shoppi.app.ui.common.ViewModelFactory

class CategoryFragment : Fragment() {

    private val viewModel: CategoryViewModel by viewModels {
        ViewModelFactory(requireContext())
    }
    private lateinit var binding: FragmentCategoryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoryAdapter = CategoryAdapter(viewModel)
        binding.rvCategoryList.adapter = categoryAdapter
        viewModel.items.observe(viewLifecycleOwner) {
            categoryAdapter.submitList(it)
        }

        // 그냥 openCategoryDetail을 부르게되면 뒤로가기 버튼이 활성화되지 않음
        // 다시 돌아와도 livedata가 계속 호출되기 때문
        // data가 소비되어있는지 확인하기 위해 Event객체를 생성
        viewModel.openCategoryEvent.observe(viewLifecycleOwner, EventObserver {
            openCategoryDetail(it.categoryId, it.label)
        })
    }

    private fun openCategoryDetail(categoryId: String, categoryLabel: String) {
        findNavController().navigate(
            R.id.action_category_to_categoryDetail, bundleOf(
                KEY_CATEGORY_ID to categoryId,
                KEY_CATEGORY_LABEL to categoryLabel
            )
        )
    }
}