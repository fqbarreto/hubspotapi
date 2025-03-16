package fqbarreto.hubspotapi.model;

//Model baseado no payload visto no webhook configurado da minha conta hubSpot dev
public class WebhookContactPayloadModel {
    private long eventId;
    private long subscriptionId;
    private long portalId;
    private long appId;
    private long occurredAt;
    private String subscriptionType;
    private long attemptNumber;
    private long objectId;
    private String changeFlag;
    private String changeSource;
    private long sourceId;

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public long getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(long subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public long getAppId() {
        return appId;
    }

    public void setAppId(long appId) {
        this.appId = appId;
    }

    public long getPortalId() {
        return portalId;
    }

    public void setPortalId(long portalId) {
        this.portalId = portalId;
    }

    public long getOccurredAt() {
        return occurredAt;
    }

    public void setOccurredAt(long occurredAt) {
        this.occurredAt = occurredAt;
    }

    public long getAttemptNumber() {
        return attemptNumber;
    }

    public void setAttemptNumber(long attemptNumber) {
        this.attemptNumber = attemptNumber;
    }

    public long getObjectId() {
        return objectId;
    }

    public void setObjectId(long objectId) {
        this.objectId = objectId;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public String getChangeFlag() {
        return changeFlag;
    }

    public void setChangeFlag(String changeFlag) {
        this.changeFlag = changeFlag;
    }

    public long getSourceId() {
        return sourceId;
    }

    public void setSourceId(long sourceId) {
        this.sourceId = sourceId;
    }

    public String getChangeSource() {
        return changeSource;
    }

    public void setChangeSource(String changeSource) {
        this.changeSource = changeSource;
    }

}
