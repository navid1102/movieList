package com.navid.moviescore

enum class UrlType {
    Public
}

object BaseUri {
    var baseHost = "api.themoviedb.org/"//baseUrl
    var baseUrl = "https://${baseHost}3/discover/movie"//baseUrl
    val TMDB_IMAGEURL = "https://image.tmdb.org/t/p/w500/"

}

class Server {
    companion object {
        fun setServerType(urlType: UrlType) {

            when (urlType) {

                UrlType.Public -> {
                    BaseUri.baseHost = "api.themoviedb.org/"
                    BaseUri.baseUrl = "https://${BaseUri.baseHost}"//baseUrl
                }
            }

        }
    }


}