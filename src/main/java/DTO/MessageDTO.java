package DTO;

public class MessageDTO {
    private int message_id;
    private int posted_by;
    private String message_text;
    private long time_posted_epoch;

    public MessageDTO() {
    }

    public int getMessage_id() {
        return message_id;
    }

   public MessageDTO(int message_id, int posted_by, String message_text, long time_posted_epoch) {
    this.message_id = message_id;
    this.posted_by = posted_by;
    this.message_text = message_text;
    this.time_posted_epoch = time_posted_epoch;
    }

    public void setMessage_id(int message_id) {
        this.message_id = message_id;
    }
    public int getPosted_by() {
        return posted_by;
    }
    public void setPosted_by(int posted_by) {
        this.posted_by = posted_by;
    }
    public String getMessage_text() {
        return message_text;
    }
    public void setMessage_text(String message_text) {
        this.message_text = message_text;
    }
    public long getTime_posted_epoch() {
        return time_posted_epoch;
    }
    public void setTime_posted_epoch(long time_posted_epoch) {
        this.time_posted_epoch = time_posted_epoch;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + message_id;
        result = prime * result + posted_by;
        result = prime * result + ((message_text == null) ? 0 : message_text.hashCode());
        result = prime * result + (int) (time_posted_epoch ^ (time_posted_epoch >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MessageDTO other = (MessageDTO) obj;
        if (message_id != other.message_id)
            return false;
        if (posted_by != other.posted_by)
            return false;
        if (message_text == null) {
            if (other.message_text != null)
                return false;
        } else if (!message_text.equals(other.message_text))
            return false;
        if (time_posted_epoch != other.time_posted_epoch)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "MessageDTO [message_id=" + message_id + ", posted_by=" + posted_by + ", message_text=" + message_text
                + ", time_posted_epoch=" + time_posted_epoch + "]";
    }
}