package application;

import java.util.ArrayList;
import java.util.List;

public class RegistrationRequest {
    private UserData userData;
    private RegistrationStatus status;
    List<RegistrationRequest> registrationRequests = new ArrayList<>();

    public void addRegistrationRequest(RegistrationRequest request) {
        registrationRequests.add(request);
    }

    public RegistrationRequest(UserData userData, RegistrationStatus status) {
        this.userData = userData;
        this.status = status;
    }
    public enum RegistrationStatus {
        APPROVED,
        REJECTED,
        PENDING
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public RegistrationStatus getStatus() {
        return status;
    }

    public void setStatus(RegistrationStatus status) {
        this.status = status;
    }
}
