package datn.entity;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuditorAwareImpl implements AuditorAware<String>{

    public static final String ANONYMOUS_USER = "anonymousUser";

//    @Override
    public String getCurrentAuditor() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && !ANONYMOUS_USER.equals(auth.getPrincipal())) {
//            User user = (User) auth.getDetails();
//            return user.getId();
        }
        return "SYSTEM";
    }

}
