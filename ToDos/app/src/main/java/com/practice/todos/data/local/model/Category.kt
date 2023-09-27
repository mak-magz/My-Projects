package com.practice.todos.data.local.model

import org.mongodb.kbson.ObjectId
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.Index
import io.realm.kotlin.types.annotations.PrimaryKey

class Category: RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()

    @Index
    var title: String = ""
}