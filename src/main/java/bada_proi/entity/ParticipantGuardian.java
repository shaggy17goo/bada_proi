package bada_proi.entity;

public class ParticipantGuardian {
    private int participantId;
    private int guardianID;

    public ParticipantGuardian(int participantId, int guardianID) {
        this.participantId = participantId;
        this.guardianID = guardianID;
    }

    public ParticipantGuardian() {
    }

    public int getParticipantId() {
        return participantId;
    }

    public void setParticipantId(int participantId) {
        this.participantId = participantId;
    }

    public int getGuardianID() {
        return guardianID;
    }

    public void setGuardianID(int guardianID) {
        this.guardianID = guardianID;
    }

    @Override
    public String toString() {
        return "ParticipantGuardian{" +
                "participantId=" + participantId +
                ", guardianID=" + guardianID +
                '}';
    }
}

