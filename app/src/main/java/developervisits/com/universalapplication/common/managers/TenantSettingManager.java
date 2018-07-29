package developervisits.com.universalapplication.common.managers;

/**
 * Created by developervisits on 29/7/18.
 *
 * @author Praveen.verma | Noida
 */

public class TenantSettingManager {

    private static TenantSettingManager tenantSettingManager;

    private TenantSettingManager() {

    }

    public static TenantSettingManager getInstance() {
        if (tenantSettingManager == null) {
            tenantSettingManager = new TenantSettingManager();
        }
        return tenantSettingManager;
    }
    private boolean isNotificationsVisible;

    public boolean isNotificationsVisible() {
        return true;
    }

    public void setNotificationsVisible(boolean notificationsVisible) {
        isNotificationsVisible = notificationsVisible;
    }
}
