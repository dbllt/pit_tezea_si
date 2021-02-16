package tezea.si.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class SimpleRefreshTokenService implements RefreshTokenService {

    Logger logger = LogManager.getLogger(getClass());

    Map<String, Long> refreshTokens = new HashMap<>();

    private static final long REFRESH_TOKEN_EXPIRATION = 60 * 1000; // 60seconds
//    private static final long REFRESH_TOKEN_EXPIRATION = 3 * 60 * 60 * 1000; // 3hours

    @Override
    public void saveRefreshToken(String refreshToken) {
        logger.trace("saving a refresh token");
        refreshTokens.put(refreshToken, System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION);
    }

    @Override
    public boolean isValid(String refreshToken) {
        logger.debug(refreshTokens);
        if (!refreshTokens.containsKey(refreshToken))
            return false;

        if (refreshTokens.get(refreshToken) - System.currentTimeMillis() < 0) {
            logger.trace("refresh token is expired");
            invalidate(refreshToken);
            return false;
        }

        return true;
    }

    @Override
    public void invalidate(String refreshToken) {
        logger.trace("invalidating a refresh token");
        refreshTokens.remove(refreshToken);
    }

    @Override
    public void refreshValidity(String refreshToken) {
        logger.trace("refreshing validity of a refresh token");
        saveRefreshToken(refreshToken);
    }

}
