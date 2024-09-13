package com.sysone.ddogdog.common.config.oauth;

import com.sysone.ddogdog.common.config.Role;
import com.sysone.ddogdog.customer.auth.mapper.KakaoMapper;
import com.sysone.ddogdog.customer.auth.model.CustomerDTO;
import com.sysone.ddogdog.customer.auth.model.User;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    private final KakaoMapper kakaoMapper;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("getClientRegistration = "
            + userRequest.getClientRegistration()); // registrationId로 어떤 OAuth로 로그인 했는지 확인 가능
        log.info("getAccessToken = " + userRequest.getAccessToken().getTokenValue());

        OAuth2User oAuth2User = super.loadUser(userRequest);
        // 로그인 버튼 클릭 -> 로그인창 -> 로그인을 완료 -> code를 리턴(OAuth2-Client 라이브러리) -> AccessToken 요청
        // userRequest 정보 -> 회원 프로필 받아야함(loadUser함수 호출) -> 회원프로필 받아준다.
        log.info("getAttributes = " + oAuth2User.getAttributes());

        OAuth2UserInfo oAuth2UserInfo = null;
        KakaoUserInfo kakaoUserInfo = null;
        if (userRequest.getClientRegistration().getRegistrationId().equals("kakao")) {
            log.info("카카오 로그인 요청");
            oAuth2UserInfo = new KakaoUserInfo(oAuth2User.getAttributes());
            kakaoUserInfo = new KakaoUserInfo(oAuth2User.getAttributes());
        } else {
            log.info("우리는 카카오만 지원합니다.");
        }

        String provider = oAuth2UserInfo.getProvider();
        String providerId = oAuth2UserInfo.getProviderId();
        log.info(kakaoUserInfo.getAgeRange());
        log.info(kakaoUserInfo.getGender());
        Long id = Long.parseLong(providerId);
        String email = oAuth2UserInfo.getEmail();
        Role role = Role.CUSTOMER;

        Optional<CustomerDTO> customerDTO = kakaoMapper.existsById(id);
        User user = User.of(id,
            email,
            kakaoUserInfo.getName(),
            kakaoUserInfo.getAgeRange(),
            kakaoUserInfo.getGender(),
            Role.CUSTOMER);

        if (customerDTO.isEmpty()) {
            kakaoMapper.saveUserCustomers(user);

        } else {
            log.info("카카오 로그인을 이미 한적이 있습니다. 당신은 자동회원가입이 되어 있습니다.");
        }

        return new PrincipalDetails(user, oAuth2UserInfo);
    }
}