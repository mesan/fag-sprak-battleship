import net.yested.AjaxRequest
import net.yested.ajaxGet
import net.yested.ajaxPost

public fun <RESULT> put(url:String, data:String, success: ((RESULT) -> Unit)) : Unit {
    ajaxPost(AjaxRequest(url, "PUT", data, "application/json; charset=utf-8", "json", success))
}

public fun <RESULT> post(url:String, data:String, success: ((RESULT) -> Unit)) : Unit {
    ajaxPost(AjaxRequest(url, "POST", data, "application/json; charset=utf-8", "json", success))
}

public fun <RESULT> get(url:String, loaded:(response:RESULT) -> Unit) : Unit {
    ajaxGet(url, loaded)
}