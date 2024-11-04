//package com.silence.utils;
//
//import com.silence.config.UtilFiledConfig;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jws;
//import io.jsonwebtoken.JwsHeader;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.Keys;
//import io.jsonwebtoken.security.SecureDigestAlgorithm;
//import lombok.experimental.UtilityClass;
//import lombok.extern.slf4j.Slf4j;
//
//import javax.crypto.SecretKey;
//import java.security.SecureRandom;
//import java.time.Instant;
//import java.util.Date;
//import java.util.UUID;
//
///**
// * @author silence
// * @since 2024/9/11 16:59
// **/
//@Slf4j
//@UtilityClass
//public class JwtUtil {
//
//    /**
//     * 自定义负载信息中的用户id
//     */
//    private static final String UID = "uid";
//
//    /**
//     * 过期时间(单位:秒)
//     */
//    public static int ACCESS_EXPIRE;
//
//    /**
//     * 加密算法
//     */
//    private final static SecureDigestAlgorithm<SecretKey, SecretKey> ALGORITHM = Jwts.SIG.HS256;
//
//    /**
//     * 私钥 / 生成签名的时候使用的秘钥secret，一般可以从本地配置文件中读取，切记这个秘钥不能外露，只在服务端使用，在任何场景都不应该流露出去。
//     * 一旦客户端得知这个secret, 那就意味着客户端是可以自我签发jwt了。
//     * 应该大于等于 256位(长度32及以上的字符串)，并且是随机的字符串
//     */
//    private static String SECRET;
//
//    /**
//     * jwt签发者
//     */
//    private static String JWT_ISS;
//
//    /**
//     * jwt主题
//     */
//    private static String SUBJECT;
//
//    public static void setConfig(UtilFiledConfig config) {
//        ACCESS_EXPIRE = config.getJwtExpireTime();
//        JWT_ISS = config.getJwtIssuer();
//        SUBJECT = config.getJwtSubject();
//        SECRET = config.getJwtSecretKey();
//    }
//
//    /**
//     * 生成token
//     *
//     * @param userId 用户id
//     * @return token字符串
//     */
//    public static String generateToken(String userId) {
//        // 令牌id
//        String uuid = UUID.randomUUID().toString();
//        Date exprireDate = Date.from(Instant.now().plusSeconds(ACCESS_EXPIRE));
//
//        String token = Jwts.builder()
//                // 设置头部信息header
//                .header()
//                .add("typ", "JWT")
//                .add("alg", "HS256")
//                .and()
//                // 设置自定义负载信息payload
//                .claim(UID, userId)
//                // 令牌ID
//                .id(uuid)
//                // 过期日期
//                .expiration(exprireDate)
//                // 签发时间
//                .issuedAt(new Date())
//                // 主题
//                .subject(SUBJECT)
//                // 签发者
//                .issuer(JWT_ISS)
//                // 签名
//                .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()), ALGORITHM)
//                .compact();
//        log.info("生成token：{}", token);
//
//        return token;
//    }
//
//    /**
//     * 解析token获取用户id
//     *
//     * @param token token字符串
//     * @return 用户id
//     */
//    public static String getUserId(String token) {
//        Claims claims = parsePayload(token);
//        return claims.get(UID, String.class);
//    }
//
//    /**
//     * 验证token是否有效
//     *
//     * @param token token字符串
//     * @return true：有效；false：无效
//     */
//    public static boolean validateToken(String token) {
//         try {
//             // 判断token是否过期
//             Claims claims = parsePayload(token);
//             Date expiration = claims.getExpiration();
//             if (expiration.before(new Date())) {
//                 log.warn("token[{}]已过期", token);
//
//                 return false;
//             }
//             // 判断token是否被篡改
//             JwsHeader header = parseHeader(token);
//             log.info("header.getAlgorithm()：{}", header.getAlgorithm());
//             if (header.getAlgorithm().equals(ALGORITHM.getId())) {
//                 if (claims.getSubject().equals(SUBJECT) && claims.getIssuer().equals(JWT_ISS)) {
//                     return true;
//                 }
//             }
//         } catch (Exception e) {
//             log.error("token解析失败：", e);
//         }
//        return false;
//    }
//
//    /**
//     * 解析token
//     *
//     * @param token token
//     * @return Jws<Claims>
//     */
//    private static Jws<Claims> parseClaim(String token) {
//        return Jwts.parser()
//                .verifyWith(Keys.hmacShaKeyFor(SECRET.getBytes()))
//                .build()
//                .parseSignedClaims(token);
//    }
//
//    /**
//     * 解析token中的header信息
//     *
//     * @param token token
//     * @return JwsHeader
//     */
//    private static JwsHeader parseHeader(String token) {
//        return parseClaim(token).getHeader();
//    }
//
//    /**
//     * 解析token中的payload信息
//     *
//     * @param token token
//     * @return Claims
//     */
//    private static Claims parsePayload(String token) {
//        return parseClaim(token).getPayload();
//    }
//
//    /**
//     * 生成一个安全的随机密钥
//     *
//     * @return 随机密钥
//     */
//    private static String generateSecureSecret() {
//        SecureRandom random = new SecureRandom();
//        // 32 字节对应 256 位
//        byte[] secretBytes = new byte[32];
//        random.nextBytes(secretBytes);
//        return bytesToHex(secretBytes);
//    }
//
//    /**
//     * 将字节数组转换成十六进制字符串
//     *
//     * @param bytes 字节数组
//     * @return 十六进制字符串
//     */
//    private static String bytesToHex(byte[] bytes) {
//        StringBuilder hexString = new StringBuilder();
//        for (byte b : bytes) {
//            String hex = Integer.toHexString(0xff & b);
//            if (hex.length() == 1) {
//                hexString.append('0');
//            }
//            hexString.append(hex);
//        }
//        return hexString.toString();
//    }
//
//    public static void main(String[] args) {
//        System.out.printf(generateToken("123"));
//    }
//}
