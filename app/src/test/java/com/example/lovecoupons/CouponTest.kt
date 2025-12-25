package com.example.lovecoupons

import org.junit.Assert.assertTrue
import org.junit.Test

class CouponTest {

    @Test
    fun couponsListIsNotEmpty() {
        val coupons = listOf(
            "1/2 Hour Back Massage",
            "Unlimited Cuddling Session"
        )
        assertTrue(coupons.isNotEmpty())
    }
}