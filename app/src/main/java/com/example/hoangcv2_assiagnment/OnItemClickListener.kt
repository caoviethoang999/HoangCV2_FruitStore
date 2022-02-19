package com.example.hoangcv2_assiagnment

enum class Status {
    DETAIL, CATEGORY,ADD_TO_CART,DELETE_ITEM_CART
}

interface OnItemClickListener {
    fun onItemClick(position: Int, status: Status)
}