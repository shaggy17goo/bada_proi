package bada_proi.entity;

public class ParticipantRealization {
    private int participantId;
    private int realizationId;

    public ParticipantRealization(int participantId, int relationId) {
        this.participantId = participantId;
        this.realizationId = relationId;
    }

    public ParticipantRealization() {
    }

    public int getParticipantId() {
        return participantId;
    }

    public void setParticipantId(int participantId) {
        this.participantId = participantId;
    }

    public int getRealizationId() {
        return realizationId;
    }

    public void setRealizationId(int realizationId) {
        this.realizationId = realizationId;
    }

    @Override
    public String toString() {
        return "ParticipantRealization{" +
                "participantId=" + participantId +
                ", realizationId=" + realizationId +
                '}';
    }
}

