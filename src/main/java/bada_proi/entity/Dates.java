package bada_proi.entity;

import java.sql.Date;

public class Dates {
    private int dateId;
    private Date startDate;
    private Date finishDate;
    private int realizationId;

    public Dates(int dateId, Date startDate, Date finishDate, int realizationId) {
        this.dateId = dateId;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.realizationId = realizationId;
    }

    public Dates() {
    }

    public int getDateId() {
        return dateId;
    }

    public void setDateId(int dateId) {
        this.dateId = dateId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public int getRealizationId() {
        return realizationId;
    }

    public void setRealizationId(int realizationId) {
        this.realizationId = realizationId;
    }

    @Override
    public String toString() {
        return "Dates{" +
                "dateId=" + dateId +
                ", startDate=" + startDate +
                ", finishDate=" + finishDate +
                ", realizationId=" + realizationId +
                '}';
    }
}
