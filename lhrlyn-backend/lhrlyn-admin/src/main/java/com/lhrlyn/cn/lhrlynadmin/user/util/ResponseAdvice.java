package com.lhrlyn.cn.lhrlynadmin.user.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *  自动拦截返回类
 * @author lhr
 * @date  2021/12/29 10:25 PM
 * @param
 * @return:
 */
@RestControllerAdvice
public class ResponseAdvice  {
    // public class ResponseAdvice implements ResponseBodyAdvice<Object> {

//    @Autowired
//    private ObjectMapper objectMapper;
//
//
//    @Override
//    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
//        return true;
//    }
//
//
//    @SneakyThrows
//    @Override
//    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
//        if(o instanceof String){
//            return objectMapper.writeValueAsString(ResultData.success(o));
//        }
//        if (o instanceof ResultData) {
//            return o;
//        }
//        return ResultData.success(o);
//    }

}
