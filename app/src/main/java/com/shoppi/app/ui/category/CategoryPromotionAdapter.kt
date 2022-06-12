package com.shoppi.app.ui.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shoppi.app.R
import com.shoppi.app.common.KEY_PRODUCT_ID
import com.shoppi.app.databinding.ItemCategoryPromotionBinding
import com.shoppi.app.model.Product

class CategoryPromotionAdapter(
    private val navController: NavController?
) :
    ListAdapter<Product, CategoryPromotionAdapter.CategoryPromotionViewHolder>(ProductDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryPromotionViewHolder {
        val binding =
            ItemCategoryPromotionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryPromotionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryPromotionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CategoryPromotionViewHolder(private val binding: ItemCategoryPromotionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.product = product

            binding.root.setOnClickListener {
                navController?.navigate(
                    R.id.action_home_to_product_detail,
                    bundleOf(
                        KEY_PRODUCT_ID to "desk-1"
                    )
                )
            }

            binding.executePendingBindings()
        }
    }
}

class ProductDiffCallback : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.productId == newItem.productId
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }

}