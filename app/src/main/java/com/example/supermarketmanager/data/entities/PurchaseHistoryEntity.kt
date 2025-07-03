package com.example.supermarketmanager.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters

@Entity(tableName = "purchase_history")
@TypeConverters(Converters::class)
data class PurchaseHistoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val timestamp: Long,
    val totalCost: Double,
    val productIds: List<Int>,
    val prices: List<Double>,
    val quantities: List<Int>
)

class Converters {

    @TypeConverter
    fun fromIntList(list: List<Int>): String =
        list.joinToString(separator = ",")

    @TypeConverter
    fun toIntList(csv: String): List<Int> =
        if (csv.isBlank()) emptyList() else csv.split(",").map { it.toInt() }

    @TypeConverter
    fun fromDoubleList(list: List<Double>): String =
        list.joinToString(separator = ",")

    @TypeConverter
    fun toDoubleList(csv: String): List<Double> =
        if (csv.isBlank()) emptyList() else csv.split(",").map { it.toDouble() }
}
