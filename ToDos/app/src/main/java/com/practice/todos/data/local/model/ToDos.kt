package com.practice.todos.data.local.model

import org.mongodb.kbson.ObjectId
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.Index
import io.realm.kotlin.types.annotations.PrimaryKey

class ToDos: RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()

    var owner_id: String = ""

    @Index
    var title: String = ""
}