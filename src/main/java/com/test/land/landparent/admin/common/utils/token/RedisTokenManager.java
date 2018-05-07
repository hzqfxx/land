package com.test.land.landparent.admin.common.utils.token;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.test.land.landparent.admin.common.constants.Constants;
import com.test.land.landparent.admin.common.utils.AssertUtils;
import com.test.land.landparent.admin.common.utils.DozerUtil;
import com.test.land.landparent.admin.entity.AuthModel;
import com.test.land.landparent.admin.entity.request.UserDTO;
import com.test.land.landparent.admin.service.RedisService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

/**
 * 通过redis实现token的超时管理
 * @author BinMang
 * @since 2017年1月17日
 */
@Component
@Slf4j
public class RedisTokenManager implements TokenManager {


    private static final String TOKEN ="TOKEN_";


    /**
     * token有效时间，8小时的秒数
     */
    private static final Integer EXPIRE_SECONDS = 28800;

    @Autowired
    RedisService redisService;


    /**
     * 创建认证用户模型(包含用户信息和生成的token)
     * @param platform
     * @param user
     * @return
     */

    @SuppressWarnings("unchecked")
    @Override
    public AuthModel createAuthModel(String platform, UserDTO user) {

        AssertUtils.notNull(user, "illegal user,'user' must not be null!");
        AssertUtils.notNull(user.getId(), "illegal user,'user_id' must not be null!");
        AssertUtils.hasText(user.getName(),"illegal user account,'name' must not be null!");
        AssertUtils.hasLength(user.getTelephone(),"illegal user account,'telephone' must not be null!");
        AssertUtils.hasLength(user.getCode(), "illegal user account,'code' must not be null!");
        AssertUtils.hasLength(platform, "illegal platform must not be null!");

        //隐藏password
        user.setPassword(null);
        Map<String, Object> params = DozerUtil.map(user, Map.class);
        params.put("platform", platform);
        params.put("time", System.currentTimeMillis());

        String token = Jwts.builder()
                .setClaims(params)
                .signWith(SignatureAlgorithm.HS512, Constants.AUTH_KEY)
                .compact();
        AuthModel authModel = new AuthModel(user, token, platform);
        redisService.setStrValue(TOKEN + platform.toUpperCase() + "_" + user.getCode(), token, EXPIRE_SECONDS);
        return authModel;
    }

    /**
     * 验证token信息的redis有效性
     */
    @Override
    public boolean checkAuthModel(AuthModel model) {
        String key = TOKEN + model.getPlatform().toUpperCase() + "_" + model.getUser().getCode();
        if (redisService.exists(key) && redisService.getStrValue(key).equals(model.getToken())) {
            redisService.setKeyExpireTime(key, EXPIRE_SECONDS);
            return true;
        }
        return false;
    }

    @Override
    public AuthModel getAuthModel(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(Constants.AUTH_KEY).parseClaimsJws(token).getBody();
            UserDTO user = DozerUtil.map(claims, UserDTO.class);
            String platform = (String) claims.get("platform");
            String key = TOKEN + platform.toUpperCase() + "_" + user.getCode();
            if (StringUtils.isNoneBlank(redisService.getStrValue(key))) {
                return new AuthModel(user, token, platform);
            }
        } catch (Exception e) {
            log.error("SignatureException when parse the token", e);
        }
        return null;
    }

    @Override
    public void deleteAuthModel(AuthModel model) {
        redisService.remove(TOKEN + model.getPlatform().toUpperCase() + "_" + model.getUser().getCode());
    }

}
