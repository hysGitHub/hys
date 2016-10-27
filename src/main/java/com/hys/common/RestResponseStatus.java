package com.hys.common;

/**
 * 响应状态码,移自HttpStatus
 * @date 2016年1月13日 上午10:32:39
 * 
 */
public enum RestResponseStatus
{

    // 2xx Success

    /**
     * 处理成功
     * {@code 200 OK}.
     */
    OK(200, "OK"),
    
 
    /**
     * 无内容
     * {@code 204 No Content}.
     */
    NO_CONTENT(204, "No Content"),
  
    // --- 4xx Client Error ---

    /**
     * 无效的请求，一般参数错误，提交的数据格式错误等
     * {@code 400 Bad Request}.
     */
    BAD_REQUEST(400, "Bad Request"),
    
    /**
     * 未认证，需要先登录认证
     * {@code 401 Unauthorized}.
     */
    UNAUTHORIZED(401, "Unauthorized"),

    /**
     * 无访问权限，用户已经登录，但是没有访问此功能的权限
     * {@code 403 Forbidden}.
     */
    FORBIDDEN(403, "Forbidden"),
    
    /**
     * 未发现，无，未找到
     * {@code 404 Not Found}.
     */
    NOT_FOUND(404, "Not Found"),
    
 
    TOO_MANY_REQUESTS(429, "Too Many Requests"),
    
    /**
     * 数据冲突，已经存面相同的数据
     */
    CONFLICT(409,"CONFLICT"),
    
    // --- 5xx Server Error ---
    /**
     * 服务器内部错误
     * {@code 500 服务器内部错误}.
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.6.1">HTTP/1.1: Semantics and Content, section 6.6.1</a>
     */
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
  
    /**
     * 错误的地址
     * {@code 502 Bad Gateway}.
     */
    BAD_GATEWAY(502, "Bad Gateway"),
    
    /**
     * 服务不可用
     * {@code 503 Service Unavailable}.
     */
    SERVICE_UNAVAILABLE(503, "Service Unavailable");
    

    private final int code;

    private final String reason;


    private RestResponseStatus(int code, String reason) {
        this.code = code;
        this.reason = reason;
    }

    /**
     * Return the integer value of this status code.
     */
    public int code() {
        return this.code;
    }
    
    @Override
    public String toString(){
        return Integer.toString(code);
    }


    public String getReason() {
        return reason;
    }

}
