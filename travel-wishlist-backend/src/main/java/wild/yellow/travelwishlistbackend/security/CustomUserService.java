package wild.yellow.travelwishlistbackend.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import wild.yellow.travelwishlistbackend.store.repositories.ConsumerRepository;

@Service
@RequiredArgsConstructor
public class CustomUserService implements UserDetailsService {

    private final ConsumerRepository consumerRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return consumerRepository.findByUsername(username).map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
