package raul.imashev.utils.imageUtils

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}