package com.spring.blog.security.oauth2.user;

import java.util.Map;

/**
 * Каждый поставщик OAuth2 возвращает свой ответ JSON, когда мы получаем данные аутентифицированного пользователя.
 * Spring security анализирует ответ в форме мапы.
 * Следующий классы используются для получения необходимых сведений о пользователе
 */
public abstract class OAuth2UserInfo {

    protected Map<String, Object> attributes;

    public OAuth2UserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public abstract String getId();

    public abstract String getName();

    public abstract String getEmail();

    public abstract String getImageUrl();
}
