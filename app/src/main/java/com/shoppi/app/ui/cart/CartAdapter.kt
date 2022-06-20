package com.shoppi.app.ui.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shoppi.app.databinding.ItemCartSectionBinding
import com.shoppi.app.databinding.ItemCartSectionHeaderBinding
import com.shoppi.app.model.CartHeader
import com.shoppi.app.model.CartItem
import com.shoppi.app.model.CartProduct

private const val VIEW_TYPE_HEADER = 0
private const val VIEW_TYPE_ITEM = 1

// ViewHolder의 type이 두개이므로 원형 클래스인 RecyclerView.ViewHolder를 전달한다.
class CartAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val cartProducts = mutableListOf<CartProduct>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            VIEW_TYPE_HEADER -> HeaderViewHolder(
                ItemCartSectionHeaderBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )
            else -> ItemViewHolder(ItemCartSectionBinding.inflate(inflater, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> {
                val item = cartProducts[position] as CartHeader
                holder.bind(item)
            }
            is ItemViewHolder -> {
                val item = cartProducts[position] as CartItem
                holder.bind(item)
            }
        }
    }

    override fun getItemCount(): Int {
        return cartProducts.size
    }

    // viewType마다 다른 viewHolder를 적용하기 위해
    override fun getItemViewType(position: Int): Int {
        return when (cartProducts[position]) {
            is CartHeader -> VIEW_TYPE_HEADER
            is CartItem -> VIEW_TYPE_ITEM
        }
    }

    // ListAdapter와 다르게 안에 있는 data른 전달해주어야함
    fun submitHeaderAndItemList(items: List<CartItem>) {
        val itemGroups = items.groupBy { it.brandName }
        val products = mutableListOf<CartProduct>()

        // map data 다루기
        itemGroups.entries.forEach { entry ->
            val header = CartHeader(entry.key)
            products.add(header)
            products.addAll(entry.value)
        }

        cartProducts.addAll(products)

        // adapter에 data변경을 알려야함 -> ListAdapter 내부에는 구현이 되었던 부분
        notifyItemRangeInserted(cartProducts.size, products.size)
    }

    class HeaderViewHolder(private val binding: ItemCartSectionHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(header: CartHeader) {
            binding.header = header
            binding.executePendingBindings()
        }
    }

    class ItemViewHolder(private val binding: ItemCartSectionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CartItem) {
            binding.item = item
            binding.executePendingBindings()
        }
    }
}