package com.Demo_QuanLyBanHang.QuanLyBanHang.users.services;

import com.Demo_QuanLyBanHang.QuanLyBanHang.common.constants.JwtProperties;
import com.Demo_QuanLyBanHang.QuanLyBanHang.common.enums.ErrorCode;
import com.Demo_QuanLyBanHang.QuanLyBanHang.common.exceptions.AppException;
import com.Demo_QuanLyBanHang.QuanLyBanHang.users.dtos.request.SignIn;
import com.Demo_QuanLyBanHang.QuanLyBanHang.users.dtos.request.SignOut;
import com.Demo_QuanLyBanHang.QuanLyBanHang.users.dtos.request.SignUp;
import com.Demo_QuanLyBanHang.QuanLyBanHang.users.dtos.response.AuthResponse;
import com.Demo_QuanLyBanHang.QuanLyBanHang.users.entities.InvalidatedToken;
import com.Demo_QuanLyBanHang.QuanLyBanHang.users.entities.User;
import com.Demo_QuanLyBanHang.QuanLyBanHang.users.repositories.InvalidatedTokenRepository;
import com.Demo_QuanLyBanHang.QuanLyBanHang.users.repositories.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;
import java.util.StringJoiner;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;

    private final UserService userService;

    private final JwtProperties jwtProperties;

    private final InvalidatedTokenRepository invalidatedTokenRepository;

    public AuthResponse signIn(SignIn request) {

        String phoneNumberOrEmail = request.getEmailOrPhoneNumber();

        Optional<User> optionalUser;

        if(phoneNumberOrEmail.matches("^[0-9]{10,11}$")){
            optionalUser = userRepository.findByPhoneNumber(phoneNumberOrEmail);
        }
        else{
            optionalUser = userRepository.findByEmail(phoneNumberOrEmail);
        }
        User user = optionalUser.orElseThrow(()->new AppException(ErrorCode.USER_NOT_FOUND));

        boolean isMatch = user.getPassword().equals(request.getPassword());

        if (!isMatch) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        String accessToken = generateAccessToken(user);

        AuthResponse result = AuthResponse.builder()
                .token(accessToken)
                .build();
        return result;
    }

    public AuthResponse signUp(SignUp request) {

        boolean isPasswordConfirmed = request.getPassword().equals(request.getConfirmPassword());

        if(!isPasswordConfirmed) {
            throw new AppException(ErrorCode.PASSWORD_CONFIRM_NOT_MATCH);
        }

        User user = userService.createUser(request);

        String accessToken = generateAccessToken(user);

        AuthResponse result = AuthResponse.builder()
                .token(accessToken)
                .build();
        return result;
    }

    private String generateAccessToken(User user) {
        Instant now = Instant.now();
        Instant expiry = now.plusSeconds(jwtProperties.getAccessTokenExpirationTime());
        JWTClaimsSet claimSet = new JWTClaimsSet.Builder()
                .subject(user.getFullName())
                .issuer(jwtProperties.getIssuer())
                .issueTime(Date.from(now))
                .expirationTime(Date.from(expiry))
                .claim("userId", user.getUserId())
                .claim("roles", user.getRoles())
                .build();
        JWSObject jwsObject = new JWSObject(new JWSHeader(JWSAlgorithm.HS512), new Payload(claimSet.toJSONObject()));
        try{
            jwsObject.sign(new MACSigner(jwtProperties.getSecret()));
            var accessToken = jwsObject.serialize();
            return accessToken;
        }catch(JOSEException e){
            throw new AppException(ErrorCode.TOKEN_SIGNING_FAILED);
        }
    }

    private String generateRefreshToken(User user) {
        Instant now = Instant.now();
        Instant expiry = now.plusSeconds(jwtProperties.getRefreshTokenExpirationTime());
        JWTClaimsSet claimSet = new JWTClaimsSet.Builder()
                .subject(user.getFullName())
                .issuer(jwtProperties.getIssuer())
                .issueTime(Date.from(now))
                .expirationTime(Date.from(expiry))
                .claim("type", "refresh")
                .build();
        JWSObject jwsObject = new JWSObject(new JWSHeader(JWSAlgorithm.HS512), new Payload(claimSet.toJSONObject()));
        try{
            jwsObject.sign(new MACSigner(jwtProperties.getSecret()));
            var refreshToken = jwsObject.serialize();
            return refreshToken;
        } catch (JOSEException e) {
            throw new AppException(ErrorCode.TOKEN_SIGNING_FAILED);
        }
    }

    public void signOut(SignOut token) throws ParseException, JOSEException {
        try {
            var signToken = verifyToken(token.getToken(), true);

            String jit = signToken.getJWTClaimsSet().getJWTID();
            Date expiryTime = signToken.getJWTClaimsSet().getExpirationTime();

            InvalidatedToken invalidatedToken =
                    InvalidatedToken.builder().id(jit).expiryTime(expiryTime).build();

            invalidatedTokenRepository.save(invalidatedToken);
        } catch (AppException exception) {
        }
    }

    private void validateRefreshToken(String token){
        try{
            JWSObject jwsObject = JWSObject.parse(token);
            jwsObject.verify(new MACVerifier(jwtProperties.getSecret()));
            JWTClaimsSet jwtClaimsSet = JWTClaimsSet.parse(jwsObject.getPayload().toJSONObject());
            Date expirationTime = jwtClaimsSet.getExpirationTime();
            if(expirationTime.before(new Date())){
                throw new AppException(ErrorCode.INVALID_TOKEN);
            }
            String issuer = jwtClaimsSet.getIssuer();
            if(!issuer.equals(jwtProperties.getIssuer())){
                throw new AppException(ErrorCode.INVALID_TOKEN);
            }
            String type = (String) jwtClaimsSet.getClaim("type");
            if(!"refresh".equals(type)){
                throw new AppException(ErrorCode.INVALID_TOKEN);
            }
        }catch(ParseException | JOSEException e){
            throw new AppException(ErrorCode.INVALID_TOKEN);
        }
    }

    private SignedJWT verifyToken(String token, boolean isRefresh) throws JOSEException, ParseException {
        JWSVerifier verifier = new MACVerifier(jwtProperties.getSecret().getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);
        Date expiryTime = (isRefresh)
                ? new Date (signedJWT.getJWTClaimsSet()
                .getIssueTime()
                .toInstant()
                .plus(jwtProperties.getRefreshTokenExpirationTime(), ChronoUnit.SECONDS).toEpochMilli())
                : signedJWT.getJWTClaimsSet().getExpirationTime();
        var verified = signedJWT.verify(verifier);
        if(!(verified && expiryTime.after(new Date()))){
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
        if(invalidatedTokenRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID()))
            throw new AppException(ErrorCode.UNAUTHORIZED);
        return signedJWT;
    }

//    private String buildScope(User user){
//        StringJoiner stringJoiner = new StringJoiner("");
//        if(!CollectionUtils.isEmpty(user.getRoles()))
//            user.getRoles().forEach(role -> {
//                stringJoiner.add(role.getName());
//                if(!CollectionUtils.isEmpty(role.getPermission())){
//                    role.getPermission().forEach(permission -> stringJoiner.add(permission.getName()));
//                }
//            });
//        return stringJoiner.toString();
//    }
}
