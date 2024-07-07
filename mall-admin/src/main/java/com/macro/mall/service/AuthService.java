package com.macro.mall.service;

import com.macro.mall.common.api.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Map;

/**
 * 认证服务远程调用Service
 * Created by macro on 2020/7/19.
 */
public interface AuthService {
    CommonResult getAccessToken(Map<String, String> parameters, Principal userPrincipal) throws HttpRequestMethodNotSupportedException;
}