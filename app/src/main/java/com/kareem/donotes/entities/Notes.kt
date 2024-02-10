package com.kareem.donotes.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "Notes")
data class Notes(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "sub_title")
    val subTitle: String,

    @ColumnInfo(name = "date_time")
    val dateTime: String,

    @ColumnInfo(name = "note_text")
    val noteText: String,

    @ColumnInfo(name = "img_path")
    val imgPath: String,

    @ColumnInfo(name = "web_link")
    val link: String,

    @ColumnInfo(name = "color")
    val color: String,
) : Serializable {
    override fun toString(): String {
        return "$title : $dateTime"
    }

}