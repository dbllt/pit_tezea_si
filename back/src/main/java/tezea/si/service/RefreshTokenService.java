package tezea.si.service;

/**
 * Interface to add a system of refresh tokens
 * 
 * @author Nils Richard
 *
 */
public interface RefreshTokenService {

    /**
     * Saves the refreshToken to keep track of currently valid refresh tokens
     * a valid refresh token is one that was saved and not removed
     * 
     * @param username owner of the refresh token
     * @param refreshToken
     */
    void saveRefreshToken(String refreshToken);
    
    /**
     * Checks if the refresh token is in the valid refresh tokens
     * 
     * @param refreshToken
     * @return
     */
    boolean isValid(String refreshToken);
    
    /**
     * Removes the refresh token from the valid refresh tokens
     * 
     * @param refreshToken
     */
    void invalidate(String refreshToken);
    
    /**
     * Refreshes the validity of the refresh token 
     * 
     * @param refreshToken
     */
    void refreshValidity(String refreshToken);
}
