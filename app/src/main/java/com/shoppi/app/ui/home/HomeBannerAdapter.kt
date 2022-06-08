package com.shoppi.app.ui.home

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shoppi.app.model.Banner
import com.shoppi.app.GlideApp
import com.shoppi.app.R
import com.shoppi.app.databinding.ItemHomeBannerBinding
import java.text.DecimalFormat
import kotlin.math.roundToInt

// ListAdapter : data의 List로 받아서 0번째부터 순차적으로 binding함, inflate한 뷰에 그대로 데이터만 변경하여 이점을 얻음
class HomeBannerAdapter(private val viewModel: HomeViewModel) : ListAdapter<Banner, HomeBannerAdapter.HomeBannerViewHolder>(
    BannerDiffCallback()
) {
    private lateinit var binding: ItemHomeBannerBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeBannerViewHolder {
        binding = ItemHomeBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return HomeBannerViewHolder(binding)
    }

    // holder : onCreateViewHolder에서 잘 생성한 holder가 전달
    override fun onBindViewHolder(holder: HomeBannerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    // view? : HomeBanner에서 inflate시킬 뷰
    inner class HomeBannerViewHolder(private val binding: ItemHomeBannerBinding) : RecyclerView.ViewHolder(binding.root) {

        // viewHolder에서 할당받은 view는 itemView로 참조가 가능
        fun bind(banner: Banner) {
            binding.banner = banner
            binding.viewModel = viewModel
            // 데이터를 바로 바인딩시키는 메소드
            binding.executePendingBindings()
        }
    }
}

class BannerDiffCallback: DiffUtil.ItemCallback<Banner>() {

    // item이 같은지
    override fun areItemsTheSame(oldItem: Banner, newItem: Banner): Boolean {
        return oldItem.productDetail.productId == newItem.productDetail.productId
    }

    // 모두 같은지
    override fun areContentsTheSame(oldItem: Banner, newItem: Banner): Boolean {
        return oldItem == newItem
    }

}