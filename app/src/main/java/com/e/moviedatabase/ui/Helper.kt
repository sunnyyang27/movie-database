package com.e.moviedatabase.ui

class Helper {
    companion object {
        fun getImagePath(relativePath: String) : String {
            return "https://image.tmdb.org/t/p/w500$relativePath"
        }
    }
}