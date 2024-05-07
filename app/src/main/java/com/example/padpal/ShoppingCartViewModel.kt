package com.example.padpal

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf

class ShoppingCartViewModel : ViewModel() {
    // Example: Array of items in the shopping cart
    val items = mutableStateOf(arrayOf<Int>(0, 0, 0, 0, 0, 0))

    fun addSmall() {
        val updatedItems = items.value.toMutableList()
        val small=updatedItems[0]
        updatedItems.set(0,small+1)
        items.value = updatedItems.toTypedArray()
    }

    fun addMedium() {
        val updatedItems = items.value.toMutableList()
        val medium=updatedItems[1]
        updatedItems.set(1,medium+1)
        items.value = updatedItems.toTypedArray()
    }

    fun addOvernight() {
        val updatedItems = items.value.toMutableList()
        val overnight=updatedItems[2]
        updatedItems.set(2,overnight+1)
        items.value = updatedItems.toTypedArray()
    }

    fun removeSmall() {
        val updatedItems = items.value.toMutableList()
        val small=updatedItems[0]
        if(small!=0){
        updatedItems.set(0,small-1)}
        items.value = updatedItems.toTypedArray()
    }

    fun removeMedium() {
        val updatedItems = items.value.toMutableList()
        val medium=updatedItems[1]
        if(medium!=0){
            updatedItems.set(1,medium-1)}
        items.value = updatedItems.toTypedArray()
    }

    fun removeOvernight() {
        val updatedItems = items.value.toMutableList()
        val overnight=updatedItems[2]
        if(overnight!=0){
            updatedItems.set(2,overnight-1)}
        items.value = updatedItems.toTypedArray()
    }


}
