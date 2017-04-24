package rs.team15.service;

import rs.team15.model.SystemManager;
import rs.team15.model.User;

public interface SystemManagerService {
	SystemManager getSystemManager(Long id);

    User create(SystemManager sysManager);
}
