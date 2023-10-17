package com.example.devnatif

class Note {

    //attributs
    private var id: Int = 0
    private var title: String = ""
    private var content: String = ""

    //constructeur
    constructor(id: Int, title: String, content: String) {
        this.id = id
        this.title = title
        this.content = content
    }

    //constructeur
    constructor(title: String, content: String) {
        this.title = title
        this.content = content
    }

    //constructeur
    constructor() {
    }

    //getter
    fun getId(): Int {
        return id
    }

    fun getTitle(): String {
        return title
    }

    fun getContent(): String {
        return content
    }

    //setter
    fun setId(id: Int) {
        this.id = id
    }

    fun setTitle(title: String) {
        this.title = title
    }

    fun setContent(content: String) {
        this.content = content
    }


}