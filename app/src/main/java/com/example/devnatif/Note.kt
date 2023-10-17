package com.example.devnatif

class Note {

    //attributs
    private var id: Int = 0
    private var title: String = ""
    private var content: String = ""

    companion object {
        private var lastId = 0
    }

    //constructeur
    constructor(id: Int, title: String, content: String) {
        this.id = id
        this.title = title
        this.content = content
        updateLastId(id)
    }

    //constructeur
    constructor(title: String, content: String) {
        this.id = generateId()
        this.title = title
        this.content = content
    }

    //constructeur
    constructor() {
        this.id = generateId()
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
        updateLastId(id)
    }

    fun setTitle(title: String) {
        this.title = title
    }

    fun setContent(content: String) {
        this.content = content
    }

    private fun generateId(): Int {
        lastId++
        return lastId
    }

    private fun updateLastId(id: Int) {
        if (id > lastId) {
            lastId = id
        }
    }
}