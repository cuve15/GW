package jwt.util;

import java.util.Date;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {
	private static final String SECRET_KEY = "dk-wlqdp-rkrh-tlvek.";

	public static String createToken(String empId, String position, String department) {
		long now = System.currentTimeMillis();
		return Jwts.builder()
				.setSubject(empId)
				.claim("position", position)
				.claim("department", department)
				.setIssuedAt(new Date(now))
				.setExpiration(new Date(now + 1000L * 60 * 60 * 16))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY)
				.compact();
	}

	private static Claims parseToken(String token) {
		return Jwts.parser()
				.setSigningKey(SECRET_KEY)
				.parseClaimsJws(token)
				.getBody();
	}

	public static String getEmpId(String token) {
		return parseToken(token).getSubject();
	}

	public static String getPosition(String token) {
		return (String) parseToken(token).get("position");
	}

	public static String getDepartment(String token) {
		return (String) parseToken(token).get("department");
	}
}
