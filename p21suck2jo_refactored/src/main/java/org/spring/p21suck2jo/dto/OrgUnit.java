package org.spring.p21suck2jo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrgUnit {
    private int domainId;
    private String orgUnitId;
    private String orgUnitExternalKey;
    private String orgUnitName;
    private OrgUnitI18nName[] i18nNames;
    private String email;
    private String description;
    private boolean visible;
    private String parentOrgUnitId;
    private String parentExternalKey;
    private int displayOrder;
    private int displayLevel;
    private String[] aliasEmails;
    private boolean canReceiveExternalMail;
    private boolean useMessage;
    private boolean useNote;
    private boolean useCalendar;
    private boolean useTask;
    private boolean useFolder;
    private boolean useServiceNotification;
    private OrgUnitAllowedMember[] membersAllowedToUseOrgUnitEmailAsRecipient;
    private OrgUnitAllowedMember[] membersAllowedToUseOrgUnitEmailAsSender;
}
