package raul.imashev.utils.textUtils

class TextUtil : ITextUtil {
    override fun getItems(list: List<String>): String {
        var result = ""
        if (list.size > 1) {
            for (i in list) {
                result += "$i ,"
                if (i == list.last()) {
                    result += i
                }
            }
        } else {
            result = list.first()
        }
        return result
    }
}