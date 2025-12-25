package com.example.lovecoupons

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CouponAdapter(
    private val coupons: List<String>,
    private val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<CouponAdapter.CouponViewHolder>() {

    class CouponViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val couponText: TextView = view.findViewById(R.id.couponText)
        val heartIcon: ImageView = view.findViewById(R.id.heartIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CouponViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_coupon, parent, false)
        return CouponViewHolder(view)
    }

    override fun onBindViewHolder(holder: CouponViewHolder, position: Int) {
        val coupon = coupons[position]
        holder.couponText.text = coupon
        holder.itemView.setOnClickListener {
            val pulseAnim = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.pulse)
            holder.heartIcon.startAnimation(pulseAnim)
            onItemClick(coupon)
        }
    }

    override fun getItemCount() = coupons.size
}