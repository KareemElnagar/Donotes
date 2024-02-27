package com.kareem.donotes.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Notes")
data class Notes(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,

    @ColumnInfo(name = "title")
    var title: String? = null,

    @ColumnInfo(name = "date_time")
    var dateTime: String? = null,

    @ColumnInfo(name = "note_text")
    var noteText: String? = null,

    @ColumnInfo(name = "img_path")
    var imgPath: String? = null,

    @ColumnInfo(name = "web_link")
    var link: String? = null,

    @ColumnInfo(name = "color")
    var color: String? = null,

    )