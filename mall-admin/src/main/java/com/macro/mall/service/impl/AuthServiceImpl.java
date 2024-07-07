package com.macro.mall.service.impl;

import com.macro.mall.common.api.CommonResult;
import com.macro.mall.common.constant.AuthConstant;
import com.macro.mall.domain.Oauth2TokenDto;
import com.macro.mall.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import java.security.Principal;
import java.util.Map;

/**
 * @author wangyao
 * @date 2024/7/6 11:55
 */
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private TokenEndpoint tokenEndpoint;
    @Override
    public CommonResult getAccessToken(Map<String, String> parameters, Principal userPrincipal) throws HttpRequestMethodNotSupportedException {
        OAuth2AccessToken oAuth2AccessToken = tokenEndpoint.postAccessToken(userPrincipal, parameters).getBody();
        Oauth2TokenDto oauth2TokenDto = Oauth2TokenDto.builder()
                .token(oAuth2AccessToken.getValue())
                .refreshToken(oAuth2AccessToken.getRefreshToken().getValue())
                .expiresIn(oAuth2AccessToken.getExpiresIn())
                .tokenHead(AuthConstant.JWT_TOKEN_PREFIX).build();

        return CommonResult.success(oauth2TokenDto);
    }
}
