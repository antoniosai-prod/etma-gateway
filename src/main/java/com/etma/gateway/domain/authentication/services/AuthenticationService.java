package com.etma.gateway.domain.authentication.services;

import com.etma.gateway.domain.authentication.dto.AuthenticationLoginDTO;
import com.etma.gateway.domain.user.entities.UserMaterializedViewEntity;

public interface AuthenticationService {
    UserMaterializedViewEntity authenticate(AuthenticationLoginDTO input);
}
