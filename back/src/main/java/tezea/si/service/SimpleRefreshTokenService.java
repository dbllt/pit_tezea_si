package tezea.si.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class SimpleRefreshTokenService implements RefreshTokenService {

    List<String> refreshTokens = new ArrayList<String>();

    @Override
    public void saveRefreshToken(String refreshToken) {
        refreshTokens.add(refreshToken);
    }

    @Override
    public boolean isValid(String refreshToken) {
        return refreshTokens.contains(refreshToken);
    }

    @Override
    public void invalidate(String refrehToken) {
        refreshTokens.remove(refrehToken);
    }

}
